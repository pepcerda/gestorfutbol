package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Usuari;
import com.jcerdar.gestorfutbol.persistence.model.UsuariTenant;
import com.jcerdar.gestorfutbol.persistence.model.UsuariTenantId;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariTenantDao extends JpaRepository<UsuariTenant, UsuariTenantId> {

    Optional<UsuariTenant> findByUsuariIdAndTenantId(Long usuariId, Long tenantId);

    Page<UsuariTenant> findByTenantId(Long tenantId, Pageable pageable);

    Page<UsuariTenant> findByUsuariId(Long usuariId, Pageable pageable);

}
