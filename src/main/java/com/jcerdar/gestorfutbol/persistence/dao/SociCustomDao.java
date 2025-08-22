package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SociCustomDao {

    Page<Soci> buscarConFiltros(Filtre request);

    List<Soci> buscarConFiltrosAll(Filtre request);
}
