package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import com.jcerdar.gestorfutbol.persistence.model.CategoriaDespesa;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaDespesaCustomDao {

    Page<CategoriaDespesa> buscarConFiltros(Filtre request);

    List<CategoriaDespesa> buscarConFiltrosAll(Filtre request);
}
