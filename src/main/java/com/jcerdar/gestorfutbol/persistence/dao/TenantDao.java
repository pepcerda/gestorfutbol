package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Campanya;
import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDao extends JpaRepository<Tenant, Long> {

}
