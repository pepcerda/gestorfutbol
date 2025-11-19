package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Equip;
import com.jcerdar.gestorfutbol.service.model.EquipDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipDao extends JpaRepository<Equip, Long> {

    @Query("""
                select e
                from Categoria c
                join c.equips e
                where c.campanya.id = :campanyaId
            """)
    List<Equip> findAllByCampanyaId(Long campanyaId);
}
