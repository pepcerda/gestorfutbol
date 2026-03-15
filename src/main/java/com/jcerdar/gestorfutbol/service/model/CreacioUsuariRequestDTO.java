package com.jcerdar.gestorfutbol.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class CreacioUsuariRequestDTO {

    private ProfileDTO profile;

    private List<IdentityDTO> identities;


    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public List<IdentityDTO> getIdentities() {
        return identities;
    }

    public void setIdentities(List<IdentityDTO> identities) {
        this.identities = identities;
    }

    // Constructor de conveniencia para facilitar el uso
    public CreacioUsuariRequestDTO(String email, String firstName, String lastName) {
        this.profile = new ProfileDTO(firstName, lastName);
        this.identities = Collections.singletonList(new IdentityDTO("email", email));
    }
}

class ProfileDTO {
    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    public ProfileDTO(String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}

class IdentityDTO {
    private String type;
    private IdentityDetailsDTO details;

    public IdentityDTO(String type, String email) {
        this.type = type;
        this.details = new IdentityDetailsDTO(email);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IdentityDetailsDTO getDetails() {
        return details;
    }

    public void setDetails(IdentityDetailsDTO details) {
        this.details = details;
    }
}

class IdentityDetailsDTO {
    private String email;

    public IdentityDetailsDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
