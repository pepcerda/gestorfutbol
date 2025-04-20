package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_ROLDIR")
public class RolDirectiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RDI_ID", nullable = false)
    private Long id;

    @Column(name = "RDI_ROL", nullable = false)
    private String rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
