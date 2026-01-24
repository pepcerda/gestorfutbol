package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Contacte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContacteDao extends JpaRepository<Contacte, Long> {
}
