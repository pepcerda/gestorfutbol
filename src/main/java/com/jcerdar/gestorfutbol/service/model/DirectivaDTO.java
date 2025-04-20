package com.jcerdar.gestorfutbol.service.model;
import java.util.Date;
import java.util.List;

public class DirectivaDTO {

    private Long id;

    private Date dataAlta;

    private Date dataBaixa;

    private List<DirectiuDTO> directius;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public List<DirectiuDTO> getDirectius() {
        return directius;
    }

    public void setDirectius(List<DirectiuDTO> directius) {
        this.directius = directius;
    }
}
