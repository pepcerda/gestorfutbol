package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.RolUsuariTenant;
import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolUsuariTenantDao extends JpaRepository<RolUsuariTenant, Long> {


}
