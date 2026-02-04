package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "GF_PLANTILLA_DOCUMENTO")
public class PlantillaDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLA_ID", nullable = false)
    private Long id;

    @Column(name = "PLA_COD", nullable = false, length = 100)
    private String codi;

    @Column(name = "PLA_NOM", nullable = false, length = 255)
    private String nom;

    @Column(name = "PLA_IDI", nullable = false)
    private String idioma;

    @Column(name = "PLA_HTML", nullable = false, columnDefinition = "text")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String contingutHtml;

    @Column(name = "PLA_CSS", columnDefinition = "text")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String contingutCss;

    @Column(name = "PLA_CREA", nullable = false, updatable = false)
    private Date dataCreacio;

    @Column(name = "PLA_ACT", nullable = false)
    private Date dataActualitzacio;

    @PrePersist
    protected void onCreate() {
        this.dataCreacio = new Date();
        this.dataActualitzacio = this.dataCreacio;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataActualitzacio = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getContingutHtml() {
        return contingutHtml;
    }

    public void setContingutHtml(String contingutHtml) {
        this.contingutHtml = contingutHtml;
    }

    public String getContingutCss() {
        return contingutCss;
    }

    public void setContingutCss(String contingutCss) {
        this.contingutCss = contingutCss;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataActualitzacio() {
        return dataActualitzacio;
    }

    public void setDataActualitzacio(Date dataActualitzacio) {
        this.dataActualitzacio = dataActualitzacio;
    }
}
