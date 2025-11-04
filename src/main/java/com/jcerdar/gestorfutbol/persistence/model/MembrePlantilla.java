package com.jcerdar.gestorfutbol.persistence.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_MEMPLANT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "MEM_ROL", discriminatorType = DiscriminatorType.STRING)
public abstract class MembrePlantilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEM_CAM", nullable = false)
    private Campanya campanya;

    @ManyToOne
    @JoinColumn(name = "MEM_CAT", nullable = false)
    private Categoria categoria;

    @Column(name = "MEM_NOM", nullable = false)
    private String nom;

    @Column(name = "MEM_LLIN1")
    private String llinatge1;

    @Column(name = "MEM_LLIN2")
    private String llinatge2;

    @Column(name = "MEM_DNAIX", nullable = false)
    private Date dataNaixement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

}

