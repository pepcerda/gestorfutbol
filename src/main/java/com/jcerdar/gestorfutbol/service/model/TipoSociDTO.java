package com.jcerdar.gestorfutbol.service.model;

public class TipoSociDTO {

    private Long id;

    private Long campanya;

    private String nom;

    private Long cuota;

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

    public Long getCuota() {
        return cuota;
    }

    public void setCuota(Long cuota) {
        this.cuota = cuota;
    }
}
