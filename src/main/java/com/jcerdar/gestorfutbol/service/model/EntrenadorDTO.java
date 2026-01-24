package com.jcerdar.gestorfutbol.service.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EntrenadorDTO extends MembrePlantillaDTO {
    private  String carrec;

    public EntrenadorDTO() {

    }

    public EntrenadorDTO(Long id, Long campanya, Long equip, String nom, String llinatge1, String llinatge2, Date dataNaixement, String carrec, List<ContacteDTO> contactes) {
        super(id, campanya, equip, nom, llinatge1, llinatge2, dataNaixement, contactes);
        this.carrec = carrec;
    }

    public String getCarrec() {
        return carrec;
    }

    public void setCarrec(String carrec) {
        this.carrec = carrec;
    }
}
