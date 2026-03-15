package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_USUARI")
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID", nullable = false)
    private Long id;

    @Column(name = "USU_PROVID", nullable = false)
    private String providerId;

    @Column(name = "USU_SUPERADMIN", nullable = false)
    private Boolean superAdmin;

    @Column(name = "USU_NOM", nullable = false)
    private String nom;

    @Column(name = "USU_COGNOMS", nullable = false)
    private String cognoms;

    @Column(name = "USU_EMAIL", nullable = false)
    private String email;

    @PrePersist
    public void prePersist() {
        if (superAdmin == null) superAdmin = false;
    }

    @PreUpdate
    public void preUpdate() {
        if (superAdmin == null) superAdmin = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
