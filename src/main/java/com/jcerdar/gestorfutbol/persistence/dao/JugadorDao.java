package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JugadorDao extends JpaRepository<Jugador, Long>, JugadorCustomDao{

    @Query("select c from Jugador c where c.campanya.id = :idCampanya")
    List<Jugador> findAllByCampanya(@Param("idCampanya") Long idCampanya);

    @Query("select c from Jugador c where c.campanya.id = :idCampanya and c.equip.id = :idEquip")
    List<Jugador> findAllByCampanyaAndEquip(@Param("idCampanya") Long idCampanya, @Param("idEquip") Long idEquip);
}