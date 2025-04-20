package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GF_DIRECTIVA")
public class Directiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIR_ID", nullable = false)
    private Long id;

    @Column(name = "DIR_ALTA", nullable = false)
    private Date dataAlta;

    @Column(name = "DIR_BAIXA")
    private Date dataBaixa;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Directiu> directius;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public List<Directiu> getDirectius() {
        return directius;
    }

    public void setDirectius(List<Directiu> directius) {
        this.directius = directius;
        directius.forEach(d -> d.setDirectiva(this));
    }
}
