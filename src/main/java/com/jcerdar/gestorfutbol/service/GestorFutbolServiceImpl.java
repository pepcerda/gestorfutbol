package com.jcerdar.gestorfutbol.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.service.model.*;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.util.PdfUtil;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GestorFutbolServiceImpl implements GestorFutbolService {

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private CampanyaDao campanyaDao;

    @Autowired
    private SociDao sociDao;

    @Autowired
    private PatrocinadorDao patrocinadorDao;

    @Autowired
    private RolDirectiuDao rolDirectiuDao;

    @Autowired
    private DirectiuDao directiuDao;

    @Autowired
    private DirectivaDao directivaDao;

    @Autowired
    private ConfiguracioDao configuracioDao;

    @Autowired
    private CaixaFixaDao caixaFixaDao;

    @Autowired
    private TipoSociDao tipoSociDao;

    @Autowired
    private JugadorDao jugadorDao;

    @Autowired
    private EntrenadorDao entrenadorDao;

    @Autowired
    private DelegatDao delegatDao;

    @Autowired
    private MembrePlantillaDao membrePlantillaDao;

    @Autowired
    private MensualitatDao mensualitatDao;

    @Autowired
    private NominaDao nominaDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Autowired
    private EquipDao equipDao;

    @Autowired
    private PosicioJugadorDao posicioJugadorDao;

    @Autowired
    private QuotaJugadorDao quotaJugadorDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MediaService mediaService;

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<Long, Tenant> toTenant = ctx -> {
            Tenant tenant = tenantDao.findById(ctx.getSource()).orElse(null);
            return tenant;
        };

        Converter<Long, Campanya> toCampanya = ctx -> {
            Campanya campanya = campanyaDao.findById(ctx.getSource()).orElse(null);
            return campanya;
        };

        Converter<Long, Mensualitat> toMensualitat = ctx -> {
            Mensualitat mensualitat = mensualitatDao.findById(ctx.getSource()).orElse(null);
            return mensualitat;
        };

        Converter<Long, MembrePlantilla> toMembrePlantilla = ctx -> {
            MembrePlantilla membrePlantilla = membrePlantillaDao.findById(ctx.getSource()).orElse(null);
            return membrePlantilla;
        };

        Converter<Long, Categoria> toCategoria = ctx -> {
            Categoria categoria = categoriaDao.findById(ctx.getSource()).orElse(null);
            return categoria;
        };

        Converter<Long, Equip> toEquip = ctx -> {
            Equip equip = equipDao.findById(ctx.getSource()).orElse(null);
            return equip;
        };

        Converter<Long, PosicioJugador> toPosicioJugador = ctx -> {
            PosicioJugador posicioJugador = posicioJugadorDao.findById(ctx.getSource()).orElse(null);
            return posicioJugador;
        };

        TypeMap<CampanyaDTO, Campanya> jCampanyaMapper = modelMapper.createTypeMap(CampanyaDTO.class, Campanya.class);
        jCampanyaMapper.addMappings(mapper -> mapper.using(toTenant).map(CampanyaDTO::getTenantId, Campanya::setTenant));

        TypeMap<Campanya, CampanyaDTO> campanyaMapper = modelMapper.createTypeMap(Campanya.class, CampanyaDTO.class);
        campanyaMapper.addMappings(mapper -> mapper.map(src -> src.getTenant().getId(), CampanyaDTO::setTenantId));

        TypeMap<RolDirectiuDTO, RolDirectiu> jRolDirectiuMapper = modelMapper.createTypeMap(RolDirectiuDTO.class, RolDirectiu.class);
        jRolDirectiuMapper.addMappings(mapper -> mapper.using(toTenant).map(RolDirectiuDTO::getTenantId, RolDirectiu::setTenant));

        TypeMap<RolDirectiu, RolDirectiuDTO> rolDirectiuMapper = modelMapper.createTypeMap(RolDirectiu.class, RolDirectiuDTO.class);
        rolDirectiuMapper.addMappings(mapper -> mapper.map(src -> src.getTenant().getId(), RolDirectiuDTO::setTenantId));

        TypeMap<TipoSociDTO, TipoSoci> jTipoSociMapper = modelMapper.createTypeMap(TipoSociDTO.class, TipoSoci.class);
        jTipoSociMapper.addMappings(mapper -> mapper.using(toCampanya).map(TipoSociDTO::getCampanya, TipoSoci::setCampanya));

        TypeMap<TipoSoci, TipoSociDTO> tipoSociMapper = modelMapper.createTypeMap(TipoSoci.class, TipoSociDTO.class);
        tipoSociMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), TipoSociDTO::setCampanya));

        TypeMap<DirectiuDTO, Directiu> jDirectiuMapper = modelMapper.createTypeMap(DirectiuDTO.class, Directiu.class);
        jDirectiuMapper.addMappings(mapper -> mapper.skip(Directiu::setRol));

        TypeMap<Directiu, DirectiuDTO> directiuMapper = modelMapper.createTypeMap(Directiu.class, DirectiuDTO.class);
        directiuMapper.addMappings(mapper -> mapper.map(src -> src.getRol().getId(), DirectiuDTO::setRol));

        TypeMap<DirectivaDTO, Directiva> jDirectivaMapper = modelMapper.createTypeMap(DirectivaDTO.class, Directiva.class);
        jDirectivaMapper.addMappings(mapper -> mapper.skip(Directiva::setDirectius));
        jDirectivaMapper.addMappings(mapper -> mapper.using(toTenant).map(DirectivaDTO::getTenantId, Directiva::setTenant));

        TypeMap<Directiva, DirectivaDTO> directivaMapper = modelMapper.createTypeMap(Directiva.class, DirectivaDTO.class);
        directivaMapper.addMappings(mapper -> mapper.map(src -> src.getTenant().getId(), DirectivaDTO::setTenantId));

        TypeMap<ConfiguracioDTO, Configuracio> jConfiguracioMapper = modelMapper.createTypeMap(ConfiguracioDTO.class, Configuracio.class);
        jConfiguracioMapper.addMappings(mapper -> mapper.skip(Configuracio::setLogo));
        jConfiguracioMapper.addMappings(mapper -> mapper.using(toTenant).map(ConfiguracioDTO::getTenantId, Configuracio::setTenant));

        TypeMap<Configuracio, ConfiguracioDTO> configuracioMapper = modelMapper.createTypeMap(Configuracio.class, ConfiguracioDTO.class);
        configuracioMapper.addMappings(mapper -> mapper.map(src -> src.getTenant().getId(), ConfiguracioDTO::setTenantId));

        TypeMap<JugadorDTO, Jugador> jJugadorMapper = modelMapper.createTypeMap(JugadorDTO.class, Jugador.class);
        jJugadorMapper.addMappings(mapper -> mapper.using(toCampanya).map(JugadorDTO::getCampanya, Jugador::setCampanya));
        jJugadorMapper.addMappings(mapper -> mapper.using(toEquip).map(JugadorDTO::getEquip, Jugador::setEquip));

        TypeMap<Jugador, JugadorDTO> jugadorMapper = modelMapper.createTypeMap(Jugador.class, JugadorDTO.class);
        jugadorMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), JugadorDTO::setCampanya));
        jugadorMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), JugadorDTO::setEquip));

        TypeMap<EntrenadorDTO, Entrenador> jEntrenadorMapper = modelMapper.createTypeMap(EntrenadorDTO.class, Entrenador.class);
        jEntrenadorMapper.addMappings(mapper -> mapper.using(toCampanya).map(EntrenadorDTO::getCampanya, Entrenador::setCampanya));
        jEntrenadorMapper.addMappings(mapper -> mapper.using(toEquip).map(EntrenadorDTO::getEquip, Entrenador::setEquip));

        TypeMap<Entrenador, EntrenadorDTO> entrenadorMapper = modelMapper.createTypeMap(Entrenador.class, EntrenadorDTO.class);
        entrenadorMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), EntrenadorDTO::setCampanya));
        entrenadorMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), EntrenadorDTO::setEquip));

        TypeMap<DelegatDTO, Delegat> jDelegatMapper = modelMapper.createTypeMap(DelegatDTO.class, Delegat.class);
        jDelegatMapper.addMappings(mapper -> mapper.using(toCampanya).map(DelegatDTO::getCampanya, Delegat::setCampanya));
        jDelegatMapper.addMappings(mapper -> mapper.using(toEquip).map(DelegatDTO::getEquip, Delegat::setEquip));

        TypeMap<Delegat, DelegatDTO> delegatMapper = modelMapper.createTypeMap(Delegat.class, DelegatDTO.class);
        delegatMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), DelegatDTO::setCampanya));
        delegatMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), DelegatDTO::setEquip));

        TypeMap<Jugador, MembrePlantillaDTO> membrePlantillaJugadorMapper = modelMapper.createTypeMap(Jugador.class, MembrePlantillaDTO.class);
        membrePlantillaJugadorMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), MembrePlantillaDTO::setCampanya));
        membrePlantillaJugadorMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), MembrePlantillaDTO::setEquip));

        TypeMap<Entrenador, MembrePlantillaDTO> membrePlantillaEntrenadorMapper = modelMapper.createTypeMap(Entrenador.class, MembrePlantillaDTO.class);
        membrePlantillaEntrenadorMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), MembrePlantillaDTO::setCampanya));
        membrePlantillaEntrenadorMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), MembrePlantillaDTO::setEquip));

        TypeMap<Delegat, MembrePlantillaDTO> membrePlantillaDelegatMapper = modelMapper.createTypeMap(Delegat.class, MembrePlantillaDTO.class);
        membrePlantillaDelegatMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), MembrePlantillaDTO::setCampanya));
        membrePlantillaDelegatMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), MembrePlantillaDTO::setEquip));

        TypeMap<CategoriaDTO, Categoria> jCategoriaMapper = modelMapper.createTypeMap(CategoriaDTO.class, Categoria.class);
        jCategoriaMapper.addMappings(mapper -> mapper.using(toCampanya).map(CategoriaDTO::getCampanya, Categoria::setCampanya));

        TypeMap<Categoria, CategoriaDTO> categoriaMapper = modelMapper.createTypeMap(Categoria.class, CategoriaDTO.class);
        categoriaMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), CategoriaDTO::setCampanya));
    }

    @Override
    public PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre) {
        Page<Campanya> campanyas = campanyaDao.findAllByTenantId(filtre.getTenantId(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize(), Sort.by("any").ascending()));
        PaginaDTO<List<CampanyaDTO>> paginaDTO = new PaginaDTO<>();
        List<CampanyaDTO> campanyaDTOS = new ArrayList<>();
        if (campanyas.getTotalElements() > 0) {
            campanyaDTOS = campanyas.map(campanya -> modelMapper.map(campanya, CampanyaDTO.class)).getContent();
            paginaDTO.setTotal(campanyas.getTotalElements());
            paginaDTO.setResult(campanyaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public List<CampanyaDTO> listAllCampanyas(Filtre filtre) {
        List<Campanya> campanyas = campanyaDao.findAllByTenantId(filtre.getTenantId(), Sort.by("any").ascending());
        List<CampanyaDTO> campanyaDTOS = new ArrayList<>();
        if (!campanyas.isEmpty()) {
            campanyaDTOS = campanyas.stream().map(campanya -> modelMapper.map(campanya, CampanyaDTO.class)).collect(Collectors.toList());
        }
        return campanyaDTOS;
    }

    @Override
    public Long saveCampanya(CampanyaDTO campanyaDTO) {
        Campanya campanya = modelMapper.map(campanyaDTO, Campanya.class);
        campanya = campanyaDao.save(campanya);

        return campanya.getId();
    }

    @Override
    public void deleteCampanya(Long id) {
        //TODO: Associar deletes de totes les taules associades
        List<Soci> socis = sociDao.findAllByCampanyaId(id);
        if (socis.isEmpty()) {
            socis.forEach(s -> sociDao.deleteById(s.getId()));
        }

        List<Patrocinador> patrocinadors = patrocinadorDao.findAllByCampanyaId(id);
        if (patrocinadors.isEmpty()) {
            patrocinadors.forEach(p -> patrocinadorDao.deleteById(p.getId()));
        }

        campanyaDao.deleteById(id);
    }


    @Override
    public List<RolDirectiuDTO> listRolsDirectiu(Filtre filtre) {
        List<RolDirectiu> rols = rolDirectiuDao.findAllByTenantId(filtre.getTenantId());
        List<RolDirectiuDTO> rolsDTO = new ArrayList<>();
        if (!rols.isEmpty()) {
            rolsDTO = rols.stream().map(r -> modelMapper.map(r, RolDirectiuDTO.class)).collect(Collectors.toList());
            return rolsDTO;
        }
        return null;
    }

    @Override
    public PaginaDTO<List<DirectiuDTO>> listDirectius(Filtre filtre) {
        Page<Directiu> directius = directiuDao.findAllByTenantId(filtre.getTenantId(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<DirectiuDTO>> paginaDTO = new PaginaDTO<>();
        List<DirectiuDTO> directiuDTOS = new ArrayList<>();
        if (directius.getTotalElements() > 0) {
            directiuDTOS = directius.map(d -> modelMapper.map(d, DirectiuDTO.class)).getContent();
            paginaDTO.setTotal(directius.getTotalElements());
            paginaDTO.setResult(directiuDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long saveDirectiu(DirectiuDTO directiuDTO) {
        Directiu directiu = jDirectiuMapper(directiuDTO);
        directiu = directiuDao.save(directiu);
        return directiu.getId();
    }

    @Override
    public void deleteDirectiu(Long id) {
        directiuDao.deleteById(id);
    }

    @Override
    public void baixaDirectiva(BaixaDTO baixaDTO) {
        Directiva directivaActual = directivaDao.findById(baixaDTO.getDirectivaId()).orElseThrow(() -> new RuntimeException("Directiva no trobada"));
        directivaActual.setDataBaixa(baixaDTO.getDataBaixa());
        directivaDao.save(directivaActual);
    }

    @Override
    public Long saveDirectiva(DirectivaDTO directivaDTO) {

        Directiva directiva = jDirectivaMapper(directivaDTO);

        directiva = directivaDao.save(directiva);

        return directiva.getId();
    }

    @Override
    public DirectivaDTO listDirectiva(Filtre filtre) {

        Directiva directiva = directivaDao.findDirectivaByDataBaixaIsNull();
        if (directiva != null) {
            DirectivaDTO directivaDTO = modelMapper.map(directiva, DirectivaDTO.class);
            return directivaDTO;
        }

        return null;
    }

    public Boolean checkDirectiva() {
        return directivaDao.count() > 0;
    }

    @Override
    public List<DirectivaDTO> listHistoricDirectiva(Filtre filtre) {
        List<Directiva> directivas = directivaDao.findAllByTenantIdOrderByDataAltaAsc(filtre.getTenantId());
        List<DirectivaDTO> directivaDTOS = new ArrayList<>();
        directivas.forEach(d -> directivaDTOS.add(modelMapper.map(d, DirectivaDTO.class)));
        return directivaDTOS;
    }

    @Override
    public ConfiguracioDTO getConfiguracio(Filtre filtre) {
        Configuracio configuracio = configuracioDao.findConfiguracioByTenantId(filtre.getTenantId());
        ConfiguracioDTO config = modelMapper.map(configuracio, ConfiguracioDTO.class);
        return config;
    }

    @Override
    public ConfiguracioGeneralDTO getConfiguracioGeneral(Filtre filtre) {
        Configuracio configuracio = configuracioDao.findConfiguracioByTenantId(filtre.getTenantId());

        if (configuracio != null) {
            ConfiguracioGeneralDTO configuracioGeneralDTO = new ConfiguracioGeneralDTO();
            configuracioGeneralDTO.setNom(configuracio.getNom());
            configuracioGeneralDTO.setLogo(configuracio.getLogo());
            configuracioGeneralDTO.setColorPrincipal(configuracio.getColorPrincipal());
            configuracioGeneralDTO.setColorFons1(configuracio.getColorFons1());
            configuracioGeneralDTO.setColorFons2(configuracio.getColorFons2());
            return configuracioGeneralDTO;
        } else {
            return null;
        }

    }

    @Override
    public Long saveConfiguracio(ConfiguracioDTO configuracioDTO) {
        Configuracio configuracio = jConfiguracioMapper(configuracioDTO);
        configuracioDao.save(configuracio);
        return configuracio.getId();
    }

    @Override
    public PaginaDTO<List<TipoSociDTO>> listTipoSocis(Filtre filtre) {
        Page<TipoSoci> tipoSocis = tipoSociDao.findByCampanya(filtre.getCampanyaActiva(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<TipoSociDTO>> paginaDTO = new PaginaDTO<>();
        List<TipoSociDTO> tipoSociDTOS = new ArrayList<>();
        if (tipoSocis.getTotalElements() > 0) {
            tipoSociDTOS = tipoSocis.map(c -> modelMapper.map(c, TipoSociDTO.class)).getContent();
            paginaDTO.setTotal(tipoSocis.getTotalElements());
            paginaDTO.setResult(tipoSociDTOS);
        }
        return paginaDTO;

    }

    @Override
    public List<TipoSociDTO> listAllTipoSocis(Filtre filtre) {
        List<TipoSoci> tipoSocis = tipoSociDao.findAllByCampanya(filtre.getCampanyaActiva());
        List<TipoSociDTO> tipoSociDTOS = new ArrayList<>();
        if (!tipoSocis.isEmpty()) {
            tipoSociDTOS = tipoSocis.stream().map(c -> modelMapper.map(c, TipoSociDTO.class)).collect(Collectors.toList());
        }
        return tipoSociDTOS;
    }

    @Override
    public Long saveTipoSoci(TipoSociDTO tipoSociDTO) {
        TipoSoci tipoSoci = jTipoSociMapper(tipoSociDTO);
        tipoSoci = tipoSociDao.save(tipoSoci);
        return tipoSoci.getId();
    }

    @Override
    public void deleteTipoSoci(Long id) {
        tipoSociDao.deleteById(id);
    }

    @Override
    public List<MembrePlantillaDTO> listAllMembresPlantilla(Filtre filtre) {
        List<Long> idsFiltrats = filtre.getIds();
        List<MembrePlantilla> membres;
        if (idsFiltrats != null && !idsFiltrats.isEmpty()) {
            membres = membrePlantillaDao.findAllByCampanyaIdAndIdInAndEquipId(filtre.getCampanyaActiva(), idsFiltrats, filtre.getEquipActiu());
        } else {
            membres = membrePlantillaDao.findAllByCampanyaIdAndEquipId(filtre.getCampanyaActiva(), filtre.getEquipActiu());
        }
        List<MembrePlantillaDTO> membreDTOS = new ArrayList<>();
        if (!membres.isEmpty()) {
            membreDTOS = membres.stream().map(c -> modelMapper.map(c, MembrePlantillaDTO.class)).collect(Collectors.toList());
        }
        return membreDTOS;
    }

    @Override
    public Long saveJugador(JugadorDTO jugadorDTO) {
        Jugador jugador = modelMapper.map(jugadorDTO, Jugador.class);
        jugador = jugadorDao.save(jugador);
        QuotaJugador quotaJugador = new QuotaJugador();
        quotaJugador.setJugador(jugador);
        quotaJugador.setQuantitat(jugador.getEquip().getQuota());
        quotaJugadorDao.save(quotaJugador);
        return jugador.getId();
    }

    @Override
    public void deleteJugador(Long id) {
        jugadorDao.deleteById(id);
    }

    @Override
    public List<JugadorDTO> listAllJugadors(Filtre filtre) {
        List<Jugador> jugadors = jugadorDao.buscarConFiltrosAll(filtre);
        List<JugadorDTO> jugadorDTOS = new ArrayList<>();
        if (!jugadors.isEmpty()) {
            jugadorDTOS = jugadors.stream().map(jugador -> modelMapper.map(jugador, JugadorDTO.class)).collect(Collectors.toList());
        }
        return jugadorDTOS;
    }

    @Override
    public List<PosicioDTO> listAllPosicions() {
        List<PosicioJugador> posicions = posicioJugadorDao.findAll();
        List<PosicioDTO> posicioDTOS = new ArrayList<>();
        if (!posicions.isEmpty()) {
            posicioDTOS = posicions.stream().map(p -> modelMapper.map(p, PosicioDTO.class)).collect(Collectors.toList());
        }
        return posicioDTOS;
    }

    @Override
    public PaginaDTO<List<PosicioDTO>> listPosicions(Filtre filtre) {
        Page<PosicioJugador> posicions = posicioJugadorDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<PosicioDTO>> paginaDTO = new PaginaDTO<>();
        List<PosicioDTO> posicioDTOS = new ArrayList<>();
        if (posicions.getTotalElements() > 0) {
            posicioDTOS = posicions.map(c -> modelMapper.map(c, PosicioDTO.class)).getContent();
            paginaDTO.setTotal(posicions.getTotalElements());
            paginaDTO.setResult(posicioDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long savePosicio(PosicioDTO posicioDTO) {
        PosicioJugador posicioJugador = modelMapper.map(posicioDTO, PosicioJugador.class);
        posicioJugador = posicioJugadorDao.save(posicioJugador);
        return posicioJugador.getId();
    }

    @Override
    public void deletePosicio(Long id) {
        posicioJugadorDao.deleteById(id);
    }

    @Override
    public Long saveEntrenador(EntrenadorDTO entrenadorDTO) {
        Entrenador entrenador = modelMapper.map(entrenadorDTO, Entrenador.class);
        return entrenadorDao.save(entrenador).getId();
    }

    @Override
    public void deleteEntrenador(Long id) {
        entrenadorDao.deleteById(id);
    }

    @Override
    public List<EntrenadorDTO> listAllEntrenadors(Filtre filtre) {
        List<Entrenador> entrenadors = entrenadorDao.findAllByCampanyaAndEquip(filtre.getCampanyaActiva(), filtre.getEquipActiu());
        List<EntrenadorDTO> entrenadorDTOS = new ArrayList<>();
        if (!entrenadors.isEmpty()) {
            entrenadorDTOS = entrenadors.stream().map(c -> modelMapper.map(c, EntrenadorDTO.class)).collect(Collectors.toList());
        }
        return entrenadorDTOS;
    }

    @Override
    public List<DelegatDTO> listAllDelegats(Filtre filtre) {
        List<Delegat> delegats = delegatDao.findAllByCampanyaAndEquip(filtre.getCampanyaActiva(), filtre.getEquipActiu());
        List<DelegatDTO> delegatDTOS = new ArrayList<>();
        if (!delegats.isEmpty()) {
            delegatDTOS = delegats.stream().map(c -> modelMapper.map(c, DelegatDTO.class)).collect(Collectors.toList());
        }
        return delegatDTOS;
    }

    @Override
    public Long saveDelegat(DelegatDTO delegatDTO) {
        Delegat delegat = modelMapper.map(delegatDTO, Delegat.class);
        return delegatDao.save(delegat).getId();
    }

    @Override
    public void deleteDelegat(Long id) {
        delegatDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<CategoriaDTO>> listCategoria(Filtre filtre) {
        Page<Categoria> categorias = categoriaDao.findByCampanya(filtre.getCampanyaActiva(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<CategoriaDTO>> paginaDTO = new PaginaDTO<>();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        if (categorias.getTotalElements() > 0) {
            categoriaDTOS = categorias.map(c -> modelMapper.map(c, CategoriaDTO.class)).getContent();
            paginaDTO.setTotal(categorias.getTotalElements());
            paginaDTO.setResult(categoriaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public List<CategoriaDTO> listAllCategoria(Filtre filtre) {
        List<Categoria> categorias = categoriaDao.findAllByCampanya(filtre.getCampanyaActiva());
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        if (!categorias.isEmpty()) {
            categoriaDTOS = categorias.stream().map(c -> modelMapper.map(c, CategoriaDTO.class)).collect(Collectors.toList());
        }
        return categoriaDTOS;
    }

    public Long saveCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);


        // --- NUEVO: actualizar cuota de jugadores si la cuota del equipo cambia ---
        categoriaDTO.getEquips().forEach(equip -> {

            if (equip.getId() != null) {  // Solo si no es nuevo
                Equip equipBD = equipDao.findById(equip.getId()).orElse(null);

                if (equipBD != null && equipBD.getQuota() != null &&
                        equip.getQuota().compareTo(equipBD.getQuota()) != 0) {

                    // La quota ha cambiado → actualizar jugadores
                    actualizarQuotaJugadors(equip, categoriaDTO.getId());
                }
            }
        });
        // ---------------------------------------------------------------------------
        categoria = categoriaDao.save(categoria);

        // --- BLOQUE ORIGINAL: creación de mensualidades ---
        if (categoriaDTO.getId() == null || categoriaDTO.getEquips().stream().anyMatch(e -> e.getId() == null)) {

            Campanya campanya = categoria.getCampanya();

            Categoria finalCategoria = categoria;
            categoriaDTO.getEquips().stream().filter(e -> e.getId() == null).forEach(e -> {
                Calendar cal = Calendar.getInstance();
                cal.setTime(campanya.getAny());
                Integer anyInici = cal.get(Calendar.YEAR);
                Integer anyFi = anyInici + 1;

                List<Mensualitat> mensualitats = new ArrayList<>();

                for (int mes = 7; mes <= 12; mes++) {
                    Mensualitat m = new Mensualitat();
                    m.setCampanya(campanya);
                    m.setAny(anyInici);
                    m.setMes(mes);
                    m.setEquip(finalCategoria.getEquips()
                            .stream()
                            .filter(eq -> eq.getNom().equals(e.getNom()))
                            .findFirst().orElse(null));
                    mensualitats.add(m);
                }

                for (int mes = 1; mes <= 6; mes++) {
                    Mensualitat m = new Mensualitat();
                    m.setCampanya(campanya);
                    m.setAny(anyFi);
                    m.setMes(mes);
                    m.setEquip(finalCategoria.getEquips()
                            .stream()
                            .filter(eq -> eq.getNom().equals(e.getNom()))
                            .findFirst().orElse(null));
                    mensualitats.add(m);
                }

                mensualitatDao.saveAll(mensualitats);
            });
        }

        return categoria.getId();
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaDao.deleteById(id);
    }

    @Override
    public List<EquipDTO> listAllEquips(Long idCampanya) {
        List<Equip> equips = equipDao.findAllByCampanyaId(idCampanya);
        List<EquipDTO> equipDTOS = new ArrayList<>();
        if (!equips.isEmpty()) {
            equipDTOS = equips.stream().map(c -> modelMapper.map(c, EquipDTO.class)).collect(Collectors.toList());
        }
        return equipDTOS;
    }

    private Directiu jDirectiuMapper(DirectiuDTO directiuDTO) {
        Directiu directiu = modelMapper.map(directiuDTO, Directiu.class);

        RolDirectiu rolDirectiu = rolDirectiuDao.findById(directiuDTO.getRol()).orElse(null);
        directiu.setRol(rolDirectiu);

        return directiu;
    }

    private Directiva jDirectivaMapper(DirectivaDTO directivaDTO) {
        Directiva directiva = modelMapper.map(directivaDTO, Directiva.class);
        List<Directiu> directius = new ArrayList<>();

        directivaDTO.getDirectius().forEach(d -> {
            Directiu directiu = jDirectiuMapper(d);
            directius.add(directiu);
        });

        directiva.setDirectius(directius);

        return directiva;
    }

    private Configuracio jConfiguracioMapper(ConfiguracioDTO configuracioDTO) {
        Configuracio configuracio = modelMapper.map(configuracioDTO, Configuracio.class);
        try {
            if (configuracioDTO.getLogo() != null && !configuracioDTO.getLogo().isEmpty()) {
                String logoUrl = mediaService.guardarLogoBase64(configuracioDTO.getLogo());
                configuracio.setLogo(logoUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuracio;
    }

    private TipoSoci jTipoSociMapper(TipoSociDTO tipoSociDTO) {
        TipoSoci tipoSoci = modelMapper.map(tipoSociDTO, TipoSoci.class);

        Campanya campanya = campanyaDao.findById(tipoSociDTO.getCampanya()).orElse(null);
        tipoSoci.setCampanya(campanya);

        return tipoSoci;
    }

    @Transactional
    private void actualizarQuotaJugadors(EquipDTO equip, Long campanyaId) {

        // 1. Obtener jugadores del equipo
        List<Jugador> jugadors = jugadorDao.findAllByEquip(equip.getId());

        // 2. Actualizar su QuotaJugador si NO tienen excepción
        jugadors.forEach(jugador -> {
            QuotaJugador quotaJugador = quotaJugadorDao.findByJugadorAndCampanya(jugador.getId(), campanyaId);
            if (!quotaJugador.getExepcio()) {
                quotaJugador.setQuantitat(equip.getQuota());
                quotaJugadorDao.save(quotaJugador);
            }
        });
    }


}
