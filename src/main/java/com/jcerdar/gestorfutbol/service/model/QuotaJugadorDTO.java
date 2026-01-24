package com.jcerdar.gestorfutbol.service.model;

import java.math.BigDecimal;
import java.util.Date;

public class QuotaJugadorDTO {

    private Long id;

    private JugadorDTO jugador;

    private Date dataPagament;

    private String estatPagament;

    private Boolean excepcio;

    private BigDecimal quantitat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JugadorDTO getJugador() {
        return jugador;
    }

    public void setJugador(JugadorDTO jugador) {
        this.jugador = jugador;
    }

    public Date getDataPagament() {
        return dataPagament;
    }

    public void setDataPagament(Date dataPagament) {
        this.dataPagament = dataPagament;
    }

    public String getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(String estatPagament) {
        this.estatPagament = estatPagament;
    }

    public Boolean getExcepcio() {
        return excepcio;
    }

    public void setExcepcio(Boolean excepcio) {
        this.excepcio = excepcio;
    }

    public BigDecimal getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(BigDecimal quantitat) {
        this.quantitat = quantitat;
    }


}

