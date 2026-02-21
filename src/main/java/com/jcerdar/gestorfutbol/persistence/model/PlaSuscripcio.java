package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "GF_PLA_SUSCRIPCIO")
public class PlaSuscripcio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLA_ID", nullable = false)
    private Long id;

    @Column(name = "PLA_TIPUS", nullable = false)
    private String tipus;

    @Column(name = "PLA_CUOTA", nullable = false)
    private BigDecimal cuota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
}
