package com.example.Projet.service;

import com.example.Projet.entity.Utilisateur;
import com.example.Projet.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }


    public void enregistreUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}
