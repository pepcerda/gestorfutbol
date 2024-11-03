package com.jcerdar.gestorfutbol.service.model;

public class SociDTO {

    private Long id;

    private Long campanya;

    //Estat pagament. Possibles valors "P" (Pagat) o "D" (Pendent pagament)
    private String estatPagament;

    private String nom;

    private String llinatge1;

    private String llinatge2;

    private Boolean patrocinador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampanya() {
        return campanya;
    }

    public void setCampanya(Long campanya) {
        this.campanya = campanya;
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

    public Boolean getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Boolean patrocinador) {
        this.patrocinador = patrocinador;
    }
}
