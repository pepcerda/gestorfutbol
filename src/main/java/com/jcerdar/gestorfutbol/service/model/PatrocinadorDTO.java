package com.jcerdar.gestorfutbol.service.model;
import com.jcerdar.gestorfutbol.persistence.model.Campanya;

import java.util.Date;


public class PatrocinadorDTO {

    private Long id;

    private Long campanya;

    private String cif;

    private String nom;

    private Long donacio;

    private Date dataDonacio;

    private String observacio;

    public PatrocinadorDTO() {

    }

    // Constructor
    public PatrocinadorDTO(Long id, Long campanya, String cif, String nom, Long donacio, Date dataDonacio, String observacio) {
        this.id = id;
        this.campanya = campanya;
        this.cif = cif;
        this.nom = nom;
        this.donacio = donacio;
        this.dataDonacio = dataDonacio;
        this.observacio = observacio;
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

    public Long getDonacio() {
        return donacio;
    }

    public void setDonacio(Long donacio) {
        this.donacio = donacio;
    }

    public Date getDataDonacio() {
        return dataDonacio;
    }

    public void setDataDonacio(Date dataDonacio) {
        this.dataDonacio = dataDonacio;
    }

    public String getObservacio() {
        return observacio;
    }

    public void setObservacio(String observacio) {
        this.observacio = observacio;
    }
}
