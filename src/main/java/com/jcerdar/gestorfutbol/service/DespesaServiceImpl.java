package com.jcerdar.gestorfutbol.service;


import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.*;
import com.jcerdar.gestorfutbol.persistence.model.type.Posicio;
import com.jcerdar.gestorfutbol.service.model.*;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaServiceImpl implements DespesaService{

    @Autowired
    private CampanyaDao campanyaDao;

    @Autowired
    private CaixaFixaDao caixaFixaDao;

    @Autowired
    private MensualitatDao mensualitatDao;

    @Autowired
    private NominaDao nominaDao;

    @Autowired
    private MembrePlantillaDao membrePlantillaDao;

    @Autowired
    private ProveidorDao proveidorDao;

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private EquipDao equipDao;

    @Autowired
    private CategoriaDespesaDao categoriaDespesaDao;

    @Autowired
    MediaService mediaService;

    @Autowired
    ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<Long, Campanya> toCampanya = ctx -> {
            Campanya campanya = campanyaDao.findById(ctx.getSource()).orElse(null);
            return campanya;
        };

        Converter<Long, Equip> toEquip = ctx -> {
            Equip equip = equipDao.findById(ctx.getSource()).orElse(null);
            return equip;
        };

        Converter<Long, Mensualitat> toMensualitat = ctx -> {
            Mensualitat mensualitat = mensualitatDao.findById(ctx.getSource()).orElse(null);
            return mensualitat;
        };

        Converter<Long, MembrePlantilla> toMembrePlantilla = ctx -> {
            MembrePlantilla membrePlantilla = membrePlantillaDao.findById(ctx.getSource()).orElse(null);
            return membrePlantilla;
        };

        TypeMap<CaixaFixa, CaixaFixaDTO> caixaFixaMapper = modelMapper.createTypeMap(CaixaFixa.class, CaixaFixaDTO.class);
        caixaFixaMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), CaixaFixaDTO::setCampanya));

        TypeMap<CaixaFixaDTO, CaixaFixa> jCaixaFixaMapper = modelMapper.createTypeMap(CaixaFixaDTO.class, CaixaFixa.class);
        jCaixaFixaMapper.addMappings(mapper -> mapper.skip(CaixaFixa::setFactura));
        jCaixaFixaMapper.addMappings(mapper -> mapper.using(toCampanya).map(CaixaFixaDTO::getCampanya, CaixaFixa::setCampanya));

        TypeMap<MensualitatDTO, Mensualitat> jMensualitatMapper = modelMapper.createTypeMap(MensualitatDTO.class, Mensualitat.class);
        jMensualitatMapper.addMappings(mapper -> mapper.using(toCampanya).map(MensualitatDTO::getCampanya, Mensualitat::setCampanya));
        jMensualitatMapper.addMappings(mapper -> mapper.using(toEquip).map(MensualitatDTO::getEquip, Mensualitat::setEquip));

        TypeMap<Mensualitat, MensualitatDTO> mensualitatMapper = modelMapper.createTypeMap(Mensualitat.class, MensualitatDTO.class);
        mensualitatMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), MensualitatDTO::setCampanya));
        mensualitatMapper.addMappings(mapper -> mapper.map(src -> src.getEquip().getId(), MensualitatDTO::setEquip));

        TypeMap<NominaDTO, Nomina> jNominaMapper = modelMapper.createTypeMap(NominaDTO.class, Nomina.class);
        jNominaMapper.addMappings(mapper -> mapper.using(toMembrePlantilla).map(NominaDTO::getMembre, Nomina::setMembre));
        jNominaMapper.addMappings(mapper -> mapper.using(toMensualitat).map(NominaDTO::getMensualitat, Nomina::setMensualitat));

        TypeMap<Nomina, NominaDTO> nominaMapper = modelMapper.createTypeMap(Nomina.class, NominaDTO.class);
        nominaMapper.addMappings(mapper -> mapper.map(src -> src.getMembre().getId(), NominaDTO::setMembre));
        nominaMapper.addMappings(mapper -> mapper.map(src -> src.getMensualitat().getId(), NominaDTO::setMensualitat));

        TypeMap<Factura, FacturaDTO> facturaMapper = modelMapper.createTypeMap(Factura.class, FacturaDTO.class);
        facturaMapper.addMappings(mapper -> mapper.map(src -> src.getCampanya().getId(), FacturaDTO::setCampanya));

        TypeMap<FacturaDTO, Factura> jFacturaMapper = modelMapper.createTypeMap(FacturaDTO.class, Factura.class);
        jFacturaMapper.addMappings(mapper -> mapper.skip(Factura::setDocument));
        jFacturaMapper.addMappings(mapper -> mapper.using(toCampanya).map(FacturaDTO::getCampanya, Factura::setCampanya));

    }

    @Override
    public PaginaDTO<List<CaixaFixaDTO>> listCaixaFixa(Filtre filtre) {
        Page<CaixaFixa> caixaFixas = caixaFixaDao.buscarConFiltros(filtre);
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
    public Long saveCaixaFixa(CaixaFixaDTO caixaFixaDTO, MultipartFile fitxer) {
        CaixaFixa caixaFixa = jCaixaFixaMapper(caixaFixaDTO, fitxer);
        caixaFixa = caixaFixaDao.save(caixaFixa);
        return caixaFixa.getId();
    }

    @Override
    public void deleteCaixaFixa(Long id) {
        caixaFixaDao.deleteById(id);
        //TODO: No eliminamos el fichero asociado a la fila de BBDD.
    }

    @Override
    public Long saveNomina(NominaDTO nominaDTO) {
        Nomina nomina = modelMapper.map(nominaDTO, Nomina.class);
        return nominaDao.save(nomina).getId();
    }

    @Override
    public void deleteNomina(Long id) {
        nominaDao.deleteById(id);
    }

    @Override
    public Long saveMensualitat(MensualitatDTO mensualitatDTO) {
        Mensualitat mensualitat = modelMapper.map(mensualitatDTO, Mensualitat.class);
        return mensualitatDao.save(mensualitat).getId();
    }

    @Override
    public void deleteMensualitat(Long id) {
        mensualitatDao.deleteById(id);
    }

    @Override
    public List<MensualitatDTO> listAllMensualitats(Filtre filtre) {
        List<Mensualitat> mensualitats = mensualitatDao.findMensualitatsByEquipAndCampanya(filtre.getEquipActiu(), filtre.getCampanyaActiva());
        List<MensualitatDTO> mensualitatDTOS = new ArrayList<>();
        if (!mensualitats.isEmpty()) {
            mensualitatDTOS = mensualitats.stream().map(c -> modelMapper.map(c, MensualitatDTO.class)).collect(Collectors.toList());
        }
        return mensualitatDTOS;
    }

    @Override
    public List<ProveidorDTO> listAllProveidors() {
        List<Proveidor> proveidors = proveidorDao.findAll();
        List<ProveidorDTO> proveidorDTOS = new ArrayList<>();
        if (!proveidors.isEmpty()) {
            proveidorDTOS = proveidors.stream().map(c -> modelMapper.map(c, ProveidorDTO.class)).collect(Collectors.toList());
        }
        return proveidorDTOS;

    }

    @Override
    public PaginaDTO<List<ProveidorDTO>> listProveidors(Filtre filtre) {
        Page<Proveidor> proveidors = proveidorDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<ProveidorDTO>> paginaDTO = new PaginaDTO<>();
        List<ProveidorDTO> proveidorDTOS = new ArrayList<>();
        if (proveidors.getTotalElements() > 0) {
            proveidorDTOS = proveidors.map(c -> modelMapper.map(c, ProveidorDTO.class)).getContent();
            paginaDTO.setTotal(proveidors.getTotalElements());
            paginaDTO.setResult(proveidorDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long saveProveidor(ProveidorDTO proveidorDTO) {
        Proveidor proveidor = modelMapper.map(proveidorDTO, Proveidor.class);
        return proveidorDao.save(proveidor).getId();
    }

    @Override
    public void deleteProveidor(Long id) {
        proveidorDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<FacturaDTO>> listFactures(Filtre filtre) {
        Page<Factura> factures = facturaDao.buscarConFiltros(filtre);
        PaginaDTO<List<FacturaDTO>> paginaDTO = new PaginaDTO<>();
        List<FacturaDTO> facturaDTOS = new ArrayList<>();
        if (factures.getTotalElements() > 0) {
            facturaDTOS = factures.map(c -> modelMapper.map(c, FacturaDTO.class)).getContent();
            paginaDTO.setTotal(factures.getTotalElements());
            paginaDTO.setResult(facturaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long saveFactura(FacturaDTO facturaDTO, MultipartFile fitxer) {
        Factura factura = jFacturaMapper(facturaDTO, fitxer);
        factura = facturaDao.save(factura);
        return factura.getId();
    }

    @Override
    public void deleteFactura(Long id) {
        facturaDao.deleteById(id);
    }

    @Override
    public List<CategoriaDespesaDTO> listAllCategoriesDespesa() {
        List<CategoriaDespesa> categoriaDespesas = categoriaDespesaDao.findAll();
        List<CategoriaDespesaDTO> categoriaDespesaDTOS = new ArrayList<>();
        if (!categoriaDespesas.isEmpty()) {
            categoriaDespesaDTOS = categoriaDespesas.stream().map(c -> modelMapper.map(c, CategoriaDespesaDTO.class)).collect(Collectors.toList());
        }
        return categoriaDespesaDTOS;
    }

    @Override
    public PaginaDTO<List<CategoriaDespesaDTO>> listCategoriesDespesa(Filtre filtre) {
        Page<CategoriaDespesa> categoriaDespesas = categoriaDespesaDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<CategoriaDespesaDTO>> paginaDTO = new PaginaDTO<>();
        List<CategoriaDespesaDTO> categoriaDespesaDTOS = new ArrayList<>();
        if (categoriaDespesas.getTotalElements() > 0) {
            categoriaDespesaDTOS = categoriaDespesas.map(c -> modelMapper.map(c, CategoriaDespesaDTO.class)).getContent();
            paginaDTO.setTotal(categoriaDespesas.getTotalElements());
            paginaDTO.setResult(categoriaDespesaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long saveCategoriaDespesa(CategoriaDespesaDTO categoriaDespesaDTO) {
        CategoriaDespesa categoriaDespesa = modelMapper.map(categoriaDespesaDTO, CategoriaDespesa.class);
        return categoriaDespesaDao.save(categoriaDespesa).getId();
    }

    @Override
    public void deleteCategoriaDespesa(Long id) {
        categoriaDespesaDao.deleteById(id);
    }


    private CaixaFixa jCaixaFixaMapper(CaixaFixaDTO caixaFixaDTO, MultipartFile file) {
        CaixaFixa caixaFixa = modelMapper.map(caixaFixaDTO, CaixaFixa.class);

        try {
            if (file != null && !file.isEmpty()) {
                String facturaUrl = mediaService.guardarDespesaMultipart(file);
                caixaFixa.setFactura(facturaUrl);
            } else {
                caixaFixa.setFactura(caixaFixaDTO.getFactura());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caixaFixa;
    }

    private Factura jFacturaMapper(FacturaDTO facturaDTO, MultipartFile file) {
        Factura factura = modelMapper.map(facturaDTO, Factura.class);

        try {
            if (file != null && !file.isEmpty()) {
                String facturaUrl = mediaService.guardarDespesaMultipart(file);
                factura.setDocument(facturaUrl);
            } else {
                factura.setDocument(facturaDTO.getDocument());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factura;
    }
}
