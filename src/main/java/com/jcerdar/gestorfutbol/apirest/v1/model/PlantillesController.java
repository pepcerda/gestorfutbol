package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.PdfGeneradorService;
import com.jcerdar.gestorfutbol.service.model.PlantillaDocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PlantillesController extends BaseController {

    @Autowired
    private PdfGeneradorService pdfGeneradorService;

    @GetMapping("/plantilles")
    public ResponseEntity<?> getAllPlantillesDocuments() {
        return ResponseEntity.ok(pdfGeneradorService.getAllPlantillesDocuments());
    }

    @GetMapping("/plantilla/{codi}")
    public ResponseEntity<?> getPlantillaDocumentByCodi(@PathVariable String codi) {
        return ResponseEntity.ok(pdfGeneradorService.getPlantillaDocumentByCodi(codi));
    }

    @PostMapping("/plantilla")
    public ResponseEntity<Long> savePlantillaDocument(@RequestBody PlantillaDocumentDTO plantillaDocumentDTO) {
        return ResponseEntity.ok(pdfGeneradorService.savePlantillaDocument(plantillaDocumentDTO));
    }


}
