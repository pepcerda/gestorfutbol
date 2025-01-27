package com.jcerdar.gestorfutbol.service.model;

import java.util.Date;

public class CampanyaDTO {


    private Long id;

    private Date any;

    private Long importSocis;

    private String titol;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAny() {
        return any;
    }

    public void setAny(Date any) {
        this.any = any;
    }

    public Long getImportSocis() {
        return importSocis;
    }

    public void setImportSocis(Long importSocis) {
        this.importSocis = importSocis;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }
}
