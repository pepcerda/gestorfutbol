package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SociDao extends JpaRepository<Soci, Long> {
}
