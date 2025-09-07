package com.jcerdar.gestorfutbol.persistence.model;

import com.jcerdar.gestorfutbol.persistence.model.type.Posicio;
import jakarta.persistence.*;

@Entity
@Table(name = "GF_JUGADOR")
@DiscriminatorValue("GF_JUGADOR")
public class Jugador extends MembrePlantilla{

    @Enumerated(EnumType.STRING)
    @Column(name = "JUG_POSICIO", nullable = false)
    private Posicio posicio;

    public Posicio getPosicio() {
        return posicio;
    }

    public void setPosicio(Posicio posicio) {
        this.posicio = posicio;
    }

}
