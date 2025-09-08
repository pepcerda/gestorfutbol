package com.jcerdar.gestorfutbol.service;

import java.util.List;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.service.model.BaixaDTO;
import com.jcerdar.gestorfutbol.service.model.CaixaFixaDTO;
import com.jcerdar.gestorfutbol.service.model.CampanyaDTO;
import com.jcerdar.gestorfutbol.service.model.ConfiguracioDTO;
import com.jcerdar.gestorfutbol.service.model.ConfiguracioGeneralDTO;
import com.jcerdar.gestorfutbol.service.model.DelegatDTO;
import com.jcerdar.gestorfutbol.service.model.DirectiuDTO;
import com.jcerdar.gestorfutbol.service.model.DirectivaDTO;
import com.jcerdar.gestorfutbol.service.model.EntrenadorDTO;
import com.jcerdar.gestorfutbol.service.model.JugadorDTO;
import com.jcerdar.gestorfutbol.service.model.MensualitatDTO;
import com.jcerdar.gestorfutbol.service.model.NominaDTO;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.RolDirectiuDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;
import com.jcerdar.gestorfutbol.service.model.TipoSociDTO;

public interface GestorFutbolService {

    PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre);

    List<CampanyaDTO> listAllCampanyas();

    Long saveCampanya(CampanyaDTO campanyaDTO);

    void deleteCampanya(Long id);

    PaginaDTO<List<SociDTO>> listSocis(Filtre filtre);

    List<SociDTO> listAllSocis(Filtre filtre);

    Long saveSoci(SociDTO sociDTO);

    void deleteSoci(Long id);

    PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre);

    List<PatrocinadorDTO> listAllPatrocinadors(Filtre filtre);

    Long savePatrocinador(PatrocinadorDTO patrocinadorDTO);

    void deletePatrocinador(Long id);

    Long duplicarPatrocinador(PatrocinadorDTO patrocinadorDTO, Long idCampanya);

    String getReceipt(Long id);

    List<RolDirectiuDTO> listRolsDirectiu();

    PaginaDTO<List<DirectiuDTO>> listDirectius(Filtre filtre);

    Long saveDirectiu(DirectiuDTO directiuDTO);

    void deleteDirectiu(Long id);

    void baixaDirectiva(BaixaDTO baixaDTO);

    Long saveDirectiva(DirectivaDTO directivaDTO);

    DirectivaDTO listDirectiva();

    Boolean checkDirectiva();

    List<DirectivaDTO> listHistoricDirectiva();

    ConfiguracioDTO getConfiguracio();

    ConfiguracioGeneralDTO getConfiguracioGeneral();

    Long saveConfiguracio(ConfiguracioDTO configuracioDTO);

    PaginaDTO<List<CaixaFixaDTO>> listFactures(Filtre filtre);

    Long saveCaixaFixa(CaixaFixaDTO caixaFixaDTO);

    void deleteCaixaFixa(Long id);

    PaginaDTO<List<TipoSociDTO>> listTipoSocis(Filtre filtre);

    List<TipoSociDTO> listAllTipoSocis(Filtre filtre);

    Long saveTipoSoci(TipoSociDTO tipoSociDTO);

    void deleteTipoSoci(Long id);

    Long saveJugador(JugadorDTO jugadorDTO);

    void deleteJugador(Long id);

    List<JugadorDTO> listAllJugadors(Filtre filtre);

    Long saveEntrenador(EntrenadorDTO entrenadorDTO);

    void deleteEntrenador(Long id);

    List<EntrenadorDTO> listAllEntrenadors(Filtre filtre);

    List<DelegatDTO> listAllDelegats(Filtre filtre);

    Long saveDelegat(DelegatDTO delegatDTO);

    void deleteDelegat(Long id);

    Long saveNomina(NominaDTO nominaDTO);

    void deleteNomina(Long id);

    Long saveMensualitat(MensualitatDTO mensualitatDTO);

    void deleteMensualitat(Long id);

    List<MensualitatDTO> listAllMensualitats(Filtre filtre);
    
}
