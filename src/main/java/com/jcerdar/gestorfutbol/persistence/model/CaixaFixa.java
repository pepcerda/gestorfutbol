package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "GF_CAIXAFIXA")
public class CaixaFixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CF_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CF_CAM", nullable = false)
    private Campanya campanya;

    @Column(name = "CF_NOM", nullable = false)
    private String nom;

    @Column(name = "CF_LLIN1")
    private String llinatge1;

    @Column(name = "CF_LLIN2")
    private String llinatge2;

    @Column(name = "CF_DESP", nullable = false, precision = 15, scale = 2)
    private BigDecimal despesa;

    @Column(name = "CF_OBS", length = 1024)
    private String observacio;

    @Column(name = "CF_FACT")
    private String factura;

    @Column(name = "CF_ESTAT")
    private String estat;

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

    public BigDecimal getDespesa() {
        return despesa;
    }

    public void setDespesa(BigDecimal despesa) {
        this.despesa = despesa;
    }

    public String getObservacio() {
        return observacio;
    }

    public void setObservacio(String observacio) {
        this.observacio = observacio;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
}
