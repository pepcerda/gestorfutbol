package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_ENTRENADOR")
@DiscriminatorValue("GF_ENTRENADOR")
public class Entrenador extends MembrePlantilla{

    @Column(name = "ENT_CARREC")
    private String carrec;

    public String getCarrec() {
        return carrec;
    }

    public void setCarrec(String carrec) {
        this.carrec = carrec;
    }
}
