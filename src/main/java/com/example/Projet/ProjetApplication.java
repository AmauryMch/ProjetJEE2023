package com.example.Projet;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Notation;
import com.example.Projet.entity.Programme;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.ProgrammeService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private ProgrammeService programmeService;


	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utilisateur u=new Utilisateur(null, "User", "user", "user", "user", "USER",new ArrayList<Programme>(), new ArrayList<Notation>());
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "admin", "admin", "admin", "admin", "ADMIN", new ArrayList<Programme>(), new ArrayList<Notation>()));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Mechin", "Amaury", "amaury@email.fr", "user", "USER",new ArrayList<Programme>(), new ArrayList<Notation>()));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Bardel", "François", "francois@email.fr", "user", "USER",new ArrayList<Programme>(), new ArrayList<Notation>()));

		activiteService.enregistrerActivite(new Activite(null, "Pétanque", "Lancer des boules tout en buvant un ptit jaune", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Basketball", "Marquer des paniers en équipe", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Football", "Fouler la pelouse et marquer des buts", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Volleyball", "Frapper une balle avec les mains", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Natation", "Nager pour se dépenser", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Badminton", "Jouer en raquette avec un volant", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Tennis", "Frapper une balle avec une raquette", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Ark", "Dino dino", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Gymnastique", "Faire des mouvements pour se muscler", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Escalade", "Monter une paroi à l'aide de cordes", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Randonnée", "Marcher en nature pour découvrir de nouveaux paysages", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Cyclisme", "Pédaler sur des routes ou des chemins", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Yoga", "Pratiquer des postures pour se détendre", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Taekwondo", "Combattre avec les poings et les pieds", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Boxe", "Se défendre en utilisant les techniques de combat", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Judo", "Se battre en utilisant des techniques de projection", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Beyblade", "Devenir le meilleur blader", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Karate", "Pratiquer des techniques d'autodéfense japonaises", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Aikido", "Pratiquer des techniques d'autodéfense japonaises", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Krav Maga", "Pratiquer des techniques d'autodéfense israéliennes", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Danse", "Danser sur différents types de musique", null, new ArrayList<Notation>()));
		activiteService.enregistrerActivite(new Activite(null, "Musculation", "Travailler ses muscles pour les renforcer", null, new ArrayList<Notation>()));

		utilisateurService.enregistreUtilisateur(u);

		Programme p=new Programme(null, "Sport",u, new ArrayList<Activite>(),0);
		Programme p2=new Programme(null, "Geek puant",u, new ArrayList<Activite>(),0);
		programmeService.enregistreProgramme(p);
		programmeService.enregistreProgramme(p2);
		List<Programme> pl = u.getProgrammes();
		pl.add(p);
		pl.add(p2);
		u.setProgrammes(pl);
		utilisateurService.enregistreUtilisateur(u);


	}
}
