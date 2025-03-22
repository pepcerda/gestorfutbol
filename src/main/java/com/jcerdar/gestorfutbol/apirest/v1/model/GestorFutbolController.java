package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.GestorFutbolService;
import com.jcerdar.gestorfutbol.service.model.CampanyaDTO;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gestor-futbol/api/v1")
public class GestorFutbolController {

    @Autowired
    private GestorFutbolService gestorFutbolService;

    @GetMapping("/campanyas")
    public ResponseEntity<List<CampanyaDTO>> listCampanyas() {
        return ResponseEntity.ok(gestorFutbolService.listAllCampanyas());
    }

    @PostMapping("/campanyas")
    public ResponseEntity<PaginaDTO<List<CampanyaDTO>>> listCampanyas(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listCampanyas(filtre));
    }

    @PostMapping("/campanya")
    public ResponseEntity<Long> saveCampanya(@RequestBody CampanyaDTO campanyaDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveCampanya(campanyaDTO));
    }

    @DeleteMapping("/campanya/{id}")
    public ResponseEntity<String> deleteCampanya(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteCampanya(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/socis")
    public ResponseEntity<PaginaDTO<List<SociDTO>>> listSocis(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listSocis(filtre));
    }

    @PostMapping("/soci")
    public ResponseEntity<Long> saveSoci(@RequestBody SociDTO sociDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveSoci(sociDTO));
    }

    @DeleteMapping("/soci/{id}")
    public ResponseEntity<String> deleteSoci(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteSoci(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/patrocinadors")
    public ResponseEntity<PaginaDTO<List<PatrocinadorDTO>>> listPatrocinadors(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listPatrocinador(filtre));
    }

    @PostMapping("/patrocinador")
    public ResponseEntity<Long> savePatrocinador(@RequestBody PatrocinadorDTO patrocinadorDTO) {
        return ResponseEntity.ok(gestorFutbolService.savePatrocinador(patrocinadorDTO));
    }

    @DeleteMapping("/patrocinador/{id}")
    public ResponseEntity<String> deletePatrocinador(@PathVariable Long id) {
        try {
            gestorFutbolService.deletePatrocinador(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patrocinador/rebut/{id}")
    public ResponseEntity<String> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok(gestorFutbolService.getReceipt(id));
    }


}
