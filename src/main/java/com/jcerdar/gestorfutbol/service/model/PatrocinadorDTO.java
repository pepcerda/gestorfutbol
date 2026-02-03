package com.jcerdar.gestorfutbol.service.model;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PatrocinadorDTO {

    private Long id;

    private Long campanya;

    private String cif;

    private String nom;

    private Long donacio;

    private Date dataDonacio;

    private String observacio;

    private String estatPagament;

    private String logo;

    public PatrocinadorDTO() {

    }

    // Constructor
    public PatrocinadorDTO(Long id, Long campanya, String cif, String nom, Long donacio, Date dataDonacio, String observacio, String estatPagament) {
        this.id = id;
        this.campanya = campanya;
        this.cif = cif;
        this.nom = nom;
        this.donacio = donacio;
        this.dataDonacio = dataDonacio;
        this.observacio = observacio;
        this.estatPagament = estatPagament;
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

    public String getDataDonacioFormatada() {
        if (dataDonacio == null) {
            return "";
        }
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataDonacio);
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
