package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Mensualitat;

public interface MensualitatDao extends JpaRepository<Mensualitat, Long> {

}
