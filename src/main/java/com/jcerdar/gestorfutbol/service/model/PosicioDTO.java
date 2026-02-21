package com.jcerdar.gestorfutbol.service.model;

public class PosicioDTO {

    private Long id;
    private Long tenantId;
    private String valor;

    public PosicioDTO() {
    }

    public PosicioDTO(Long id, Long tenantId, String valor) {
        this.id = id;
        this.tenantId = tenantId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}