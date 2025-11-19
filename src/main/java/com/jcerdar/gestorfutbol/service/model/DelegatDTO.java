package com.jcerdar.gestorfutbol.service.model;

import java.io.Serializable;
import java.util.Date;


public class DelegatDTO implements Serializable {
    private  Long id;
    private  Long campanya;
    private Long equip;
    private  String nom;
    private  String llinatge1;
    private  String llinatge2;
    private  Date dataNaixement;
    private  Boolean esDelegatCamp;

    public DelegatDTO() {

    }

    public DelegatDTO(Long id, Long campanya, Long categoria, String nom, String llinatge1, String llinatge2, Date dataNaixement, Boolean esDelegatCamp) {
        this.id = id;
        this.campanya = campanya;
        this.equip = categoria;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.dataNaixement = dataNaixement;
        this.esDelegatCamp = esDelegatCamp;
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

    public Long getEquip() {
        return equip;
    }

    public void setEquip(Long equip) {
        this.equip = equip;
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

    public Boolean getEsDelegatCamp() {
        return esDelegatCamp;
    }

    public void setEsDelegatCamp(Boolean esDelegatCamp) {
        this.esDelegatCamp = esDelegatCamp;
    }
}
