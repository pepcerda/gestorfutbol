package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.service.model.*;
import com.jcerdar.gestorfutbol.service.util.PdfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngressosServiceImpl implements IngressosService {


    @Autowired
    private CampanyaDao campanyaDao;

    @Autowired
    private SociDao sociDao;

    @Autowired
    private PatrocinadorDao patrocinadorDao;

    @Autowired
    private QuotaJugadorDao quotaJugadorDao;

    @Autowired
    private JugadorDao jugadorDao;

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

        TypeMap<QuotaJugadorDTO, QuotaJugador> jQuotaJugadorMapper = modelMapper.createTypeMap(QuotaJugadorDTO.class, QuotaJugador.class);

        TypeMap<QuotaJugador, QuotaJugadorDTO> quotaJugadorMapper = modelMapper.createTypeMap(QuotaJugador.class, QuotaJugadorDTO.class);
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
    public PaginaDTO<List<PatrocinadorDTO>> listPatrocinadors(Filtre filtre) {
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
    public PaginaDTO<List<QuotaJugadorDTO>> listQuotesJugador(Filtre filtre) {
        Page<QuotaJugador> quotaJugadors = quotaJugadorDao.buscarConFiltros(filtre);
        PaginaDTO<List<QuotaJugadorDTO>> paginaDTO = new PaginaDTO<>();
        List<QuotaJugadorDTO> quotaJugadorDTOS = new ArrayList<>();
        if (quotaJugadors.getTotalElements() > 0) {
            quotaJugadorDTOS = quotaJugadors.map(quot -> modelMapper.map(quot, QuotaJugadorDTO.class)).getContent();
            paginaDTO.setTotal(quotaJugadors.getTotalElements());
            paginaDTO.setResult(quotaJugadorDTOS);
        }
        return paginaDTO;
    }

    @Override
    public List<QuotaJugadorDTO> listAllQuotesJugador(Filtre filtre) {
        List<QuotaJugador> quotaJugadors = quotaJugadorDao.buscarConFiltrosAll(filtre);
        List<QuotaJugadorDTO> quotaJugadorDTOS = new ArrayList<>();
        if (!quotaJugadors.isEmpty()) {
            quotaJugadorDTOS = quotaJugadors.stream().map(quot -> modelMapper.map(quot, QuotaJugadorDTO.class)).collect(Collectors.toList());
        }
        return quotaJugadorDTOS;
    }

    @Override
    public Long saveQuotaJugador(QuotaJugadorDTO quotaJugadorDTO) {
        QuotaJugador quotaJugador = modelMapper.map(quotaJugadorDTO, QuotaJugador.class);
        quotaJugador = quotaJugadorDao.save(quotaJugador);
        return quotaJugador.getId();
    }

    @Override
    public void deleteQuotaJugador(Long id) {
        quotaJugadorDao.deleteById(id);
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
}
