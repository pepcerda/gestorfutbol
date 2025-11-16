package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DespesaService {

    PaginaDTO<List<CaixaFixaDTO>> listCaixaFixa(Filtre filtre);

    Long saveCaixaFixa(CaixaFixaDTO caixaFixaDTO, MultipartFile fitxer);

    void deleteCaixaFixa(Long id);

    Long saveNomina(NominaDTO nominaDTO);

    void deleteNomina(Long id);

    Long saveMensualitat(MensualitatDTO mensualitatDTO);

    void deleteMensualitat(Long id);

    List<MensualitatDTO> listAllMensualitats(Filtre filtre);

    List<ProveidorDTO> listAllProveidors();

    PaginaDTO<List<ProveidorDTO>> listProveidors(Filtre filtre);

    Long saveProveidor(ProveidorDTO proveidorDTO);

    void deleteProveidor(Long id);

    PaginaDTO<List<FacturaDTO>> listFactures(Filtre filtre);

    Long saveFactura(FacturaDTO facturaDTO, MultipartFile fitxer);

    void deleteFactura(Long id);

    List<CategoriaDespesaDTO> listAllCategoriesDespesa();

    PaginaDTO<List<CategoriaDespesaDTO>> listCategoriesDespesa(Filtre filtre);

    Long saveCategoriaDespesa(CategoriaDespesaDTO categoriaDespesaDTO);

    void deleteCategoriaDespesa(Long id);
}
