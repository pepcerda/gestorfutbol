package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_SOCI")
public class Soci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOC_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SOC_CAM", nullable = false)
    private Campanya campanya;

    @ManyToOne
    @JoinColumn(name = "SOC_TIP", nullable = false)
    private TipoSoci tipoSoci;

    //Estat pagament. Possibles valors "P" (Pagat) o "D" (Pendent pagament)
    @Column(name = "SOC_ESTPAG", length = 1, nullable = false)
    private String estatPagament; 

    @Column(name = "SOC_NOM", nullable = false)
    private String nom; 

    @Column(name = "SOC_LLIN1")
    private String llinatge1; 

    @Column(name = "SOC_LLIN2")
    private String llinatge2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(String estatPagament) {
        this.estatPagament = estatPagament;
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

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }

    public TipoSoci getTipoSoci() {
        return tipoSoci;
    }

    public void setTipoSoci(TipoSoci tipoSoci) {
        this.tipoSoci = tipoSoci;
    }
}
