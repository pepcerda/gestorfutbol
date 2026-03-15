package com.jcerdar.gestorfutbol.service;

import java.util.ArrayList;
import java.util.List;

import com.jcerdar.gestorfutbol.persistence.dao.RolUsuariTenantDao;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.service.model.*;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.TenantDao;
import com.jcerdar.gestorfutbol.persistence.dao.UsuariDao;
import com.jcerdar.gestorfutbol.persistence.dao.UsuariTenantDao;

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
    private RolUsuariTenantDao rolUsuariTenantDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;


    @PostConstruct
    public void init() {
        // Converters null-safe para ids anidados
        Converter<UsuariTenant, Long> usuariIdConv = ctx ->
                ctx.getSource() != null && ctx.getSource().getUsuari() != null
                        ? ctx.getSource().getUsuari().getId()
                        : null;

        Converter<UsuariTenant, Long> tenantIdConv = ctx ->
                ctx.getSource() != null && ctx.getSource().getTenant() != null
                        ? ctx.getSource().getTenant().getId()
                        : null;

        // Crea el TypeMap en la dirección correcta: ENTIDAD -> DTO
        TypeMap<UsuariTenant, UsuariTenantDTO> typeMap =
                modelMapper.createTypeMap(UsuariTenant.class, UsuariTenantDTO.class);

        typeMap.addMappings(m -> {
            // id del DTO = id del USUARI (asunción)
            m.using(usuariIdConv).map(src -> src, UsuariTenantDTO::setId);

            // tenantId del DTO = id del TENANT
            m.using(tenantIdConv).map(src -> src, UsuariTenantDTO::setTenantId);

            // Campos del usuario
            m.map(src -> src.getUsuari().getProviderId(), UsuariTenantDTO::setProviderId);
            m.map(src -> src.getUsuari().getSuperAdmin(), UsuariTenantDTO::setSuperAdmin);
            m.map(src -> src.getUsuari().getNom(), UsuariTenantDTO::setNom);
            m.map(src -> src.getUsuari().getCognoms(), UsuariTenantDTO::setCognoms);
            m.map(src -> src.getUsuari().getEmail(), UsuariTenantDTO::setEmail);

            // Activo
            m.map(UsuariTenant::getActiu, UsuariTenantDTO::setActiu);
        });
    }

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
    public PaginaDTO<List<TenantDTO>> listTenantsUsuari(Filtre filtre) {
        //El filtro nos llega con providerId, el FE no sabe el ID de BBDD del usuario
        if(filtre.getProviderId() != null) {
            Usuari usuari = usuariDao.findByProviderId(filtre.getProviderId())
                    .orElseThrow(() -> new RuntimeException("Usuari no trobat amb providerId: " + filtre.getProviderId()));
            filtre.setUserId(usuari.getId());
        }
        Page<UsuariTenant> page = usuariTenantDao.findByUsuariId(filtre.getUserId(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<TenantDTO>> paginaDTO = new PaginaDTO<>();
        List<TenantDTO> tenantsDTO = new ArrayList<>();
        if(page.getTotalElements() > 0) {
            tenantsDTO = page.map(usuariTenant -> modelMapper.map(usuariTenant.getTenant(), TenantDTO.class)).getContent();
            paginaDTO.setResult(tenantsDTO);
            paginaDTO.setTotal(page.getTotalElements());
        }
        return paginaDTO;
    }

    @Override
    public UsuariDTO getUsuari(String providerId) {
        Usuari usuari = usuariDao.findByProviderId(providerId)
                .orElseThrow(() -> new RuntimeException("Usuari no trobat amb id: " + providerId));
        UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
        return usuariDTO;
    }

    @Override
    public Long saveUsuari(UsuariTenantDTO usuariTenantDTO) {
        final boolean[] esNouUsuari = {false};

        Usuari usuari = usuariDao.findByProviderId(usuariTenantDTO.getProviderId())
                .or(() -> usuariDao.findByEmail(usuariTenantDTO.getEmail()))
                .orElseGet(() -> {
                    esNouUsuari[0] = true;
                    CreacioUsuariRequestDTO creacioUsuariRequestDTO = new CreacioUsuariRequestDTO(usuariTenantDTO.getEmail(), usuariTenantDTO.getNom(), usuariTenantDTO.getCognoms());
                    ProveidorResponseDTO proveidorResponseDTO = proveidorUsuarisAdminClient.crearUsuari(creacioUsuariRequestDTO);
                    Usuari u = modelMapper.map(usuariTenantDTO, Usuari.class);
                    u.setProviderId(proveidorResponseDTO.getId());
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

        usuariTenant.setRol(modelMapper.map(usuariTenantDTO.getRol(), RolUsuariTenant.class));
        usuariTenant.setActiu(Boolean.TRUE.equals(usuariTenantDTO.getActiu()));
        usuariTenantDao.save(usuariTenant);


        // 2. Disparar el aviso solo si es nuevo
        if (esNouUsuari[0]) {
            emailService.enviarAvisBenvinguda(usuari, tenant);
        }

        return usuari.getId();
    }

    @Override
    public void deleteUsuariTenant(Long tenantId, Long userId) {
        UsuariTenantId id = new UsuariTenantId(userId, tenantId);

        usuariTenantDao.deleteById(id);

        Page<UsuariTenant> page = usuariTenantDao.findByUsuariId(userId, PageRequest.of(0, 10));

        //Si el usuario no tiene más asociaciones con tenants, lo eliminamos de la tabla de usuarios y del proveedor de usuarios
        if(page.getTotalElements() == 0) {
            Usuari usuari = usuariDao.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuari no trobat amb id: " + userId));
            proveidorUsuarisAdminClient.eliminarUsuari(usuari.getProviderId());
            usuariDao.deleteById(userId);
        }
    }

    @Override
    public List<RolDTO> listRols() {
        List<RolUsuariTenant> rols = rolUsuariTenantDao.findAll();
        List<RolDTO> rolsDTO = new ArrayList<>();
        if(!rols.isEmpty()) {
            rols.stream().map(rol -> modelMapper.map(rol, RolDTO.class)).forEach(rolsDTO::add);
        }
        return rolsDTO;
    }

    private UsuariTenantDTO mapToUsuariTenantDTO(UsuariTenant usuariTenant, Usuari usuari) {
        UsuariTenantDTO usuariTenantDTO = modelMapper.map(usuariTenant, UsuariTenantDTO.class);
        usuariTenantDTO.setId(usuari.getId());
        usuariTenantDTO.setProviderId(usuari.getProviderId());
        usuariTenantDTO.setEmail(usuari.getEmail());
        usuariTenantDTO.setNom(usuari.getNom());
        usuariTenantDTO.setCognoms(usuari.getCognoms());
        return usuariTenantDTO;
    }


}
