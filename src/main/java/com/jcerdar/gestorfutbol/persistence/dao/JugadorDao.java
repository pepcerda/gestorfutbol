package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Entrenador;
import com.jcerdar.gestorfutbol.persistence.model.Jugador;
import com.jcerdar.gestorfutbol.persistence.model.TipoSoci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JugadorDao extends JpaRepository<Jugador, Long> {

    @Query("select c from Jugador c where c.campanya.id = :idCampanya")
    List<Jugador> findAllByCampanya(@Param("idCampanya") Long idCampanya);

    @Query("select c from Jugador c where c.campanya.id = :idCampanya and c.categoria.id = :idCategoria")
    List<Jugador> findAllByCampanyaAndCategoria(@Param("idCampanya") Long idCampanya, @Param("idCategoria") Long idCategoria);
}