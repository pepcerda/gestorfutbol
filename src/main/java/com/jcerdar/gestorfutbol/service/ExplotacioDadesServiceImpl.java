package com.jcerdar.gestorfutbol.service;


import com.jcerdar.gestorfutbol.persistence.dao.*;
import com.jcerdar.gestorfutbol.persistence.model.type.EstatPagament;
import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplotacioDadesServiceImpl implements ExplotacioDadesService{

    @Autowired
    private SociDao sociDao;

    @Autowired
    private PatrocinadorDao patrocinadorDao;

    @Autowired
    private CaixaFixaDao caixaFixaDao;

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private NominaDao nominaDao;

    @Autowired
    private QuotaJugadorDao quotaJugadorDao;


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

    @Override
    public ExplotacioPatrocinadorsDTO getDadesExplotacioPatrocinis(Long idCampanya) {
        ExplotacioPatrocinadorsDTO explotacioPatrocinadorsDTO = new ExplotacioPatrocinadorsDTO();
        explotacioPatrocinadorsDTO.setTotalRecaptat(patrocinadorDao.sumaDonacionsPerEstatICampanya(idCampanya, "P"));
        explotacioPatrocinadorsDTO.setPrevisioRecaptacio(patrocinadorDao.sumaDonacionsPerCampanya(idCampanya));
        explotacioPatrocinadorsDTO.setTotalPatrocinadors(patrocinadorDao.countPatrocinadorsByCampanya(idCampanya));
        return explotacioPatrocinadorsDTO;
    }

    @Override
    public ExplotacioSocisDTO getDadesExplotacioSocis(Long idCampanya) {
        ExplotacioSocisDTO explotacioSocisDTO = new ExplotacioSocisDTO();
        explotacioSocisDTO.setTotalRecaptat(sociDao.totalRecaudacioPerCampanyaIEstat(idCampanya, "P"));
        explotacioSocisDTO.setPrevisioRecaptacio(sociDao.totalRecaudacioPerCampanya(idCampanya));
        explotacioSocisDTO.setTotalSocis(sociDao.countSocisByCampanya(idCampanya));
        return explotacioSocisDTO;
    }

    @Override
    public ExplotacioCaixaFixaDTO getDadesExplotacioCaixaFixa(Long idCampanya) {
        ExplotacioCaixaFixaDTO explotacioFacturesDTO = new ExplotacioCaixaFixaDTO();
        explotacioFacturesDTO.setTotalPagat(caixaFixaDao.sumaFacturesPerEstatICampanya(idCampanya, "P"));
        explotacioFacturesDTO.setPendentPagar(caixaFixaDao.sumaFacturesPerEstatICampanya(idCampanya, "D"));
        explotacioFacturesDTO.setTotalFactures(caixaFixaDao.sumaFacturesPerCampanya(idCampanya));
        return explotacioFacturesDTO;
    }

    @Override
    public ExplotacioQuotesDTO getDadesExplotacioQuotes(Long idCampanya, Long idEquip) {
        ExplotacioQuotesDTO explotacioQuotesDTO = new ExplotacioQuotesDTO();
        explotacioQuotesDTO.setTotalRecaptat(quotaJugadorDao.totalRecaudacioPerCampanyaIEquipIEstat(idCampanya, idEquip, EstatPagament.PAGADA));
        explotacioQuotesDTO.setPrevisioQuotes(quotaJugadorDao.totalRecaudacioPerCampanyaIEquip(idCampanya, idEquip));
        return explotacioQuotesDTO;
    }

    @Override
    public ExplotacioNominesDTO getDadesExplotacioNomines(Long idCampanya) {
        ExplotacioNominesDTO explotacioNominesDTO = new ExplotacioNominesDTO();
        explotacioNominesDTO.setTotalNominesPagades(nominaDao.sumaNominesPagadesPerCampanya(idCampanya, EstatPagament.PAGADA));
        explotacioNominesDTO.setTotalNominesPendents(nominaDao.sumaNominesPagadesPerCampanya(idCampanya, EstatPagament.PENDENT));
        return explotacioNominesDTO;
        
    }

    @Override
    public ExplotacioFacturesDTO getDadesExplotacioFactures(Long idCampanya) {
        ExplotacioFacturesDTO explotacioFacturesDTO = new ExplotacioFacturesDTO();
        explotacioFacturesDTO.setTotalPagat(facturaDao.sumaFacturesPerEstatICampanya(idCampanya, EstatPagament.PAGADA));
        explotacioFacturesDTO.setPendentPagar(facturaDao.sumaFacturesPerEstatICampanya(idCampanya, EstatPagament.PENDENT));
        explotacioFacturesDTO.setTotalFactures(facturaDao.sumaFacturesPerCampanya(idCampanya));
        return explotacioFacturesDTO;
    }

    @Override
    public ExplotacioQuotesDTO getDadesExplotacioQuotes(Long idCampanya, Long idEquip) {
        ExplotacioQuotesDTO explotacioQuotesDTO = new ExplotacioQuotesDTO();
        explotacioQuotesDTO.setTotalRecaptat(quotaJugadorDao.totalRecaudacioPerCampanyaIEstat(idCampanya, EstatPagament.PAGADA));
        explotacioQuotesDTO.setTotalRecaptatPerEquip(quotaJugadorDao.totalRecaudacioPerCampanyaIEquipIEstat(idCampanya, idEquip, EstatPagament.PAGADA));
        explotacioQuotesDTO.setPrevisioRecaptacioPerEquip(quotaJugadorDao.totalRecaudacioPerCampanyaIEquip(idCampanya, idEquip));
        return explotacioQuotesDTO;
    }
}
