package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Usuari;
import com.jcerdar.gestorfutbol.persistence.model.UsuariTenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariTenantDao extends JpaRepository<UsuariTenant, Long> {

}
