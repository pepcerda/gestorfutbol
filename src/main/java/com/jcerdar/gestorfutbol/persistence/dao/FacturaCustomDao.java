package com.jcerdar.gestorfutbol.persistence.dao;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.CaixaFixa;
import com.jcerdar.gestorfutbol.persistence.model.Factura;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacturaCustomDao {

    Page<Factura> buscarConFiltros(Filtre filtre);

    List<Factura> buscarConFiltrosAll(Filtre filtre);
}
