package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.RolDirectiu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolDirectiuDao extends JpaRepository<RolDirectiu, Long> {

    List<RolDirectiu> findAllByTenantId(Long tenantId);

    Page<RolDirectiu> findAllByTenantId(Long tenantId, Pageable pageable);

}
