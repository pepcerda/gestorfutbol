package com.jcerdar.gestorfutbol.persistence.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GF_CAMPANYA")
public class Campanya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAM_ID", nullable = false)
    private Long id;

    @Column(name = "CAM_ANY", nullable = false)
    private Date any;

    @Column(name = "CAM_TITOL", nullable = false)
    private String titol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAny() {
        return any;
    }

    public void setAny(Date any) {
        this.any = any;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    

}
