package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;

public class TenantDTO {

    private Long id;

    private PlaSuscripcioDTO plaSuscripcio;

    private String nom;

    private Boolean actiu;

    private Date dataCreacio;

    private Date dataBaixa;

    private UsuariTenantDTO usuariAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaSuscripcioDTO getPlaSuscripcio() {
        return plaSuscripcio;
    }

    public void setPlaSuscripcio(PlaSuscripcioDTO plaSuscripcio) {
        this.plaSuscripcio = plaSuscripcio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public UsuariTenantDTO getUsuariAdmin() {
        return usuariAdmin;
    }

    public void setUsuariAdmin(UsuariTenantDTO usuariAdmin) {
        this.usuariAdmin = usuariAdmin;
    }
    

}
