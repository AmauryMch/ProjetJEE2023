package com.example.Projet.controller;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Notation;
import com.example.Projet.entity.Programme;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.NotationService;
import com.example.Projet.service.ProgrammeService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private NotationService notationService;



    @GetMapping("/")
    public String home(Model model, HttpSession s) {
        if (!s.equals(null)) {
            String email = (String) s.getAttribute("email");
            if (email != null) {
                Utilisateur u = utilisateurService.findByEmail(email);
                model.addAttribute("user", u);

                List<Programme> p = u.getProgrammes();
                model.addAttribute("ListeProgrammes", p);
            }
        }
        List<Activite> activite = activiteService.getAllActivites();
        model.addAttribute("activite", activite);
        return "home";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "formConnexion";
    }

    @GetMapping("/activite/add")
    public String showFormActivite(HttpSession s) {
        String role = (String) s.getAttribute("role");
        if(role!=null) {
            if (role.equals("ADMIN")) {
                return "formActivite";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/utilisateurs")
    public String afficherUtilisateurs(Model model,HttpSession s) {
        String role = (String) s.getAttribute("role");
        if(role!=null) {
            if (role.equals("ADMIN")) {
                List<Utilisateur> utilisateur = utilisateurService.getAllUtilisateurs();
                model.addAttribute("utilisateur", utilisateur);
                return "utilisateurs";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/inscription")
    public String showFormUtilisateur() {
        return "formInscription";
    }

    @PostMapping("/formActivite")
    public String addActivite(String nom, String description_activite, Model model) {
        List<Activite> la = activiteService.getAllActivites();
        boolean existe = false;
        for (int temp = 0; temp < la.size(); temp++) {
            if (la.get(temp).getNom().equals(nom)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            Activite activite = new Activite(null, nom, description_activite, null, null);
            activiteService.enregistrerActivite(activite);
            return "redirect:/";
        } else {
            model.addAttribute("erreur", "Une activité porte deja ce nom");
            return "formActivite";
        }
    }

    @PostMapping("/formInscription")
    public String addUtilisateur(String nom, String prenom, String email, String motDePasse, Model model) {
        List<Utilisateur> lu = utilisateurService.getAllUtilisateurs();
        boolean existe = false;
        for (int temp = 0; temp < lu.size(); temp++) {
            if (lu.get(temp).getEmail().equals(email)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            List<Programme> l = new ArrayList<Programme>();
            Utilisateur utilisateur = new Utilisateur(null, nom, prenom, email, motDePasse, "USER", l, null);
            utilisateurService.enregistreUtilisateur(utilisateur);
            return "redirect:/connexion";
        } else {
            model.addAttribute("erreur", "Un compte existe deja avec cet Email");
            return "formInscription";
        }
    }

    @PostMapping("/formConnexion")
    public String connexion(String email, String motDePasse, Model model, HttpSession s) {
        HttpSession session = s;
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null && utilisateur.getMot_de_passe().equals(motDePasse)) {
            model.addAttribute("User", utilisateur);
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("nom", utilisateur.getNom());
            session.setAttribute("prenom", utilisateur.getPrenom());
            session.setAttribute("id", utilisateur.getId_utilisateur());
            session.setAttribute("role", utilisateur.getRole());
            session.setAttribute("programmes", utilisateur.getProgrammes());
            return "redirect:/";
        } else {
            model.addAttribute("erreur", "Email ou mot de passe invalide.");
            return "formConnexion";
        }
    }

    @GetMapping("/profil")
    public String profil(Model model, HttpSession s) {
        String email = s.getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        List<Programme> p = u.getProgrammes();

        List<Notation> ln = new ArrayList<Notation>();
        for (int temp = 0; temp < p.size(); temp++) {
            List<Activite> la = p.get(temp).getActivites();
            float note = 0;
            for (int i = 0; i < la.size(); i++) {
                List<Notation> ln2 = la.get(i).getNotations();
                for (int j = 0; j < ln2.size(); j++) {
                    if (ln2.get(j).getUtilisateur().equals(u)) {
                        note += ln2.get(j).getNote();
                    }
                }
            }
            if (la.size() > 0) {
                note = note / la.size();
                p.get(temp).setNote(note);
                programmeService.enregistreProgramme(p.get(temp));
            }
        }

        model.addAttribute("programmes", p);

        List<Activite> a = new ArrayList<>();
        for (int temp = 0; temp < p.size(); temp++) {
            Programme pr = p.get(temp);
            for (int temp2 = 0; temp2 < pr.getActivites().size(); temp2++) {
                if (!a.contains(pr.getActivites().get(temp2))) {
                    a.add(pr.getActivites().get(temp2));
                }
            }
        }

        List<Notation> notes = new ArrayList<>();
        for (int temp3 = 0; temp3 < u.getNotations().size(); temp3++) {
            notes.add(u.getNotations().get(temp3));
        }
        model.addAttribute("notes", notes);
        model.addAttribute("utilisateur", u);
        model.addAttribute("activite", a);
        return "profil";
    }

    @PostMapping("/add")
    public String ajoutProgramme(String nom, String choixProg, HttpSession s) {
        String email = s.getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        List<Programme> lp = u.getProgrammes();

        Activite a = activiteService.findByNom(nom);

        for (int temp = 0; temp < lp.size(); temp++) {
            if (lp.get(temp).getNom().equals(choixProg)) {
                Programme p = lp.get(temp);
                if(!p.getActivites().contains(a)){
                    p.getActivites().add(a);

                    a.getProgrammes().add(p);

                    programmeService.enregistreProgramme(p);
                    activiteService.enregistrerActivite(a);

                }
            }
        }
        return "redirect:/profil";
    }

    @PostMapping("/newProgramme")
    public String NewProgramme(String nom, HttpSession s) {
        String email = s.getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        Programme p = new Programme(null, nom, u, new ArrayList<Activite>(), 0);
        programmeService.enregistreProgramme(p);
        List<Programme> pl = u.getProgrammes();
        pl.add(p);
        u.setProgrammes(pl);
        utilisateurService.enregistreUtilisateur(u);
        return "redirect:/profil";
    }

    @PostMapping("/notation")
    public String notation(String nomAct, int choix, HttpSession s) {
        String email = s.getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        Activite a = activiteService.findByNom(nomAct);
        boolean ok = true;
        for (int temp = 0; temp < u.getNotations().size(); temp++) {
            for (int i = 0; i < a.getNotations().size(); i++) {
                if (a.getNotations().get(i) == u.getNotations().get(temp)) {
                    ok = false;
                    Notation n = u.getNotations().get(temp);
                    n.setNote(choix);
                    notationService.enregistreNotation(n);
                }
            }
        }
        if (ok) {
            notationService.enregistreNotation(new Notation(null, choix, u, a));
        }


        return "redirect:/profil";
    }

    @GetMapping("/logout")
    public String logout(HttpSession s) {
        s.invalidate();
        return "redirect:/connexion";
    }

}
