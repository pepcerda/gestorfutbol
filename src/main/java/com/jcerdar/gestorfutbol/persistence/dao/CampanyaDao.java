package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Campanya;

import java.util.List;

public interface CampanyaDao extends JpaRepository<Campanya, Long> {

    Page<Campanya> findAllByTenantId(Long tenantId, Pageable pageable);

    List<Campanya> findAllByTenantId(Long tenantId, Sort sort);

}
