package com.example.Projet.repository;

import com.example.Projet.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    Activite findByNom(String nom);
}
