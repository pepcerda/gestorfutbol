package com.jcerdar.gestorfutbol.service.model;

public class ExplotacioSocisDTO {

    private Long totalRecaptat;

    private Long previsioRecaptacio;

    private Long totalSocis;


    public Long getTotalRecaptat() {
        return totalRecaptat;
    }

    public void setTotalRecaptat(Long totalRecaptat) {
        this.totalRecaptat = totalRecaptat;
    }

    public Long getPrevisioRecaptacio() {
        return previsioRecaptacio;
    }

    public void setPrevisioRecaptacio(Long previsioRecaptacio) {
        this.previsioRecaptacio = previsioRecaptacio;
    }

    public Long getTotalSocis() {
        return totalSocis;
    }

    public void setTotalSocis(Long totalSocis) {
        this.totalSocis = totalSocis;
    }
}
