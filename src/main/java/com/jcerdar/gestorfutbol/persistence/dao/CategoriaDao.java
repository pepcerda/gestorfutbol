package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c where c.campanya.id = :idCampanya")
    List<Categoria> findAllByCampanya(@Param("idCampanya") Long idCampanya);

    @Query("select c from Categoria c where c.campanya.id = :idCampanya")
    Page<Categoria> findByCampanya(@Param("idCampanya") Long idCampanya, Pageable pageable);
}
