package com.jcerdar.gestorfutbol.service.model;

import java.math.BigDecimal;
import java.util.Date;

public class NominaDTO {

    private Long id;

    private Long mensualitat;

    private Long membre;

    private BigDecimal quantitat;

    private Date dataPagament;

    private String estatPagament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMensualitat() {
        return mensualitat;
    }

    public void setMensualitat(Long mensualitat) {
        this.mensualitat = mensualitat;
    }

    public Long getMembre() {
        return membre;
    }

    public void setMembre(Long membre) {
        this.membre = membre;
    }

    public BigDecimal getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(BigDecimal quantitat) {
        this.quantitat = quantitat;
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

}
