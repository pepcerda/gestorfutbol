package com.jcerdar.gestorfutbol.service.model;

import java.util.List;

public class ProveidorDTO {

    private Long id;

    private String nom;

    private String nomComercial;

    private String cif;

    private List<ContacteProveidorDTO> contactes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<ContacteProveidorDTO> getContactes() {
        return contactes;
    }

    public void setContactes(List<ContacteProveidorDTO> contactes) {
        this.contactes = contactes;
    }
}
