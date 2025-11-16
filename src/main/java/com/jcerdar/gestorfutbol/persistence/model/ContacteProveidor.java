package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;

@Entity
@Table(name = "GF_CONTACTEPROV")
public class ContacteProveidor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CPR_ID", nullable = false)
    private Long id;

    @Column(name = "CPR_NOM", nullable = false)
    private String nom;

    @Column(name = "CPR_EMAIL")
    private String email;

    @Column(name = "CPR_TELF")
    private String telefon;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
