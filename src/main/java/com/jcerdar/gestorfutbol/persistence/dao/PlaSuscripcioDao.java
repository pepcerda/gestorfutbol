package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.PlaSuscripcio;
import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaSuscripcioDao extends JpaRepository<PlaSuscripcio, Long> {

}
