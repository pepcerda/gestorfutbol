package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_CONFIG")
public class Configuracio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONF_ID", nullable = false)
    private Long id;

    @Column(name = "CONF_CIF")
    private String cif;

    @Column(name = "CONF_NOM")
    private String nom;

    @Column(name = "CONF_COL")
    private String colorPrincipal;

    @Column(name = "CONF_LOGO")
    private String logo;

    @Column(name = "CONF_CLF1")
    private String colorFons1;

    @Column(name = "CONF_CLF2")
    private String colorFons2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getColorFons1() {
        return colorFons1;
    }

    public void setColorFons1(String colorFons1) {
        this.colorFons1 = colorFons1;
    }

    public String getColorFons2() {
        return colorFons2;
    }

    public void setColorFons2(String colorFons2) {
        this.colorFons2 = colorFons2;
    }
}
