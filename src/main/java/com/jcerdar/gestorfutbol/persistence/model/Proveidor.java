package com.jcerdar.gestorfutbol.persistence.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GF_PROVEIDOR")
public class Proveidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PR_ID", nullable = false)
    private Long id;

    @Column(name = "PR_NOM", nullable = false)
    private String nom;

    @Column(name = "PR_NOMC", nullable = false)
    private String nomComercial;

    @Column(name = "PR_CIF", nullable = false)
    private String cif;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CPR_PROV")
    private List<ContacteProveidor> contactes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<ContacteProveidor> getContactes() {
        return contactes;
    }

    public void setContactes(List<ContacteProveidor> contactes) {
        this.contactes = contactes;
    }
}
