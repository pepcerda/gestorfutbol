package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.*;

import java.util.List;

public interface GestorFutbolService {

    PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre);

    List<CampanyaDTO> listAllCampanyas();

    Long saveCampanya(CampanyaDTO campanyaDTO);

    void deleteCampanya(Long id);

    PaginaDTO<List<SociDTO>> listSocis(Filtre filtre);

    Long saveSoci(SociDTO sociDTO);

    void deleteSoci(Long id);

    PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre);

    Long savePatrocinador(PatrocinadorDTO patrocinadorDTO);

    void deletePatrocinador(Long id);

    String getReceipt(Long id);

    List<RolDirectiuDTO> listRolsDirectiu();

    PaginaDTO<List<DirectiuDTO>> listDirectius(Filtre filtre);

    Long saveDirectiu(DirectiuDTO directiuDTO);

    void deleteDirectiu(Long id);

    void baixaDirectiva(BaixaDTO baixaDTO);

    Long saveDirectiva(DirectivaDTO directivaDTO);

    DirectivaDTO listDirectiva();

    Boolean checkDirectiva();

}
