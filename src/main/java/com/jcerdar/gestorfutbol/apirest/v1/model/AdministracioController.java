package com.jcerdar.gestorfutbol.apirest.v1.model;

import com.jcerdar.gestorfutbol.service.*;
import com.jcerdar.gestorfutbol.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin(origins = "*")
public class AdministracioController extends BaseController {

    @Autowired
    private GestorFutbolService gestorFutbolService;

    @Autowired
    private GestioUsuarisService gestioUsuarisService;

    @PostMapping("/admin/tenant")
    public ResponseEntity<Long> saveTenant(@RequestBody TenantDTO tenantDTO) {
        Long tenantId = gestorFutbolService.saveTenant(tenantDTO);
        return new ResponseEntity<>(tenantId, HttpStatus.CREATED);
    }

    @PostMapping("/admin/tenants")
    public ResponseEntity<PaginaDTO<List<TenantDTO>>> listTenants(@RequestBody Filtre filtre) {
        PaginaDTO<List<TenantDTO>> paginaDTO = gestorFutbolService.listTenants(filtre);
        return new ResponseEntity<>(paginaDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/tenants")
    public ResponseEntity<List<TenantDTO>> listAllTenants() {
        List<TenantDTO> tenants = gestorFutbolService.listAllTenants();
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    @DeleteMapping("/admin/tenant/{tenantId}")
    public ResponseEntity<String> deleteTenant(@PathVariable Long tenantId) {
        try {
            gestorFutbolService.deleteTenant(tenantId);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/usuaris")
    public ResponseEntity<PaginaDTO<List<UsuariDTO>>> listUsuaris(@RequestBody Filtre filtre) {
        PaginaDTO<List<UsuariDTO>> paginaDTO = gestioUsuarisService.listUsuaris(filtre);
        return new ResponseEntity<>(paginaDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/usuari/{providerId}")
    public ResponseEntity<UsuariDTO> getUsuari(@PathVariable String providerId) {
        UsuariDTO usuariDTO = gestioUsuarisService.getUsuari(providerId);
        if (usuariDTO != null) {
            return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/usuaris-tenant")
    public ResponseEntity<PaginaDTO<List<UsuariTenantDTO>>> listUsuarisTenant(@RequestBody Filtre filtre) {
        PaginaDTO<List<UsuariTenantDTO>> paginaDTO = gestioUsuarisService.listUsuarisTenant(filtre);
        return new ResponseEntity<>(paginaDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/tenants-usuari")
        public ResponseEntity<PaginaDTO<List<TenantDTO>>> listTenantsUsuari(@RequestBody Filtre filtre) {
        PaginaDTO<List<TenantDTO>> paginaDTO = gestioUsuarisService.listTenantsUsuari(filtre);
        return new ResponseEntity<>(paginaDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/usuari")
    public ResponseEntity<Long> createUsuari(@RequestBody UsuariTenantDTO usuariTenantDTO) {
        Long usuariId = gestioUsuarisService.saveUsuari(usuariTenantDTO);
        return new ResponseEntity<>(usuariId, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/usuari")
    public ResponseEntity<String> deleteUsuariTenant(@RequestParam("tenant") Long tenantId, @RequestParam("usuari") Long usuariId) {
        try {
            gestioUsuarisService.deleteUsuariTenant(tenantId, usuariId);
            return ResponseEntity.ok("Eliminat correctament");
        } catch (Exception e)  {
            return new ResponseEntity<String>("No s'ha trobat element amb identificador", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/plans-suscripcio")
    public ResponseEntity<List<PlaSuscripcioDTO>> listPlaSuscripcio() {
        List<PlaSuscripcioDTO> plaSuscripcio = gestorFutbolService.listPlaSuscripcio();
        return new ResponseEntity<>(plaSuscripcio, HttpStatus.OK);
    }

    @GetMapping("/admin/rols")
    public ResponseEntity<List<RolDTO>> listRols() {
        List<RolDTO> rols = gestioUsuarisService.listRols();
        return new ResponseEntity<>(rols, HttpStatus.OK);
    }

}
