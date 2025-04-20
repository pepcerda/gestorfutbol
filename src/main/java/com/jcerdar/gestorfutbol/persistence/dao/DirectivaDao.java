package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.Directiva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectivaDao extends JpaRepository<Directiva, Long> {

    Directiva findDirectivaByDataBaixaIsNull();
}
