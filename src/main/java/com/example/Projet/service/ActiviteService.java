package com.example.Projet.service;

import com.example.Projet.entity.Activite;
import com.example.Projet.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

    public void enregistrerActivite(Activite activite) {
        activiteRepository.save(activite);
    }

    public Activite findByNom(String nom){
       return activiteRepository.findByNom(nom);
    }
}
