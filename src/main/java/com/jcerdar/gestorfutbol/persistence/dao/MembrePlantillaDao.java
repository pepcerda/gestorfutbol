package com.jcerdar.gestorfutbol.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.MembrePlantilla;

public interface MembrePlantillaDao extends JpaRepository<MembrePlantilla, Long> {

    List<MembrePlantilla> findAllByCampanyaIdAndEquipId(Long idCampanya, Long idEquip);

    List<MembrePlantilla> findAllByCampanyaIdAndIdInAndEquipId(Long idCampanya, List<Long> ids, Long idEquip);

}
