package com.example.Projet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    private String nom;

    private String prenom;

    private String email;

    private String mot_de_passe;

    private String role;

    @OneToMany(mappedBy = "utilisateur")
    private List<Programme> programmes;

    @OneToMany(mappedBy = "utilisateur")
    private List<Notation> notations;
}
