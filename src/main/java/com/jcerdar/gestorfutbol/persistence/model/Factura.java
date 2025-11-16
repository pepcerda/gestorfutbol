package com.jcerdar.gestorfutbol.persistence.model;

import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "GF_FACTURA")
public class Factura {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAC_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FAC_CAM", nullable = false)
    private Campanya campanya;

    @ManyToOne
    @JoinColumn(name = "FAC_PROV", nullable = false)
    private Proveidor proveidor;

    @ManyToOne
    @JoinColumn(name = "FAC_CAT", nullable = false)
    private CategoriaDespesa categoriaDespesa;

    @Column(name = "FAC_CONCEPTE")
    private String concepte;

    @Column(name = "FAC_DES", nullable = false, precision = 15, scale = 2)
    private BigDecimal despesa;

    @Column(name = "FAC_DOC")
    private String document;

    @Column(name = "FAC_DATA", nullable = false)
    private Date dataFactura;

    @Enumerated(EnumType.STRING)
    @Column(name= "FAC_ESTAT",nullable = false, length = 20)
    private EstatPagament estatPagament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }

    public Proveidor getProveidor() {
        return proveidor;
    }

    public void setProveidor(Proveidor proveidor) {
        this.proveidor = proveidor;
    }

    public BigDecimal getDespesa() {
        return despesa;
    }

    public void setDespesa(BigDecimal despesa) {
        this.despesa = despesa;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public EstatPagament getEstatPagament() {
        return estatPagament;
    }

    public void setEstatPagament(EstatPagament estatPagament) {
        this.estatPagament = estatPagament;
    }

    public CategoriaDespesa getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getConcepte() {
        return concepte;
    }

    public void setConcepte(String concepte) {
        this.concepte = concepte;
    }
}
