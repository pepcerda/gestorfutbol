package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.PdfGeneradorService;
import com.jcerdar.gestorfutbol.service.model.PlantillaDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PlantillesController extends BaseController {

    @Autowired
    private PdfGeneradorService pdfGeneradorService;

    @GetMapping("/plantilles/{tenantId}")
    public ResponseEntity<?> getAllPlantillesDocuments(@PathVariable Long tenantId) {
        Filtre filtre = new Filtre();
        filtre.setTenantId(tenantId);
        return ResponseEntity.ok(pdfGeneradorService.getAllPlantillesDocuments(filtre));
    }

    @PostMapping("/obtenir-plantilla")
    public ResponseEntity<PlantillaDocumentDTO> getPlantillaDocumentByCodi(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(pdfGeneradorService.getPlantillaDocumentByCodi(filtre));
    }

    @PostMapping("/plantilla")
    public ResponseEntity<Long> savePlantillaDocument(@RequestBody PlantillaDocumentDTO plantillaDocumentDTO) {
        return ResponseEntity.ok(pdfGeneradorService.savePlantillaDocument(plantillaDocumentDTO));
    }


}
