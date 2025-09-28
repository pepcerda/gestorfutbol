package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;

public class MembrePlantillaDTO {

    private Long id;

    private Long campanya;

    private String nom;

    private String llinatge1;

    private String llinatge2;

    private Date dataNaixement;

    public MembrePlantillaDTO() {

    }

    public MembrePlantillaDTO(Long id, Long campanya, String nom, String llinatge1, String llinatge2, Date dataNaixement) {
        this.id = id;
        this.campanya = campanya;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.dataNaixement = dataNaixement;
    }

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
