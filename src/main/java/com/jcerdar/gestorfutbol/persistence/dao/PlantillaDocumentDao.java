package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.persistence.model.PlantillaDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantillaDocumentDao extends JpaRepository<PlantillaDocument, Long> {

    List<PlantillaDocument> findAllByTenantId(Long tenantId);

    Optional<PlantillaDocument> findByCodiAndIdioma(String codi, String idioma);

    Optional<PlantillaDocument> findByCodi(String codi);

    Optional<PlantillaDocument> findByCodiAndTenantId(String codi, Long tenantId);

}
