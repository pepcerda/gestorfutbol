package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.ExplotacioDadesService;
import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class ExplotacioDadesController extends BaseController {

    @Autowired
    private ExplotacioDadesService explotacioDadesService;


    @GetMapping("/total-socis/{idCampanya}")
    public ResponseEntity<Long> getTotalImportSocisPerCampanya(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanya(idCampanya));
    }

    @GetMapping("/total-socis-pagat/{idCampanya}")
    public ResponseEntity<Long> getTotalImportSocisPerCampanyaiEstatPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanyaiEstatPagat(idCampanya));
    }

    @GetMapping("/total-socis-no-pagat/{idCampanya}")
    public ResponseEntity<Long> getTotalImportSocisPerCampanyaiEstatNoPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanyaiEstatNoPagat(idCampanya));
    }

    @GetMapping("/total-patrocinis/{idCampanya}")
    public ResponseEntity<Long> getTotalImportPatrocinisPerCampanya(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanya(idCampanya));
    }

    @GetMapping("/total-patrocinis-pagat/{idCampanya}")
    public ResponseEntity<Long> getTotalImportPatrocinisPerCampanyaiEstatPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanyaiEstatPagat(idCampanya));
    }

    @GetMapping("/total-patrocinis-no-pagat/{idCampanya}")
    public ResponseEntity<Long> getTotalImportPatrocinisPerCampanyaiEstatNoPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanyaiEstatNoPagat(idCampanya));
    }

    @GetMapping("/explotacio-patrocinis/{idCampanya}")
    public ResponseEntity<ExplotacioPatrocinadorsDTO> getDadesExplotacioPatrocinis(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioPatrocinis(idCampanya));
    }

    @GetMapping("/explotacio-socis/{idCampanya}")
    public ResponseEntity<ExplotacioSocisDTO> getDadesExplotacioSocis(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioSocis(idCampanya));
    }

    @GetMapping("/explotacio-quotes/{idCampanya}/{idEquip}")
    ResponseEntity<ExplotacioQuotesDTO> getDadesExplotacioQuotes(@PathVariable Long idCampanya, @PathVariable Long idEquip) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioQuotes(idCampanya, idEquip));
    }

    @GetMapping("/explotacio-caixa-fixa/{idCampanya}")
    public ResponseEntity<ExplotacioCaixaFixaDTO> getDadesExplotacioCaixaFixa(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioCaixaFixa(idCampanya));
    }

    @GetMapping("/explotacio-factures/{idCampanya}")
    public ResponseEntity<ExplotacioFacturesDTO> getDadesExplotacioFactures(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioFactures(idCampanya));
    }

    @GetMapping("/explotacio-nomines/{idCampanya}")
    public ResponseEntity<ExplotacioNominesDTO> getDadesExplotacioNomines(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioNomines(idCampanya));
    }

    @GetMapping("/explotacio-quotes/{idCampanya}/{idEquip}")
    public ResponseEntity<ExplotacioQuotesDTO> getDadesExplotacioQuotes(@PathVariable Long idCampanya, @PathVariable Long idEquip) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioQuotes(idCampanya, idEquip));
    }
    

}
