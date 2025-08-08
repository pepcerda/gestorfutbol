package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.service.model.ExplotacioFacturesDTO;
import com.jcerdar.gestorfutbol.service.model.ExplotacioPatrocinadorsDTO;
import com.jcerdar.gestorfutbol.service.model.ExplotacioSocisDTO;

public interface ExplotacioDadesService {

    Long totalImportSocisPerCampanya(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatNoPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanya(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatNoPagat(Long idCampanya);

    ExplotacioPatrocinadorsDTO getDadesExplotacioPatrocinis(Long idCampanya);

    ExplotacioSocisDTO getDadesExplotacioSocis(Long idCampanya);

    ExplotacioFacturesDTO getDadesExplotacioFactures(Long idCampanya);

}
