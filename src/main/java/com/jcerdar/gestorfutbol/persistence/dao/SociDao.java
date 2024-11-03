package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SociDao extends JpaRepository<Soci, Long> {

    @Query("select c from Soci c where c.campanya.id = :idCampanya")
    Page<Soci> findAllByCampanya(@Param("idCampanya") Long idCampanya, Pageable pageable);
}
