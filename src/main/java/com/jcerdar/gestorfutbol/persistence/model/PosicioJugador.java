package com.jcerdar.gestorfutbol.persistence.model;


import jakarta.persistence.*;

@Entity
@Table(name = "GF_POS_JUGADOR")
public class PosicioJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POS_TEN", nullable = false)
    private Tenant tenant;

    @Column(name = "POS_NOM", nullable = false)
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


}
