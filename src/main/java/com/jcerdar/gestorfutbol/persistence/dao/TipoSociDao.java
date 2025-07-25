package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Soci;
import com.jcerdar.gestorfutbol.persistence.model.TipoSoci;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoSociDao extends JpaRepository<TipoSoci, Long> {

    @Query("select c from TipoSoci c where c.campanya.id = :idCampanya")
    List<TipoSoci> findAllByCampanya(@Param("idCampanya") Long idCampanya);

}
