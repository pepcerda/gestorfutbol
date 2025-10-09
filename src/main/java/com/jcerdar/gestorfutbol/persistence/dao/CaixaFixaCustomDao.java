package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CaixaFixaCustomDao {

    Page<CaixaFixa> buscarConFiltros(Filtre request);

    List<CaixaFixa> buscarConFiltrosAll(Filtre request);
}
