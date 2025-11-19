package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GF_CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAT_CAM", nullable = false)
    private Campanya campanya;

    @Column(name = "CAT_NOM", nullable = false)
    private String nom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "EQP_CAT")
    private List<Equip> equips;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }
}
