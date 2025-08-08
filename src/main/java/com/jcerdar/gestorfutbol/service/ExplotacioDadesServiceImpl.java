package com.jcerdar.gestorfutbol.service;


import com.jcerdar.gestorfutbol.persistence.dao.PatrocinadorDao;
import com.jcerdar.gestorfutbol.persistence.dao.SociDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplotacioDadesServiceImpl implements ExplotacioDadesService{

    @Autowired
    private SociDao sociDao;

    @Autowired
    private PatrocinadorDao patrocinadorDao;


    @Override
    public Long totalImportSocisPerCampanya(Long idCampanya) {
        return sociDao.totalRecaudacioPerCampanya(idCampanya);
    }

    @Override
    public Long totalImportSocisPerCampanyaiEstatPagat(Long idCampanya) {
        return sociDao.totalRecaudacioPerCampanyaIEstat(idCampanya, "P");
    }

    @Override
    public Long totalImportSocisPerCampanyaiEstatNoPagat(Long idCampanya) {
        return sociDao.totalRecaudacioPerCampanyaIEstat(idCampanya, "D");
    }

    @Override
    public Long totalImportPatrocinadorsPerCampanya(Long idCampanya) {
        return patrocinadorDao.sumaDonacionsPerCampanya(idCampanya);
    }

    @Override
    public Long totalImportPatrocinadorsPerCampanyaiEstatPagat(Long idCampanya) {
        return patrocinadorDao.sumaDonacionsPerEstatICampanya(idCampanya, "P");
    }

    @Override
    public Long totalImportPatrocinadorsPerCampanyaiEstatNoPagat(Long idCampanya) {
        return patrocinadorDao.sumaDonacionsPerEstatICampanya(idCampanya, "D");
    }
}
