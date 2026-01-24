package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GF_EQUIP")
public class Equip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQP_ID", nullable = false)
    private Long id;

    @Column(name = "EQP_NOM", nullable = false)
    private String nom;

    @Column(name = "EQP_QUOTA", nullable = false)
    private BigDecimal quota;

    @OneToMany(mappedBy = "equip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Mensualitat> mensualitats = new ArrayList<>();

    @OneToMany(mappedBy = "equip", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MembrePlantilla> membresPlantilla = new ArrayList<>();

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

    public List<Mensualitat> getMensualitats() {
        return mensualitats;
    }

    public void setMensualitats(List<Mensualitat> mensualitats) {
        this.mensualitats = mensualitats;
    }

    public List<MembrePlantilla> getMembresPlantilla() {
        return membresPlantilla;
    }

    public void setMembresPlantilla(List<MembrePlantilla> membresPlantilla) {
        this.membresPlantilla = membresPlantilla;
    }
}
