package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_TIPSOCI")
public class TipoSoci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIS_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TIS_CAM", nullable = false)
    private Campanya campanya;

    @Column(name = "TIS_NOM")
    private String nom;

    @Column(name = "TIS_IMP")
    private Long cuota;

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

    public Long getCuota() {
        return cuota;
    }

    public void setCuota(Long cuota) {
        this.cuota = cuota;
    }
}
