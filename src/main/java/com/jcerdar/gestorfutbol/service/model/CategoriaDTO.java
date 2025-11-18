package com.jcerdar.gestorfutbol.service.model;

import java.util.List;

public class CategoriaDTO {

    private Long id;

    private String nom;

    private Long campanya;

    private List<EquipDTO> equips;

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

    public Long getCampanya() {
        return campanya;
    }

    public void setCampanya(Long campanya) {
        this.campanya = campanya;
    }

    public List<EquipDTO> getEquips() {
        return equips;
    }

    public void setEquips(List<EquipDTO> equips) {
        this.equips = equips;
    }
}
