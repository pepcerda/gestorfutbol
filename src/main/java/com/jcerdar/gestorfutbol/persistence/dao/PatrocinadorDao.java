package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrocinadorDao extends JpaRepository<Patrocinador, Long> {
}
