package com.jcerdar.gestorfutbol.service;

import java.util.List;

import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.web.multipart.MultipartFile;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;

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

    PaginaDTO<List<TipoSociDTO>> listTipoSocis(Filtre filtre);

    List<TipoSociDTO> listAllTipoSocis(Filtre filtre);

    Long saveTipoSoci(TipoSociDTO tipoSociDTO);

    void deleteTipoSoci(Long id);

    List<MembrePlantillaDTO> listAllMembresPlantilla(Filtre filtre);

    Long saveJugador(JugadorDTO jugadorDTO);

    void deleteJugador(Long id);

    List<JugadorDTO> listAllJugadors(Filtre filtre);

    Long saveEntrenador(EntrenadorDTO entrenadorDTO);

    void deleteEntrenador(Long id);

    List<EntrenadorDTO> listAllEntrenadors(Filtre filtre);

    List<DelegatDTO> listAllDelegats(Filtre filtre);

    Long saveDelegat(DelegatDTO delegatDTO);

    void deleteDelegat(Long id);

    PaginaDTO<List<CategoriaDTO>> listCategoria(Filtre filtre);

    List<CategoriaDTO> listAllCategoria(Filtre filtre);

    Long saveCategoria(CategoriaDTO categoriaDTO);

    void deleteCategoria(Long id);
    
}
