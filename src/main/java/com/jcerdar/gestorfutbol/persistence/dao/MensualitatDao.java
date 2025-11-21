package com.jcerdar.gestorfutbol.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Mensualitat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MensualitatDao extends JpaRepository<Mensualitat, Long> {

    @Query("""
                select distinct m
                from Mensualitat m
                where m.equip.id = :equipId
                and m.campanya.id = :campanyaId
            """)
    List<Mensualitat> findMensualitatsByEquipAndCampanya(Long equipId, Long campanyaId);

}
