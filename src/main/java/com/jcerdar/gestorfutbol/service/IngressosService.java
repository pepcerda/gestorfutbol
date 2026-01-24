package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.QuotaJugadorDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;

import java.util.List;

public interface IngressosService {
    PaginaDTO<List<SociDTO>> listSocis(Filtre filtre);

    List<SociDTO> listAllSocis(Filtre filtre);

    Long saveSoci(SociDTO sociDTO);

    void deleteSoci(Long id);

    PaginaDTO<List<PatrocinadorDTO>> listPatrocinadors(Filtre filtre);

    List<PatrocinadorDTO> listAllPatrocinadors(Filtre filtre);

    Long savePatrocinador(PatrocinadorDTO patrocinadorDTO);

    void deletePatrocinador(Long id);

    Long duplicarPatrocinador(PatrocinadorDTO patrocinadorDTO, Long idCampanya);

    String getReceipt(Long id);

    PaginaDTO<List<QuotaJugadorDTO>> listQuotesJugador(Filtre filtre);

    List<QuotaJugadorDTO> listAllQuotesJugador(Filtre filtre);

    Long saveQuotaJugador(QuotaJugadorDTO quotaJugadorDTO);

    void deleteQuotaJugador(Long id);

}
