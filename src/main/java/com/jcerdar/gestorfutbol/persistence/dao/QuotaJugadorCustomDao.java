package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.QuotaJugador;
import com.jcerdar.gestorfutbol.persistence.model.Soci;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuotaJugadorCustomDao {

    Page<QuotaJugador> buscarConFiltros(Filtre request);

    List<QuotaJugador> buscarConFiltrosAll(Filtre request);
}
