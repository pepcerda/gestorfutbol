package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_ROLDIR")
public class RolDirectiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RDI_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RDI_TEN", nullable = false)
    private Tenant  tenant;

    @Column(name = "RDI_ROL", nullable = false)
    private String rol;

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
