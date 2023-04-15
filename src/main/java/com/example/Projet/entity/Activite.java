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
@Table(name = "activite")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_activite;

    private String nom;

    private String description_activite;

    @ManyToMany
    @JoinTable(name = "activite_programme",
            joinColumns = @JoinColumn(name = "activite_id"),
            inverseJoinColumns = @JoinColumn(name = "programme_id"))
    private List<Programme> programmes;

    @OneToMany(mappedBy = "activite")
    private List<Notation> notations;
}

