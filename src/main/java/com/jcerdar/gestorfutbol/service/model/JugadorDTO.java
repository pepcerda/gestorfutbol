package com.jcerdar.gestorfutbol.service.model;
import java.util.Date;

public class JugadorDTO {
    private Long id;
    private Long campanya;
    private Long categoria;
    private String nom;
    private String llinatge1;
    private String llinatge2;
    private Date dataNaixement;
    private PosicioDTO posicio;

    public JugadorDTO() {

    }

    public JugadorDTO(Long id, Long campanya, Long categoria, String nom, String llinatge1, String llinatge2, Date dataNaixement, PosicioDTO posicio) {
        this.id = id;
        this.campanya = campanya;
        this.categoria = categoria;
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.dataNaixement = dataNaixement;
        this.posicio = posicio;
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

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatge1() {
        return llinatge1;
    }

    public void setLlinatge1(String llinatge1) {
        this.llinatge1 = llinatge1;
    }

    public String getLlinatge2() {
        return llinatge2;
    }

    public void setLlinatge2(String llinatge2) {
        this.llinatge2 = llinatge2;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public PosicioDTO getPosicio() {
        return posicio;
    }

    public void setPosicio(PosicioDTO posicio) {
        this.posicio = posicio;
    }
}
