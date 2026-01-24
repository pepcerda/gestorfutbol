package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_JUGADOR")
@DiscriminatorValue("GF_JUGADOR")
public class Jugador extends MembrePlantilla{


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "JUG_POSICIO", nullable = false)
    private PosicioJugador posicio;

    public PosicioJugador getPosicio() {
        return posicio;
    }

    public void setPosicio(PosicioJugador posicio) {
        this.posicio = posicio;
    }

}
