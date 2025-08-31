package com.jcerdar.gestorfutbol.service.model;

public class ExplotacioPatrocinadorsDTO {

    private Long previsioRecaptacio;

    private Long totalRecaptat;

    private Long totalPatrocinadors;

    public Long getPrevisioRecaptacio() {
        return previsioRecaptacio;
    }

    public void setPrevisioRecaptacio(Long previsioRecaptacio) {
        this.previsioRecaptacio = previsioRecaptacio;
    }

    public Long getTotalRecaptat() {
        return totalRecaptat;
    }

    public void setTotalRecaptat(Long totalRecaptat) {
        this.totalRecaptat = totalRecaptat;
    }

    public Long getTotalPatrocinadors() {
        return totalPatrocinadors;
    }

    public void setTotalPatrocinadors(Long totalPatrocinadors) {
        this.totalPatrocinadors = totalPatrocinadors;
    }
}
