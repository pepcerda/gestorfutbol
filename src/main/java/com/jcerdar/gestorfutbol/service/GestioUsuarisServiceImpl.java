package com.jcerdar.gestorfutbol.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.TenantDao;
import com.jcerdar.gestorfutbol.persistence.dao.UsuariDao;
import com.jcerdar.gestorfutbol.persistence.dao.UsuariTenantDao;
import com.jcerdar.gestorfutbol.persistence.model.Tenant;
import com.jcerdar.gestorfutbol.persistence.model.Usuari;
import com.jcerdar.gestorfutbol.persistence.model.UsuariTenant;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.UsuariDTO;
import com.jcerdar.gestorfutbol.service.model.UsuariTenantDTO;

@Service
public class GestioUsuarisServiceImpl implements GestioUsuarisService {

    @Autowired
    private ProveidorUsuarisAdminClient proveidorUsuarisAdminClient;

    @Autowired
    private UsuariDao usuariDao;

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private UsuariTenantDao usuariTenantDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginaDTO<List<UsuariDTO>> listUsuaris(Filtre filtre) {
        Page<Usuari> page = usuariDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<UsuariDTO>> paginaDTO = new PaginaDTO<>();
        List<UsuariDTO> usuarisDTO = new ArrayList<>();
        if(page.getTotalElements() > 0) {
            page.map(usuari -> modelMapper.map(usuari, UsuariDTO.class)).forEach(usuarisDTO::add);
            paginaDTO.setResult(usuarisDTO);
            paginaDTO.setTotal(page.getTotalElements());
        }
        return paginaDTO;
    }

    @Override
    public PaginaDTO<List<UsuariTenantDTO>> listUsuarisTenant(Filtre filtre) {
        Page<UsuariTenant> page = usuariTenantDao.findByTenantId(filtre.getTenantId(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<UsuariTenantDTO>> paginaDTO = new PaginaDTO<>();
        List<UsuariTenantDTO> usuarisTenantDTO = new ArrayList<>();
        if(page.getTotalElements() > 0) {
            page.map(usuariTenant -> modelMapper.map(usuariTenant, UsuariTenantDTO.class)).forEach(usuarisTenantDTO::add);
            paginaDTO.setResult(usuarisTenantDTO);
            paginaDTO.setTotal(page.getTotalElements());
        }
        return paginaDTO;
    }

    @Override
    public Long saveUsuari(UsuariTenantDTO usuariTenantDTO) {
        Usuari usuari = usuariDao.findByProviderId(usuariTenantDTO.getProviderId())
                .or(() -> usuariDao.findByEmail(usuariTenantDTO.getEmail()))
                .orElseGet(() -> {
                    Usuari u = modelMapper.map(usuariTenantDTO, Usuari.class);
                    return usuariDao.save(u);
                });

        Tenant tenant = tenantDao.findById(usuariTenantDTO.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant no trobat amb id: " + usuariTenantDTO.getTenantId()));

        UsuariTenant usuariTenant = usuariTenantDao.findByUsuariIdAndTenantId(usuari.getId(), tenant.getId())
                .orElseGet(() -> {
                    UsuariTenant ut = new UsuariTenant();
                    ut.setUsuari(usuari);
                    ut.setTenant(tenant);
                    return ut;
                });

        usuariTenant.setRol(usuariTenantDTO.getRol());
        usuariTenant.setActiu(Boolean.TRUE.equals(usuariTenantDTO.getActiu()));
        usuariTenantDao.save(usuariTenant); 
        return usuari.getId();
    }



}
