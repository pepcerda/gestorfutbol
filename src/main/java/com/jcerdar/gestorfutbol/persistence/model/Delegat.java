package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_DELEGAT")
@DiscriminatorValue("GF_DELEGAT")
public class Delegat extends MembrePlantilla {

    @Column(name = "DEL_DELCAMP")
    private Boolean esDelegatCamp = false;

    public Boolean getEsDelegatCamp() {
        return esDelegatCamp;
    }

    public void setEsDelegatCamp(Boolean esDelegatCamp) {
        this.esDelegatCamp = esDelegatCamp;
    }
}
