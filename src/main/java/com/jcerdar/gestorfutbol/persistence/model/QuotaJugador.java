package com.jcerdar.gestorfutbol.persistence.model;

import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GF_QUOTA_JUGADOR")
public class QuotaJugador{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUO_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "QUO_JUG", nullable = false)
    private Jugador jugador;

    @Column(name = "QUO_DATA")
    private Date dataPagament;

    @Enumerated(EnumType.STRING)
    @Column(name = "QUO_ESTAT", nullable = false)
    private EstatPagament estatPagament = EstatPagament.PENDENT;

    @Column(name = "QUO_EXEP")
    private Boolean exepcio = false;

    @Column(name = "QUO_QUANT")
    private BigDecimal quantitat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Date getDataPagament() {
        return dataPagament;
    }

    public void setDataPagament(Date dataPagament) {
        this.dataPagament = dataPagament;
    }

    public EstatPagament getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(EstatPagament estatPagament) {
        this.estatPagament = estatPagament;
    }

    public Boolean getExepcio() {
        return exepcio;
    }

    public void setExepcio(Boolean exepcio) {
        this.exepcio = exepcio;
    }

    public BigDecimal getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(BigDecimal quantitat) {
        this.quantitat = quantitat;
    }

}
