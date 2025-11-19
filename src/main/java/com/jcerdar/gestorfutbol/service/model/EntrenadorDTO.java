package com.jcerdar.gestorfutbol.service.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class EntrenadorDTO implements Serializable {
    private  Long id;
    private  Long campanya;
    private  Long equip;
    private  String nom;
    private  String llinatge1;
    private  String llinatge2;
    private Date dataNaixement;
    private  String carrec;

    public EntrenadorDTO() {

    }

    public EntrenadorDTO(Long id, Long campanya, Long equip, String nom, String llinatge1, String llinatge2, Date dataNaixement, String carrec) {
        this.id = id;
        this.campanya = campanya;
        this.equip = equip;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.dataNaixement = dataNaixement;
        this.carrec = carrec;
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

    public String getCarrec() {
        return carrec;
    }

    public void setCarrec(String carrec) {
        this.carrec = carrec;
    }
}
