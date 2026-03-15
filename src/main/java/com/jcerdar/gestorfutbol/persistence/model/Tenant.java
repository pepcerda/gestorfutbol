package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "GF_TENANT")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEN_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEN_PLA", nullable = false)
    private PlaSuscripcio plaSuscripcio;

    @Column(name = "TEN_NOM", nullable = false)
    private String nom;

    @Column(name = "TEN_ACTIU", nullable = false)
    private Boolean actiu;

    @CreationTimestamp
    @Column(name = "TEN_FCREA", nullable = false)
    private Date dataCreacio;

    @Column(name = "TEN_FBAIXA")
    private Date dataBaixa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaSuscripcio getPlaSuscripcio() {
        return plaSuscripcio;
    }

    public void setPlaSuscripcio(PlaSuscripcio plaSuscripcio) {
        this.plaSuscripcio = plaSuscripcio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
}
