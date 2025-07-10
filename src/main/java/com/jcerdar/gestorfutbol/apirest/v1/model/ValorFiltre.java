package com.jcerdar.gestorfutbol.apirest.v1.model;

public class ValorFiltre {

    private String value;

    private String matchMode;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }
}
