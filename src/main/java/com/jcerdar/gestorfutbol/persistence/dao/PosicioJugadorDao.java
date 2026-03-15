package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.PosicioJugador;
import com.jcerdar.gestorfutbol.persistence.model.Proveidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosicioJugadorDao extends JpaRepository<PosicioJugador, Long> {

    Page<PosicioJugador> findAll(Pageable pageable);

    Page<PosicioJugador> findByTenantId(Long tenantId, Pageable pageable);

    List<PosicioJugador> findAllByTenantId(Long tenantId);
}
