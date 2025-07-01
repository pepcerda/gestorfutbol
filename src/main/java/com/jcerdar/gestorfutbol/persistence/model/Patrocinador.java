package com.jcerdar.gestorfutbol.persistence.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_PATROCINADOR")
public class Patrocinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAT_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAT_CAM", nullable = false)
    private Campanya campanya;

    @Column(name = "PAT_CIF", nullable = false)
    private String cif; 

    @Column(name = "PAT_NOM", nullable = false)
    private String nom; 

    @Column(name = "PAT_DONACIO", nullable = false)
    private Long donacio;

    @Column(name = "PAT_DATADON", nullable = false)
    private Date dataDonacio;

    @Column(name = "PAT_OBS", length = 1024)
    private String observacio;

    //Estat pagament. Possibles valors "P" (Pagat) o "D" (Pendent pagament)
    @Column(name = "PAT_ESTPAG", length = 1, nullable = false)
    private String estatPagament;

    @Column(name = "PAT_LOGO")
    private String logo;

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

    public String getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(String estatPagament) {
        this.estatPagament = estatPagament;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
