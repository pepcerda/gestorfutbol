package com.jcerdar.gestorfutbol.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.service.model.*;
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
import com.jcerdar.gestorfutbol.persistence.model.type.Posicio;
import com.jcerdar.gestorfutbol.service.util.PdfUtil;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GestorFutbolServiceImpl implements GestorFutbolService {

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
    private ModelMapper modelMapper;

    @Autowired
    private MediaService mediaService;

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

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

        Converter<Posicio, PosicioDTO> toPosicio = pos -> {
            PosicioDTO posicio = new PosicioDTO();
            if (pos.getSource() != null) {
                posicio.setValor(pos.getSource().getValor());
                posicio.setDescripcion(pos.getSource().getDescripcion());
                posicio.setName(pos.getSource().name());
            }
            return posicio;
        };

        Converter<PosicioDTO, Posicio> toPosicioEntity = pos -> {
            if (pos.getSource() != null) {
                return Posicio.valueOf(pos.getSource().getName());
            } else {
                return null;
            }
        };

        TypeMap<TipoSociDTO, TipoSoci> jTipoSociMapper = modelMapper.createTypeMap(TipoSociDTO.class, TipoSoci.class);
        jTipoSociMapper.addMappings(mapper -> mapper.using(toCampanya).map(TipoSociDTO::getCampanya, TipoSoci::setCampanya));

        TypeMap<TipoSoci, TipoSociDTO> tipoSociMapper = modelMapper.createTypeMap(TipoSoci.class, TipoSociDTO.class);
        tipoSociMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), TipoSociDTO::setCampanya));

        TypeMap<SociDTO, Soci> jSociMapper = modelMapper.createTypeMap(SociDTO.class, Soci.class);
        jSociMapper.addMappings(mapper -> {
            mapper.using(toCampanya).map(SociDTO::getCampanya, Soci::setCampanya);
        });

        TypeMap<Soci, SociDTO> sociMapper = modelMapper.createTypeMap(Soci.class, SociDTO.class);
        sociMapper.addMappings(mapper -> {
            mapper.map(src -> src.getCampanya().getId(), SociDTO::setCampanya);
        });

        TypeMap<PatrocinadorDTO, Patrocinador> jPatrociniMapper = modelMapper.createTypeMap(PatrocinadorDTO.class, Patrocinador.class);
        jPatrociniMapper.addMappings(mapper -> mapper.skip(Patrocinador::setLogo));
        jPatrociniMapper.addMappings(mapper -> mapper.using(toCampanya).map(PatrocinadorDTO::getCampanya, Patrocinador::setCampanya));

        modelMapper.typeMap(Patrocinador.class, Patrocinador.class).addMappings(mapper -> mapper.skip(Patrocinador::setId));

        TypeMap<Patrocinador, PatrocinadorDTO> patrocinadorMapper = modelMapper.createTypeMap(Patrocinador.class, PatrocinadorDTO.class);
        patrocinadorMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), PatrocinadorDTO::setCampanya));

        TypeMap<DirectiuDTO, Directiu> jDirectiuMapper = modelMapper.createTypeMap(DirectiuDTO.class, Directiu.class);
        jDirectiuMapper.addMappings(mapper -> mapper.skip(Directiu::setRol));

        TypeMap<Directiu, DirectiuDTO> directiuMapper = modelMapper.createTypeMap(Directiu.class, DirectiuDTO.class);
        directiuMapper.addMappings(mapper -> mapper.map(src -> src.getRol().getId(), DirectiuDTO::setRol));

        TypeMap<DirectivaDTO, Directiva> jDirectivaMapper = modelMapper.createTypeMap(DirectivaDTO.class, Directiva.class);
        jDirectivaMapper.addMappings(mapper -> mapper.skip(Directiva::setDirectius));

        TypeMap<ConfiguracioDTO, Configuracio> jConfiguracioMapper = modelMapper.createTypeMap(ConfiguracioDTO.class, Configuracio.class);
        jConfiguracioMapper.addMappings(mapper -> mapper.skip(Configuracio::setLogo));

        TypeMap<JugadorDTO, Jugador> jJugadorMapper = modelMapper.createTypeMap(JugadorDTO.class, Jugador.class);
        jJugadorMapper.addMappings(mapper -> mapper.using(toCampanya).map(JugadorDTO::getCampanya, Jugador::setCampanya));
        jJugadorMapper.addMappings(mapper -> mapper.using(toEquip).map(JugadorDTO::getEquip, Jugador::setEquip));
        jJugadorMapper.addMappings(mapper -> mapper.using(toPosicioEntity).map(JugadorDTO::getPosicio, Jugador::setPosicio));

        TypeMap<Jugador, JugadorDTO> jugadorMapper = modelMapper.createTypeMap(Jugador.class, JugadorDTO.class);
        jugadorMapper.setPostConverter(ctx -> {
            Jugador source = ctx.getSource();
            JugadorDTO destination = ctx.getDestination();

            if (source.getPosicio() != null) {
                PosicioDTO dto = new PosicioDTO();
                dto.setValor(source.getPosicio().getValor());
                dto.setDescripcion(source.getPosicio().getDescripcion());
                dto.setName(source.getPosicio().name());
                destination.setPosicio(dto);
            }

            return destination;
        });
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
        Page<Campanya> campanyas = campanyaDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize(), Sort.by("any").ascending()));
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
    public List<CampanyaDTO> listAllCampanyas() {
        List<Campanya> campanyas = campanyaDao.findAll(Sort.by("any").ascending());
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
        //Revisam previament que no tengui ni socis ni patrocinadors donats d'alta
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
    public PaginaDTO<List<SociDTO>> listSocis(Filtre filtre) {
        Page<Soci> socis = sociDao.buscarConFiltros(filtre);

        PaginaDTO<List<SociDTO>> paginaDTO = new PaginaDTO<>();
        List<SociDTO> sociDTOS = new ArrayList<>();

        if (socis.getTotalElements() > 0) {
            sociDTOS = socis.map(soci -> modelMapper.map(soci, SociDTO.class)).getContent();
            paginaDTO.setTotal(socis.getTotalElements());
            paginaDTO.setResult(sociDTOS);
        }
        return paginaDTO;
    }

    @Override
    public List<SociDTO> listAllSocis(Filtre filtre) {
        List<Soci> socis = sociDao.buscarConFiltrosAll(filtre);
        List<SociDTO> sociDTOS = new ArrayList<>();

        if (!socis.isEmpty()) {
            sociDTOS = socis.stream().map(soci -> modelMapper.map(soci, SociDTO.class)).collect(Collectors.toList());
        }
        return sociDTOS;
    }


    @Override
    public Long saveSoci(SociDTO sociDTO) {
        if (sociDTO.getId() == null) {
            Integer lastIdSoci = sociDao.findMaxIdSociByCampanya(sociDTO.getCampanya());
            sociDTO.setIdSoci(lastIdSoci + 1);
        }
        Soci soci = modelMapper.map(sociDTO, Soci.class);
        soci = sociDao.save(soci);
        return soci.getId();
    }

    @Override
    public void deleteSoci(Long id) {
        sociDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre) {
        Page<Patrocinador> patrocinadors = patrocinadorDao.buscarConFiltros(filtre);
        PaginaDTO<List<PatrocinadorDTO>> paginaDTO = new PaginaDTO<>();
        List<PatrocinadorDTO> patrocinadorDTOS = new ArrayList<>();
        if (patrocinadors.getTotalElements() > 0) {
            patrocinadorDTOS = patrocinadors.map(patr -> modelMapper.map(patr, PatrocinadorDTO.class)).getContent();
            paginaDTO.setTotal(patrocinadors.getTotalElements());
            paginaDTO.setResult(patrocinadorDTOS);
        }
        return paginaDTO;
    }

    @Override
    public List<PatrocinadorDTO> listAllPatrocinadors(Filtre filtre) {
        List<Patrocinador> patrocinadors = patrocinadorDao.buscarConFiltrosAll(filtre);
        List<PatrocinadorDTO> patrocinadorDTOS = new ArrayList<>();
        if (!patrocinadors.isEmpty()) {
            patrocinadorDTOS = patrocinadors.stream().map(patr -> modelMapper.map(patr, PatrocinadorDTO.class)).collect(Collectors.toList());
        }
        return patrocinadorDTOS;
    }

    public PaginaDTO<List<PatrocinadorDTO>> listPatrocinador1(Filtre filtre) {
        Page<Patrocinador> patrocinadors = patrocinadorDao.buscarConFiltros(filtre);
        PaginaDTO<List<PatrocinadorDTO>> paginaDTO = new PaginaDTO<>();
        List<PatrocinadorDTO> patrocinadorDTOS = new ArrayList<>();
        if (patrocinadors.getTotalElements() > 0) {
            patrocinadorDTOS = patrocinadors.map(patr -> modelMapper.map(patr, PatrocinadorDTO.class)).getContent();
            paginaDTO.setTotal(patrocinadors.getTotalElements());
            paginaDTO.setResult(patrocinadorDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long savePatrocinador(PatrocinadorDTO patrocinadorDTO) {
        Patrocinador patrocinador = jPatrocinadorMapper(patrocinadorDTO);
        patrocinador = patrocinadorDao.save(patrocinador);
        return patrocinador.getId();
    }

    @Override
    public void deletePatrocinador(Long id) {
        patrocinadorDao.deleteById(id);
    }

    @Override
    public Long duplicarPatrocinador(PatrocinadorDTO patrocinadorDTO, Long idCampanya) {
        Campanya campanya = campanyaDao.findById(idCampanya).orElseThrow(() -> new EntityNotFoundException("Campanya no trobada"));
        Patrocinador original = patrocinadorDao.findById(patrocinadorDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Patrocinador no trobat"));
        Patrocinador patrocinadorNou = modelMapper.map(original, Patrocinador.class);
        patrocinadorNou.setCampanya(campanya);
        patrocinadorNou = patrocinadorDao.save(patrocinadorNou);
        return patrocinadorNou.getId();
    }

    @Override
    public String getReceipt(Long id) {
        Patrocinador patrocinador = patrocinadorDao.findById(id).orElse(null);
        if (patrocinador != null) {
            PatrocinadorDTO patrocinadorDTO = modelMapper.map(patrocinador, PatrocinadorDTO.class);
            return PdfUtil.generatePdf(patrocinadorDTO, patrocinador.getCampanya().getTitol());
        } else {
            return "";
        }
    }

    @Override
    public List<RolDirectiuDTO> listRolsDirectiu() {
        List<RolDirectiu> rols = rolDirectiuDao.findAll();
        List<RolDirectiuDTO> rolsDTO = new ArrayList<>();
        if (!rols.isEmpty()) {
            rolsDTO = rols.stream().map(r -> modelMapper.map(r, RolDirectiuDTO.class)).collect(Collectors.toList());
            return rolsDTO;
        }
        return null;
    }

    @Override
    public PaginaDTO<List<DirectiuDTO>> listDirectius(Filtre filtre) {
        Page<Directiu> directius = directiuDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
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
        Directiva directivaActual = directivaDao.findDirectivaByDataBaixaIsNull();
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
    public DirectivaDTO listDirectiva() {

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
    public List<DirectivaDTO> listHistoricDirectiva() {
        List<Directiva> directivas = directivaDao.findAllByOrderByDataAltaAsc();
        List<DirectivaDTO> directivaDTOS = new ArrayList<>();
        directivas.forEach(d -> directivaDTOS.add(modelMapper.map(d, DirectivaDTO.class)));
        return directivaDTOS;
    }

    @Override
    public ConfiguracioDTO getConfiguracio() {
        Configuracio configuracio = configuracioDao.findById(1l).orElseThrow(() -> new IllegalStateException("No se encontró configuración con ID 1"));
        ConfiguracioDTO config = modelMapper.map(configuracio, ConfiguracioDTO.class);
        return config;
    }

    @Override
    public ConfiguracioGeneralDTO getConfiguracioGeneral() {
        Configuracio configuracio = configuracioDao.findById(1l).orElse(null);

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
        Configuracio configuracio = configuracioDao.findById(1l).orElse(null);
        configuracio = jConfiguracioMapper(configuracioDTO);
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
        return jugadorDao.save(jugador).getId();
    }

    @Override
    public void deleteJugador(Long id) {
        jugadorDao.deleteById(id);
    }

    @Override
    public List<JugadorDTO> listAllJugadors(Filtre filtre) {
        List<Jugador> jugadors = jugadorDao.findAllByCampanyaAndEquip(filtre.getCampanyaActiva(), filtre.getEquipActiu());
        List<JugadorDTO> jugadorDTOS = new ArrayList<>();
        if (!jugadors.isEmpty()) {
            jugadorDTOS = jugadors.stream().map(jugador -> modelMapper.map(jugador, JugadorDTO.class)).collect(Collectors.toList());
        }
        return jugadorDTOS;
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

    @Override
    public Long saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        categoria = categoriaDao.save(categoria);

        if (categoriaDTO.getId() == null || categoriaDTO.getEquips().stream().anyMatch(e -> e.getId() == null)) {

            Campanya campanya = categoria.getCampanya();
            //En este caso hay que crear mensualidades para cada equipo de la categoria
            // --- Generación automática de mensualidades ---

            Categoria finalCategoria = categoria;
            categoriaDTO.getEquips().stream().filter(e -> e.getId() == null).forEach(e -> {
                Calendar cal = Calendar.getInstance();
                cal.setTime(campanya.getAny());
                Integer anyInici = cal.get(Calendar.YEAR);
                Integer anyFi = anyInici + 1;

                List<Mensualitat> mensualitats = new ArrayList<>();

                for (int mes = 7; mes <= 12; mes++) { // De juliol a desembre
                    Mensualitat mensualitat = new Mensualitat();
                    mensualitat.setCampanya(campanya);
                    mensualitat.setAny(anyInici);
                    mensualitat.setMes(mes);
                    mensualitat.setEquip(finalCategoria.getEquips().stream().filter(eq -> eq.getNom().equals(e.getNom())).findFirst().orElse(null));
                    mensualitats.add(mensualitat);
                }

                for (int mes = 1; mes <= 6; mes++) { // De gener a juny
                    Mensualitat mensualitat = new Mensualitat();
                    mensualitat.setCampanya(campanya);
                    mensualitat.setAny(anyFi);
                    mensualitat.setMes(mes);
                    mensualitat.setEquip(finalCategoria.getEquips().stream().filter(eq -> eq.getNom().equals(e.getNom())).findFirst().orElse(null));
                    mensualitats.add(mensualitat);
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

    private Soci jSociMapper(SociDTO sociDTO) {
        Soci soci = modelMapper.map(sociDTO, Soci.class);

        Campanya campanya = campanyaDao.findById(sociDTO.getCampanya()).orElse(null);
        soci.setCampanya(campanya);

        TipoSoci tipoSoci = jTipoSociMapper(sociDTO.getTipoSoci());
        soci.setTipoSoci(tipoSoci);

        return soci;
    }

    private Patrocinador jPatrocinadorMapper(PatrocinadorDTO patrocinadorDTO) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTO, Patrocinador.class);

        try {
            if (patrocinadorDTO.getLogo() != null && !patrocinadorDTO.getLogo().isEmpty() && mediaService.checkBase64(patrocinadorDTO.getLogo())) {
                String logoUrl = mediaService.guardarLogoBase64(patrocinadorDTO.getLogo());
                patrocinador.setLogo(logoUrl);
            } else if (patrocinadorDTO.getLogo() != null && !patrocinadorDTO.getLogo().isEmpty() && !mediaService.checkBase64(patrocinadorDTO.getLogo())) {
                patrocinador.setLogo(patrocinadorDTO.getLogo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patrocinador;
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


}
