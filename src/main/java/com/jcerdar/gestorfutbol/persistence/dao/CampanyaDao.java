package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcerdar.gestorfutbol.persistence.model.Campanya;

public interface CampanyaDao extends JpaRepository<Campanya, Long> {

    Campanya findCampanyaByActivaIsTrue();

}
