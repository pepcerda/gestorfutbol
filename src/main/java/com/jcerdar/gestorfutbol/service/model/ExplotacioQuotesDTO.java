package com.jcerdar.gestorfutbol.service.model;

public class ExplotacioQuotesDTO {

    private Long totalJugadors;

    private Long totalRecaptat;

    private Long previsioQuotes;

    public Long getTotalJugadors() {
        return totalJugadors;
    }

    public void setTotalJugadors(Long totalJugadors) {
        this.totalJugadors = totalJugadors;
    }

    public Long getTotalRecaptat() {
        return totalRecaptat;
    }

    public void setTotalRecaptat(Long totalRecaptat) {
        this.totalRecaptat = totalRecaptat;
    }

    public Long getPrevisioQuotes() {
        return previsioQuotes;
    }

    public void setPrevisioQuotes(Long previsioQuotes) {
        this.previsioQuotes = previsioQuotes;
    }
}
