package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="FOTOS")
public class Foto implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="FOTO_ID")
    private long id;

    @Column(name="FOTO_TITEL")
    private String titel;

    @Column(name="FOTO_PFAD")
    private String pfad;

    @ManyToOne
    @JoinTable(name="PERS_FOTOS")
    @JoinColumn(name="FOTO_PERS_ID", nullable = false)
    private Person owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foto foto = (Foto) o;

        if (pfad != null ? !pfad.equals(foto.pfad) : foto.pfad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pfad != null ? pfad.hashCode() : 0;
    }
}