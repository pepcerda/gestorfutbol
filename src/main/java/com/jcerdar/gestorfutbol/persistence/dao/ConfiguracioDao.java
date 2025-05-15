package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Configuracio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracioDao extends JpaRepository<Configuracio, Long> {
}
