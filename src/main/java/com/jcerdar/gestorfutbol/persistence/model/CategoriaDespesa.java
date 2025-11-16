package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;

@Entity
@Table(name = "GF_CATDESPESA")
public class CategoriaDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDE_ID", nullable = false)
    private Long id;

    @Column(name = "CDE_NOM", nullable = false, length = 100)
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
