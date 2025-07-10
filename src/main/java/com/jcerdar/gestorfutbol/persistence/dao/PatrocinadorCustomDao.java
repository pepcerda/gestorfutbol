package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.Patrocinador;
import org.springframework.data.domain.Page;

public interface PatrocinadorCustomDao {

    Page<Patrocinador> buscarConFiltros(Filtre request);
}
