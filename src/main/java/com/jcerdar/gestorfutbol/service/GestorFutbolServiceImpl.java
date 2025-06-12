package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.service.model.*;
import com.jcerdar.gestorfutbol.service.util.PdfUtil;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private ModelMapper modelMapper;

    @Autowired
    private MediaService mediaService; 

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<SociDTO, Soci> jSociMapper = modelMapper.createTypeMap(SociDTO.class, Soci.class);
        jSociMapper.addMappings(mapper -> mapper.skip(Soci::setCampanya));

        TypeMap<Soci, SociDTO> sociMapper = modelMapper.createTypeMap(Soci.class, SociDTO.class);
        sociMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), SociDTO::setCampanya));

        TypeMap<PatrocinadorDTO, Patrocinador> jPatrociniMapper = modelMapper.createTypeMap(PatrocinadorDTO.class, Patrocinador.class);
        jPatrociniMapper.addMappings(mapper -> mapper.skip(Patrocinador::setCampanya));

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

        TypeMap<CaixaFixaDTO, CaixaFixa> jCaixaFixaMapper = modelMapper.createTypeMap(CaixaFixaDTO.class, CaixaFixa.class);
        jCaixaFixaMapper.addMappings(mapper -> mapper.skip(CaixaFixa::setFactura));
    }

    @Override
    public PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre) {
        Page<Campanya> campanyas = campanyaDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
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
        List<Campanya> campanyas = campanyaDao.findAll();
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
        if(socis.isEmpty()) {
            socis.forEach(s -> sociDao.deleteById(s.getId()));
        }

        List<Patrocinador> patrocinadors = patrocinadorDao.findAllByCampanyaId(id);
        if(patrocinadors.isEmpty()) {
            patrocinadors.forEach(p -> patrocinadorDao.deleteById(p.getId()));
        }

        campanyaDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<SociDTO>> listSocis(Filtre filtre) {

        Page<Soci> socis = sociDao.findAllByCampanya(filtre.getCampanyaActiva(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
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
    public Long saveSoci(SociDTO sociDTO) {
        Soci soci = jSociMapper(sociDTO);
        soci = sociDao.save(soci);
        return soci.getId();
    }

    @Override
    public void deleteSoci(Long id) {
        sociDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre) {
        Page<Patrocinador> patrocinadors = patrocinadorDao.findAllByCampanya(filtre.getCampanyaActiva(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
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
    public String getReceipt(Long id) {
        Patrocinador patrocinador = patrocinadorDao.findById(id).orElse(null);
        if (patrocinador != null) {
            PatrocinadorDTO patrocinadorDTO = modelMapper.map(patrocinador, PatrocinadorDTO.class);
            return PdfUtil.generatePdf(patrocinadorDTO);
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
        Configuracio configuracio = configuracioDao.findById(1l)
                .orElseThrow(() -> new IllegalStateException("No se encontró configuración con ID 1"));
        ConfiguracioDTO config = modelMapper.map(configuracio, ConfiguracioDTO.class);
        return config;
    }

    @Override
    public ConfiguracioGeneralDTO getConfiguracioGeneral() {
        Configuracio configuracio = configuracioDao.findById(1l)
                .orElse(null);

        if(configuracio != null) {
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
    public PaginaDTO<List<CaixaFixaDTO>> listFactures(Filtre filtre) {
        Page<CaixaFixa> caixaFixas = caixaFixaDao.findAllByCampanya(filtre.getCampanyaActiva(), PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<CaixaFixaDTO>> paginaDTO = new PaginaDTO<>();
        List<CaixaFixaDTO> caixaFixaDTOS = new ArrayList<>();
        if (caixaFixas.getTotalElements() > 0) {
            caixaFixaDTOS = caixaFixas.map(c -> modelMapper.map(c, CaixaFixaDTO.class)).getContent();
            paginaDTO.setTotal(caixaFixas.getTotalElements());
            paginaDTO.setResult(caixaFixaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long saveCaixaFixa(CaixaFixaDTO caixaFixaDTO) {
        CaixaFixa caixaFixa = jCaixaFixaMapper(caixaFixaDTO);
        caixaFixa = caixaFixaDao.save(caixaFixa);
        return caixaFixa.getId();
    }

    @Override
    public void deleteCaixaFixa(Long id) {
        caixaFixaDao.deleteById(id);
        //TODO: No eliminamos el fichero asociado a la fila de BBDD.
    }

    private Soci jSociMapper(SociDTO sociDTO) {
        Soci soci = modelMapper.map(sociDTO, Soci.class);

        Campanya campanya = campanyaDao.findById(sociDTO.getCampanya()).orElse(null);
        soci.setCampanya(campanya);

        return soci;
    }

    private Patrocinador jPatrocinadorMapper(PatrocinadorDTO patrocinadorDTO) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTO, Patrocinador.class);

        Campanya campanya = campanyaDao.findById(patrocinadorDTO.getCampanya()).orElse(null);
        patrocinador.setCampanya(campanya);

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

    private CaixaFixa jCaixaFixaMapper(CaixaFixaDTO caixaFixaDTO) {
        CaixaFixa caixaFixa = modelMapper.map(caixaFixaDTO, CaixaFixa.class);
        try {
            if (caixaFixaDTO.getFactura() != null && !caixaFixaDTO.getFactura().isEmpty()) {
                String facturaUrl = mediaService.guardarDespesaB64(caixaFixaDTO.getFactura());
                caixaFixa.setFactura(facturaUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caixaFixa;
    }


}
