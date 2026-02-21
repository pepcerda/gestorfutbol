package com.jcerdar.gestorfutbol.service.model;

public class PlantillaDocumentDTO {

    private Long id;
    private Long tenantId;
    private String codi;
    private String nom;
    private String idioma;
    private String contingutHtml;
    private String contingutCss;

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

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getContingutHtml() {
        return contingutHtml;
    }

    public void setContingutHtml(String contingutHtml) {
        this.contingutHtml = contingutHtml;
    }

    public String getContingutCss() {
        return contingutCss;
    }

    public void setContingutCss(String contingutCss) {
        this.contingutCss = contingutCss;
    }
}
