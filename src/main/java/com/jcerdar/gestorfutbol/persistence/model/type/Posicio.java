package com.jcerdar.gestorfutbol.persistence.model.type;

public enum Posicio {
    PORTER("1", "Porter"),
    DEFENSA("2", "Defensa"),
    MIGCAMPISTA("3", "Centrocampista"),
    DAVANTER("4", "Delantero");

    private final String valor;
    private final String descripcion;

    Posicio(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
