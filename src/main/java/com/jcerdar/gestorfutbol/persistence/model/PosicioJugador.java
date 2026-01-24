package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;

@Entity
@Table(name = "GF_POS_JUGADOR")
public class PosicioJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_ID", nullable = false)
    private Long id;

    @Column(name = "POS_NOM", nullable = false)
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


}
