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

    @Column(name = "TEN_ESTAT", nullable = false)
    private String estat;

    @CreationTimestamp
    @Column(name = "TEN_FCREA", nullable = false)
    private Date dataCreacio;

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

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }
}
