package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Directiva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectivaDao extends JpaRepository<Directiva, Long> {

    Directiva findDirectivaByDataBaixaIsNull();

    List<Directiva> findAllByOrderByDataAltaAsc();
}
