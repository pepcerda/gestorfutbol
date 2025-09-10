package com.jcerdar.gestorfutbol.persistence.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(
    name = "GF_MENSUALITAT",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"MEN_ID", "MEN_ANY", "MEN_MES"})
    }
)
public class Mensualitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEN_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEN_CAM", nullable = false)
    private Campanya campanya;

    @Column(name = "MEN_ANY", nullable = false)
    private Integer any;

    @Column(name = "MEN_MES", nullable = false)
    private Integer mes;  // 1 = gener, 12 = desembre

    @OneToMany(mappedBy = "mensualitat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nomina> nomines = new ArrayList<>();

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

    public Integer getAny() {
        return any;
    }

    public void setAny(Integer any) {
        this.any = any;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public List<Nomina> getNomines() {
        return nomines;
    }

    public void setNomines(List<Nomina> nomines) {
        this.nomines = nomines;
    }

    

    

}
