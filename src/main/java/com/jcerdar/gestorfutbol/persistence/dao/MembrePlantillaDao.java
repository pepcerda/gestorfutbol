package com.jcerdar.gestorfutbol.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.MembrePlantilla;

public interface MembrePlantillaDao extends JpaRepository<MembrePlantilla, Long> {

    List<MembrePlantilla> findAllByCampanyaId(Long idCampanya);

}
