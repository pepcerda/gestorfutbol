package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrenadorDao extends JpaRepository<Entrenador, Long> {

    @Query("select c from Entrenador c where c.campanya.id = :idCampanya")
    List<Entrenador> findAllByCampanya(@Param("idCampanya") Long idCampanya);

    @Query("select c from Entrenador c where c.campanya.id = :idCampanya and c.equip.id = :idEquip")
    List<Entrenador> findAllByCampanyaAndEquip(@Param("idCampanya") Long idCampanya, @Param("idEquip") Long idEquip);
}