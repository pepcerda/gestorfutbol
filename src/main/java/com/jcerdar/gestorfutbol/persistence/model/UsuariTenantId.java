package com.jcerdar.gestorfutbol.persistence.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuariTenantId implements Serializable {

    private Long usuari;

    private Long tenant;

    public Long getUsuari() {
        return usuari;
    }

    public void setUsuari(Long usuari) {
        this.usuari = usuari;
    }

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariTenantId that = (UsuariTenantId) o;
        return Objects.equals(usuari, that.usuari) && Objects.equals(tenant, that.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuari, tenant);
    }
}
