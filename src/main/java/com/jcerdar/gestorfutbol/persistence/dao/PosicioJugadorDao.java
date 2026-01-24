package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.PosicioJugador;
import com.jcerdar.gestorfutbol.persistence.model.Proveidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosicioJugadorDao extends JpaRepository<PosicioJugador, Long> {

    Page<PosicioJugador> findAll(Pageable pageable);
}
