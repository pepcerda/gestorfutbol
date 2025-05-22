package com.jcerdar.gestorfutbol.service.model;

public class ConfiguracioGeneralDTO {

    private String logo;

    private String nom;

    private String colorPrincipal;

    private String colorFons1;

    private String colorFons2;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
