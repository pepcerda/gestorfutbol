package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaixaFixaDao extends JpaRepository<CaixaFixa, Long> {

    @Query("select c from CaixaFixa c where c.campanya.id = :idCampanya")
    Page<CaixaFixa> findAllByCampanya(@Param("idCampanya") Long idCampanya, Pageable pageable);
}
