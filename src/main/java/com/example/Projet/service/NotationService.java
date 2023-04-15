package com.example.Projet.service;

import com.example.Projet.entity.Notation;
import com.example.Projet.repository.NotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotationService {

    @Autowired
    NotationRepository notationRepository;

    public void enregistreNotation(Notation n) {
        notationRepository.save(n);
    }

}
