package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import com.jcerdar.gestorfutbol.persistence.model.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariDao extends JpaRepository<Usuari, Long> {

}
