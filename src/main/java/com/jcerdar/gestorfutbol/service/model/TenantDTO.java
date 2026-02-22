package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;

public class TenantDTO {

    private Long id;

    private PlaSuscripcioDTO plaSuscripcio;

    private String nom;

    private String estat;

    private Date dataCreacio;

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

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public UsuariTenantDTO getUsuariAdmin() {
        return usuariAdmin;
    }

    public void setUsuariAdmin(UsuariTenantDTO usuariAdmin) {
        this.usuariAdmin = usuariAdmin;
    }
    

}
