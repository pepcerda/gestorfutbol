package com.jcerdar.gestorfutbol.service.model;

import java.math.BigDecimal;
import java.util.Date;

public class FacturaDTO {

    private Long id;

    private Long campanya;

    private ProveidorDTO proveidor;

    private CategoriaDespesaDTO categoriaDespesa;

    private String concepte;

    private BigDecimal despesa;

    private String document;

    private Date dataFactura;

    private String estatPagament;

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

    public ProveidorDTO getProveidor() {
        return proveidor;
    }

    public void setProveidor(ProveidorDTO proveidor) {
        this.proveidor = proveidor;
    }

    public CategoriaDespesaDTO getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(CategoriaDespesaDTO categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getConcepte() {
        return concepte;
    }

    public void setConcepte(String concepte) {
        this.concepte = concepte;
    }

    public BigDecimal getDespesa() {
        return despesa;
    }

    public void setDespesa(BigDecimal despesa) {
        this.despesa = despesa;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public String getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(String estatPagament) {
        this.estatPagament = estatPagament;
    }
}
