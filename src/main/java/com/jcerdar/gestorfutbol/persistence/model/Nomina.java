package com.jcerdar.gestorfutbol.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "GF_NOMINA",
       uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NOM_MEN", "NOM_MEM"})
       })
public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOM_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NOM_MEN", nullable = false)
    private Mensualitat mensualitat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NOM_MEM", nullable = false)
    private MembrePlantilla membre;

    @Column(name = "NOM_QUAN", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantitat;

    @Column(name = "NOM_DATA")
    private Date dataPagament;

    @Enumerated(EnumType.STRING)
    @Column(name = "NOM_ESTAT", nullable = false, length = 20)
    private EstatPagament estatPagament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mensualitat getMensualitat() {
        return mensualitat;
    }

    public void setMensualitat(Mensualitat mensualitat) {
        this.mensualitat = mensualitat;
    }

    public MembrePlantilla getMembre() {
        return membre;
    }

    public void setMembre(MembrePlantilla membre) {
        this.membre = membre;
    }

    public BigDecimal getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(BigDecimal quantitat) {
        this.quantitat = quantitat;
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

    

}
