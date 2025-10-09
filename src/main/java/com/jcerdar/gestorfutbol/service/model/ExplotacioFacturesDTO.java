package com.jcerdar.gestorfutbol.service.model;

import java.math.BigDecimal;

public class ExplotacioFacturesDTO {

    private BigDecimal totalPagat;

    private BigDecimal pendentPagar;

    private BigDecimal totalFactures;

    public BigDecimal getTotalPagat() {
        return totalPagat;
    }

    public void setTotalPagat(BigDecimal totalPagat) {
        this.totalPagat = totalPagat;
    }

    public BigDecimal getPendentPagar() {
        return pendentPagar;
    }

    public void setPendentPagar(BigDecimal pendentPagar) {
        this.pendentPagar = pendentPagar;
    }

    public BigDecimal getTotalFactures() {
        return totalFactures;
    }

    public void setTotalFactures(BigDecimal totalFactures) {
        this.totalFactures = totalFactures;
    }
}
