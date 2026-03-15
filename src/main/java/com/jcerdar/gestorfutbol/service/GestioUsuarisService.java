package com.jcerdar.gestorfutbol.service;

import java.util.List;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.*;

public interface GestioUsuarisService {

    PaginaDTO<List<UsuariDTO>> listUsuaris(Filtre filtre);

    PaginaDTO<List<UsuariTenantDTO>> listUsuarisTenant(Filtre filtre);

    PaginaDTO<List<TenantDTO>> listTenantsUsuari(Filtre filtre);

    UsuariDTO getUsuari(String providerId);

    Long saveUsuari(UsuariTenantDTO usuariTenantDTO);

    void deleteUsuariTenant(Long tenantId, Long userId);

    List<RolDTO> listRols();


    
}
