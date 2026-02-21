package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_USUARI_TENANT")
@IdClass(UsuariTenantId.class)
public class UsuariTenant {

    @Id
    @ManyToOne
    @JoinColumn(name = "UT_USU_ID", nullable = false)
    private Usuari usuari;

    @Id
    @ManyToOne
    @JoinColumn(name = "UT_TEN_ID", nullable = false)
    private Tenant tenant;


    @Column(name = "UT_ROL", nullable = false)
    private String rol;

    @Column(name = "UT_ACTIU", nullable = false)
    private Boolean actiu;
}
