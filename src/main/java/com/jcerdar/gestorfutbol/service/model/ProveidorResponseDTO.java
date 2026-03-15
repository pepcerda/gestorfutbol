package com.jcerdar.gestorfutbol.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProveidorResponseDTO {
    private String id; // Este es el provider_id que buscas
    @JsonProperty("is_created")
    private boolean isCreated;
    // Otros campos que devuelva la API de Kinde si los necesitas

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public boolean isCreated() { return isCreated; }

    public void setCreated(boolean created) { isCreated = created; }
}