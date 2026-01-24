package com.jcerdar.gestorfutbol.service.model;

public class ExplotacioQuotesDTO {

    private Long totalRecaptat;

    private Long previsioRecaptacioPerEquip;

    private Long totalRecaptatPerEquip;

    public Long getTotalRecaptat() {
        return totalRecaptat;
    }

    public void setTotalRecaptat(Long totalRecaptat) {
        this.totalRecaptat = totalRecaptat;
    }

    public Long getPrevisioRecaptacioPerEquip() {
        return previsioRecaptacioPerEquip;
    }

    public void setPrevisioRecaptacioPerEquip(Long previsioRecaptacioPerEquip) {
        this.previsioRecaptacioPerEquip = previsioRecaptacioPerEquip;
    }

    public Long getTotalRecaptatPerEquip() {
        return totalRecaptatPerEquip;
    }

    public void setTotalRecaptatPerEquip(Long totalRecaptatPerEquip) {
        this.totalRecaptatPerEquip = totalRecaptatPerEquip;
    }

}
