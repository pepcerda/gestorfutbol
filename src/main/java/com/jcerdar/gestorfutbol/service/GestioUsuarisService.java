package com.jcerdar.gestorfutbol.service;

import java.util.List;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.UsuariDTO;
import com.jcerdar.gestorfutbol.service.model.UsuariTenantDTO;

public interface GestioUsuarisService {

    PaginaDTO<List<UsuariDTO>> listUsuaris(Filtre filtre);

    PaginaDTO<List<UsuariTenantDTO>> listUsuarisTenant(Filtre filtre);

    Long saveUsuari(UsuariTenantDTO usuariTenantDTO);


    
}
