package com.jcerdar.gestorfutbol.apirest.v1.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jcerdar.gestorfutbol.service.model.MembrePlantillaDTO;
import com.jcerdar.gestorfutbol.persistence.model.type.Posicio;
import com.jcerdar.gestorfutbol.service.GestorFutbolService;
import com.jcerdar.gestorfutbol.service.MediaService;
import com.jcerdar.gestorfutbol.service.model.BaixaDTO;
import com.jcerdar.gestorfutbol.service.model.CaixaFixaDTO;
import com.jcerdar.gestorfutbol.service.model.CampanyaDTO;
import com.jcerdar.gestorfutbol.service.model.ConfiguracioDTO;
import com.jcerdar.gestorfutbol.service.model.ConfiguracioGeneralDTO;
import com.jcerdar.gestorfutbol.service.model.DelegatDTO;
import com.jcerdar.gestorfutbol.service.model.DirectiuDTO;
import com.jcerdar.gestorfutbol.service.model.DirectivaDTO;
import com.jcerdar.gestorfutbol.service.model.EntrenadorDTO;
import com.jcerdar.gestorfutbol.service.model.JugadorDTO;
import com.jcerdar.gestorfutbol.service.model.MensualitatDTO;
import com.jcerdar.gestorfutbol.service.model.NominaDTO;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.RolDirectiuDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;
import com.jcerdar.gestorfutbol.service.model.TipoSociDTO;


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
    public ResponseEntity<Long> saveFactura(@RequestPart("caixaFixa") CaixaFixaDTO caixaFixaDTO, @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(gestorFutbolService.saveCaixaFixa(caixaFixaDTO, file));
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

    @PostMapping("/entrenadors")
    public ResponseEntity<List<EntrenadorDTO>> listEntrenadors(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllEntrenadors(filtre));
    }

    @PostMapping("/entrenador")
    public ResponseEntity<Long> saveEntrenador(@RequestBody EntrenadorDTO entrenadorDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveEntrenador(entrenadorDTO));
    }

    @DeleteMapping("/entrenador/{id}")
    public ResponseEntity<String> deleteEntrenador(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteEntrenador(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e) {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delegats")
    public ResponseEntity<List<DelegatDTO>> listDelegats(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllDelegats(filtre));
    }

    @PostMapping("/delegat")
    public ResponseEntity<Long> saveDelegat(@RequestBody DelegatDTO delegatDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveDelegat(delegatDTO));
    }

    @DeleteMapping("/delegat/{id}")
    public ResponseEntity<String> deleteDelegat(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteDelegat(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e) {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/membres-plantilla")
    public ResponseEntity<List<MembrePlantillaDTO>> listMembresPlantilla(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllMembresPlantilla(filtre));
    }
    
    @PostMapping("/jugadors")
    public ResponseEntity<List<JugadorDTO>> listJugadors(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllJugadors(filtre));
    }

    @PostMapping("/jugador")
    public ResponseEntity<Long> saveJugador(@RequestBody JugadorDTO jugadorDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveJugador(jugadorDTO));
    }

    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<String> deleteJugador(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteJugador(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e) {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posicions")
    public ResponseEntity<List<Map<String, String>>> getPosicions() {
        return ResponseEntity.ok(List.of(Posicio.values()).stream()
            .map(posicio -> Map.of(
                "valor", posicio.getValor(),
                "descripcion", posicio.getDescripcion(),
                "name", posicio.name()
            ))
            .collect(Collectors.toList()));
    }

    @PostMapping("/mensualitats")
    public ResponseEntity<List<MensualitatDTO>> listAllMensualitats(@RequestBody Filtre filtre) {
        return ResponseEntity.ok(gestorFutbolService.listAllMensualitats(filtre));
    }

    @PostMapping("/mensualitat")
    public ResponseEntity<Long> saveMensualitat(@RequestBody MensualitatDTO mensualitatDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveMensualitat(mensualitatDTO));
    }

    @DeleteMapping("/mensualitat/{id}")
    public ResponseEntity<String> deleteMensualitat(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteMensualitat(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nomina")
    public ResponseEntity<Long> saveNomina(@RequestBody NominaDTO nominaDTO) {
        return ResponseEntity.ok(gestorFutbolService.saveNomina(nominaDTO));
    }

    @DeleteMapping("/nomina/{id}")
    public ResponseEntity<String> deleteNomina(@PathVariable Long id) {
        try {
            gestorFutbolService.deleteNomina(id);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

}
