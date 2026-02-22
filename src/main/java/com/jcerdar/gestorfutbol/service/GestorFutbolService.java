package com.jcerdar.gestorfutbol.service;

import java.util.List;

import com.jcerdar.gestorfutbol.service.model.*;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.model.Tenant;

public interface GestorFutbolService {

    PaginaDTO<List<TenantDTO>> listTenants(Filtre filtre);

    List<TenantDTO> listAllTenants();

    Long saveTenant(TenantDTO tenantDTO);

    void deleteTenant(Long id);

    PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre);

    List<CampanyaDTO> listAllCampanyas(Filtre filtre);

    Long saveCampanya(CampanyaDTO campanyaDTO);

    void deleteCampanya(Long id);

    List<RolDirectiuDTO> listRolsDirectiu(Filtre filtre);

    PaginaDTO<List<DirectiuDTO>> listDirectius(Filtre filtre);

    Long saveDirectiu(DirectiuDTO directiuDTO);

    void deleteDirectiu(Long id);

    void baixaDirectiva(BaixaDTO baixaDTO);

    Long saveDirectiva(DirectivaDTO directivaDTO);

    DirectivaDTO listDirectiva(Filtre filtre);

    Boolean checkDirectiva(Filtre filtre);

    List<DirectivaDTO> listHistoricDirectiva(Filtre filtre);

    ConfiguracioDTO getConfiguracio(Filtre filtre);

    ConfiguracioGeneralDTO getConfiguracioGeneral(Filtre filtre);

    Long saveConfiguracio(ConfiguracioDTO configuracioDTO);

    PaginaDTO<List<TipoSociDTO>> listTipoSocis(Filtre filtre);

    List<TipoSociDTO> listAllTipoSocis(Filtre filtre);

    Long saveTipoSoci(TipoSociDTO tipoSociDTO);

    void deleteTipoSoci(Long id);

    List<MembrePlantillaDTO> listAllMembresPlantilla(Filtre filtre);

    Long saveJugador(JugadorDTO jugadorDTO);

    void deleteJugador(Long id);

    List<JugadorDTO> listAllJugadors(Filtre filtre);

    List<PosicioDTO> listAllPosicions();

    PaginaDTO<List<PosicioDTO>> listPosicions(Filtre filtre);

    Long savePosicio(PosicioDTO posicioDTO);

    void deletePosicio(Long id);

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

    List<EquipDTO> listAllEquips(Long idCampanya);

}
