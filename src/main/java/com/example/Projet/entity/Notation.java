package com.example.Projet.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notation")
public class Notation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notation;

    private int note;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name="id_activite")
    private Activite activite;

}
