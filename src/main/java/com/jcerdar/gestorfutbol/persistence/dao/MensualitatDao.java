package com.jcerdar.gestorfutbol.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Mensualitat;

public interface MensualitatDao extends JpaRepository<Mensualitat, Long> {

    List<Mensualitat> findAllByCampanyaId(Long idCampanya);

}
