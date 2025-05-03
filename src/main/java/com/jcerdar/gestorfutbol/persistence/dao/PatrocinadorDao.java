package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatrocinadorDao extends JpaRepository<Patrocinador, Long> {

    @Query("select c from Patrocinador c where c.campanya.id = :idCampanya")
    Page<Patrocinador> findAllByCampanya(@Param("idCampanya") Long idCampanya, Pageable pageable);

    List<Patrocinador> findAllByCampanyaId(Long campanyaId);
}
