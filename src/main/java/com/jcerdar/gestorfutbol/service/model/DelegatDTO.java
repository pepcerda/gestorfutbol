package com.jcerdar.gestorfutbol.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class DelegatDTO extends MembrePlantillaDTO {
    private  Boolean esDelegatCamp;

    public DelegatDTO() {

    }

    public DelegatDTO(Long id, Long campanya, Long categoria, String nom, String llinatge1, String llinatge2, Date dataNaixement, Boolean esDelegatCamp, List<ContacteDTO> contactes) {
        super(id, campanya, categoria, nom, llinatge1, llinatge2, dataNaixement, contactes);
        this.esDelegatCamp = esDelegatCamp;
    }

    public Boolean getEsDelegatCamp() {
        return esDelegatCamp;
    }

    public void setEsDelegatCamp(Boolean esDelegatCamp) {
        this.esDelegatCamp = esDelegatCamp;
    }

}
