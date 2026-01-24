package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;
import java.util.List;

public class JugadorDTO extends MembrePlantillaDTO {

    private PosicioDTO posicio;

    public JugadorDTO() {

    }

    public JugadorDTO(Long id, Long campanya, Long categoria, String nom, String llinatge1, String llinatge2, Date dataNaixement, PosicioDTO posicio, List<ContacteDTO> contactes) {
        super(id, campanya, categoria, nom, llinatge1, llinatge2, dataNaixement, contactes);
        this.posicio = posicio;
    }

    public PosicioDTO getPosicio() {
        return posicio;
    }

    public void setPosicio(PosicioDTO posicio) {
        this.posicio = posicio;
    }
}

