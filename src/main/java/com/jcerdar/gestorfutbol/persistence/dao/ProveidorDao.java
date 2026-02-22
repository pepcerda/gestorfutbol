package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Proveidor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveidorDao extends JpaRepository<Proveidor, Long> {

    Page<Proveidor> findAll(Pageable pageable);

    List<Proveidor> findAllByTenantId(Long tenantId);

    Page<Proveidor> findAllByTenantId(Long tenantId, Pageable pageable);
}
