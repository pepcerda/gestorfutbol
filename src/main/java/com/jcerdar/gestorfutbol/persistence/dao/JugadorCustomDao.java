package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.Jugador;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JugadorCustomDao {

    Page<Jugador> buscarConFiltros(Filtre request);

    List<Jugador> buscarConFiltrosAll(Filtre request);
}
