package com.jcerdar.gestorfutbol.service.model;

public class PosicioDTO {
    private String valor;
    private String descripcion;
    private String name;

    public PosicioDTO() {}

    public PosicioDTO(String valor, String descripcion, String name) {
        this.valor = valor;
        this.descripcion = descripcion;
        this.name = name;
    }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}