package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.*;

public interface ExplotacioDadesService {

    Long totalImportSocisPerCampanya(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatNoPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanya(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatNoPagat(Long idCampanya);

    ExplotacioPatrocinadorsDTO getDadesExplotacioPatrocinis(Long idCampanya);

    ExplotacioSocisDTO getDadesExplotacioSocis(Long idCampanya);

    ExplotacioCaixaFixaDTO getDadesExplotacioCaixaFixa(Long idCampanya);

    ExplotacioQuotesDTO getDadesExplotacioQuotes(Long idCampanya, Long idEquip);

    ExplotacioNominesDTO getDadesExplotacioNomines(Long idCampanya);

    ExplotacioFacturesDTO getDadesExplotacioFactures(Long idCampanya);

}
