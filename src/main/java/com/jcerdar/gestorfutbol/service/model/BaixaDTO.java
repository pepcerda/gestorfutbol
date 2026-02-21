package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;

public class BaixaDTO {

    private Long directivaId;

    private Date dataBaixa;

    public Long getDirectivaId() {
        return directivaId;
    }

    public void setDirectivaId(Long directivaId) {
        this.directivaId = directivaId;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
}
