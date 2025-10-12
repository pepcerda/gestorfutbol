package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.ExplotacioDadesService;
import com.jcerdar.gestorfutbol.service.model.ExplotacioFacturesDTO;
import com.jcerdar.gestorfutbol.service.model.ExplotacioNominesDTO;
import com.jcerdar.gestorfutbol.service.model.ExplotacioPatrocinadorsDTO;
import com.jcerdar.gestorfutbol.service.model.ExplotacioSocisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ExplotacioDadesController extends BaseController {

    @Autowired
    private ExplotacioDadesService explotacioDadesService;


    @GetMapping("/total-socis/{idCampanya}")
    ResponseEntity<Long> getTotalImportSocisPerCampanya(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanya(idCampanya));
    }

    @GetMapping("/total-socis-pagat/{idCampanya}")
    ResponseEntity<Long> getTotalImportSocisPerCampanyaiEstatPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanyaiEstatPagat(idCampanya));
    }

    @GetMapping("/total-socis-no-pagat/{idCampanya}")
    ResponseEntity<Long> getTotalImportSocisPerCampanyaiEstatNoPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportSocisPerCampanyaiEstatNoPagat(idCampanya));
    }

    @GetMapping("/total-patrocinis/{idCampanya}")
    ResponseEntity<Long> getTotalImportPatrocinisPerCampanya(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanya(idCampanya));
    }

    @GetMapping("/total-patrocinis-pagat/{idCampanya}")
    ResponseEntity<Long> getTotalImportPatrocinisPerCampanyaiEstatPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanyaiEstatPagat(idCampanya));
    }

    @GetMapping("/total-patrocinis-no-pagat/{idCampanya}")
    ResponseEntity<Long> getTotalImportPatrocinisPerCampanyaiEstatNoPagat(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.totalImportPatrocinadorsPerCampanyaiEstatNoPagat(idCampanya));
    }

    @GetMapping("/explotacio-patrocinis/{idCampanya}")
    ResponseEntity<ExplotacioPatrocinadorsDTO> getDadesExplotacioPatrocinis(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioPatrocinis(idCampanya));
    }

    @GetMapping("/explotacio-socis/{idCampanya}")
    ResponseEntity<ExplotacioSocisDTO> getDadesExplotacioSocis(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioSocis(idCampanya));
    }

    @GetMapping("/explotacio-factures/{idCampanya}")
    ResponseEntity<ExplotacioFacturesDTO> getDadesExplotacioFactures(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioFactures(idCampanya));
    }

    @GetMapping("/explotacio-nomines/{idCampanya}")
    ResponseEntity<ExplotacioNominesDTO> getDadesExplotacioNomines(@PathVariable Long idCampanya) {
        return ResponseEntity.ok(explotacioDadesService.getDadesExplotacioNomines(idCampanya));
    }

}
