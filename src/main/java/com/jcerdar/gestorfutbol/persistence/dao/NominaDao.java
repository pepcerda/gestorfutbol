package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Nomina;

public interface NominaDao extends JpaRepository<Nomina, Long> {

}
