package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.CampanyaDTO;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;

import java.util.List;

public interface GestorFutbolService {

    PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre);

    Long getCampanyaActivaId();

    Long saveCampanya(CampanyaDTO campanyaDTO);

    void deleteCampanya(Long id);

    PaginaDTO<List<SociDTO>> listSocis(Filtre filtre);

    Long saveSoci(SociDTO sociDTO);

    void deleteSoci(Long id);

    PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre);

    Long savePatrocinador(PatrocinadorDTO patrocinadorDTO);

    void deletePatrocinador(Long id);

}
