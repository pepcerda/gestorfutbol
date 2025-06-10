package com.jcerdar.gestorfutbol.service.util;

import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatrocinadorFactory {
    public static List<PatrocinadorDTO> getPatrocinadores() {
        List<PatrocinadorDTO> patrocinadores = new ArrayList<>();
        // Añadir objetos PatrocinadorDTO a la lista
        patrocinadores.add(new PatrocinadorDTO(1L, 101L, "CIF123", "Patrocinador 1", 1000L, new Date(), ""));
        patrocinadores.add(new PatrocinadorDTO(2L, 102L, "CIF456", "Patrocinador 2", 2000L, new Date(), ""));
        return patrocinadores;
    }
}
