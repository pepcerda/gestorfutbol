package com.jcerdar.gestorfutbol.service.model;

public class ConfiguracioDTO {

    private Long id;

    private Long tenantId;

    private String cif;

    private String nom;

    private String colorPrincipal;

    private String logo;

    private String colorFons1;

    private String colorFons2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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
