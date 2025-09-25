package com.jcerdar.gestorfutbol.service.model;

import java.util.ArrayList;
import java.util.List;

public class MensualitatDTO {

    private Long id;

    private Long campanya;

    private Integer any;

    private Integer mes;  // 1 = gener, 12 = desembre

    private List<NominaDTO> nomines = new ArrayList<>();

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

    public List<NominaDTO> getNomines() {
        return nomines;
    }

    public void setNomines(List<NominaDTO> nomines) {
        this.nomines = nomines;
    }

}
