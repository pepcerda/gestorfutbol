package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_ROL_USUARI_TENANT")
public class RolUsuariTenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID", nullable = false)
    private Long id;

    @Column(name = "ROL_NOM", nullable = false)
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
