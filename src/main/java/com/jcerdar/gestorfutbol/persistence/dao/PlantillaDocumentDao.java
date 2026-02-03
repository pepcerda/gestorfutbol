package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.PlantillaDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantillaDocumentDao extends JpaRepository<PlantillaDocument, Long> {

    Optional<PlantillaDocument> findByCodiAndIdioma(String codi, String idioma);

    Optional<PlantillaDocument> findByCodi(String codi);

}
