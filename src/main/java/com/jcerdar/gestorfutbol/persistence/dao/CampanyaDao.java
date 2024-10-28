package com.jcerdar.gestorfutbol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jcerdar.gestorfutbol.persistence.model.Campanya;

@Repository
public interface CampanyaDao extends JpaRepository<Campanya, Long>{

    

}
