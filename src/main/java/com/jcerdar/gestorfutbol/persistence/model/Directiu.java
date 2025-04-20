package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_DIRECTIU")
public class Directiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIR_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DIR_ROL", nullable = false)
    private RolDirectiu rol;

    @ManyToOne
    @JoinColumn(name = "DIR_DIR", nullable = false)
    private Directiva directiva;

    @Column(name = "DIR_NOM", nullable = false)
    private String nom;

    @Column(name = "DIR_LLIN1")
    private String llinatge1;

    @Column(name = "DIR_LLIN2")
    private String llinatge2;

    @Column(name = "DIR_NIF")
    private String nif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolDirectiu getRol() {
        return rol;
    }

    public void setRol(RolDirectiu rol) {
        this.rol = rol;
    }

    public Directiva getDirectiva() {
        return directiva;
    }

    public void setDirectiva(Directiva directiva) {
        this.directiva = directiva;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatge1() {
        return llinatge1;
    }

    public void setLlinatge1(String llinatge1) {
        this.llinatge1 = llinatge1;
    }

    public String getLlinatge2() {
        return llinatge2;
    }

    public void setLlinatge2(String llinatge2) {
        this.llinatge2 = llinatge2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
