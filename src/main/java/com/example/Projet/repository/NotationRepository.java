package com.example.Projet.repository;

import com.example.Projet.entity.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotationRepository extends JpaRepository<Notation, Long> {
}
