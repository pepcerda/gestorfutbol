package com.jcerdar.gestorfutbol.service;

public interface ExplotacioDadesService {

    Long totalImportSocisPerCampanya(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportSocisPerCampanyaiEstatNoPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanya(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatPagat(Long idCampanya);

    Long totalImportPatrocinadorsPerCampanyaiEstatNoPagat(Long idCampanya);

}
