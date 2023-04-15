package com.example.Projet.service;

import com.example.Projet.entity.Programme;
import com.example.Projet.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    public void enregistreProgramme(Programme p) {
        programmeRepository.save(p);
    }
}
