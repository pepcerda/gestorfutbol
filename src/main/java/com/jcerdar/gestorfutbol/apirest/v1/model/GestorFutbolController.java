package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.GestorFutbolService;
import com.jcerdar.gestorfutbol.service.MediaService;
import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class GestorFutbolController extends BaseController{

    @Autowired
    private GestorFutbolService gestorFutbolService;

    @Autowired
    private MediaService mediaService; 

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

    @PostMapping("/all-socis")
    public ResponseEntity<List<SociDTO>> listAllSocis(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllSocis(filtre));
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

    @PostMapping("/all-patrocinadors")
    public ResponseEntity<List<PatrocinadorDTO>> listAllPatrocinadors(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllPatrocinadors(filtre));
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

    @PostMapping("/duplica-patrocinador/{id}")
    public ResponseEntity<Long> duplicaPatrocinador(@PathVariable Long id, @RequestBody PatrocinadorDTO patrocinadorDTO) {
        return ResponseEntity.ok(gestorFutbolService.duplicarPatrocinador(patrocinadorDTO, id));
    }

    @GetMapping("/patrocinador/rebut/{id}")
    public ResponseEntity<String> getReceipt(@PathVariable Long id) {
        return ResponseEntity.ok(gestorFutbolService.getReceipt(id));
    }

    @GetMapping("/rols-directius")
    public ResponseEntity<List<RolDirectiuDTO>> listRolsDirectius() {
        return ResponseEntity.ok(gestorFutbolService.listRolsDirectiu());
    }

    @PostMapping("/directius")
    public ResponseEntity<PaginaDTO<List<DirectiuDTO>>> listDirectius(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listDirectius(filtre));
    }

    @PostMapping("/directiu")
    public ResponseEntity<Long> saveDirectiu(@RequestBody DirectiuDTO directiuDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveDirectiu(directiuDTO));
    }

    @DeleteMapping("/directiu/{id}")
    public ResponseEntity<String> deleteDirectiu(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteDirectiu(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check-directiva")
    public ResponseEntity<Boolean> checkDirectiva() {
        return ResponseEntity.ok(gestorFutbolService.checkDirectiva());
    }

    @GetMapping("/directiva")
    public ResponseEntity<DirectivaDTO> listDirectiva() {
        return ResponseEntity.ok(gestorFutbolService.listDirectiva());
    }

    @PostMapping("/directiva")
    public ResponseEntity<Long> saveDirectiva(@RequestBody DirectivaDTO directivaDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveDirectiva(directivaDTO));
    }

    @PostMapping("/directiva-baixa")
    public ResponseEntity<String> baixaDirectiva(@RequestBody BaixaDTO baixaDTO) {
        try {
            gestorFutbolService.baixaDirectiva(baixaDTO);
            return ResponseEntity.ok("Direcitva donada de baixa correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/directiva-historic")
    public ResponseEntity<List<DirectivaDTO>> listHistoricDirectiva() {
        return ResponseEntity.ok(gestorFutbolService.listHistoricDirectiva());
    }

    @GetMapping("/configuracio")
    public ResponseEntity<ConfiguracioDTO> getConfiguracio() {
        return ResponseEntity.ok(gestorFutbolService.getConfiguracio());
    }

    @GetMapping("/config-general")
    public ResponseEntity<ConfiguracioGeneralDTO> getLogo() {
        return ResponseEntity.ok(gestorFutbolService.getConfiguracioGeneral());
    }

    @PostMapping("/configuracio")
    public ResponseEntity<Long> saveConfiguracio(@RequestBody ConfiguracioDTO configuracioDTO) {
        return  ResponseEntity.ok(gestorFutbolService.saveConfiguracio(configuracioDTO));
    }

    @PostMapping("/factures")
    public ResponseEntity<PaginaDTO<List<CaixaFixaDTO>>> listCaixaFixa(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listFactures(filtre));
    }

    @PostMapping("/factura")
    public ResponseEntity<Long> saveFactura(@RequestBody CaixaFixaDTO caixaFixaDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveCaixaFixa(caixaFixaDTO));
    }

    @DeleteMapping("/factura/{id}")
    public ResponseEntity<String> deleteFactura(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteCaixaFixa(id);
            return ResponseEntity.ok("Eliminada correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tipo-socis")
    public ResponseEntity<PaginaDTO<List<TipoSociDTO>>> listTipoSocis(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listTipoSocis(filtre));
    }

    @GetMapping("/tipo-socis/{idCampanya}")
    public ResponseEntity<List<TipoSociDTO>> listAllTipoSocis(@PathVariable Long idCampanya) {
        Filtre filtre = new Filtre();
        filtre.setCampanyaActiva(idCampanya);
        return ResponseEntity.ok(gestorFutbolService.listAllTipoSocis(filtre));
    }

    @PostMapping("/tipo-soci")
    public ResponseEntity<Long> saveTipoSoci(@RequestBody TipoSociDTO tipoSociDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveTipoSoci(tipoSociDTO));
    }

    @DeleteMapping("/tipo-soci/{id}")
    public ResponseEntity<String> deleteTipoSoci(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteTipoSoci(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }
}
