package com.jcerdar.gestorfutbol.service.model;

import java.math.BigDecimal;

public class EquipDTO {

    private Long id;

    private String nom;

    private BigDecimal quota;

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

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }
}
