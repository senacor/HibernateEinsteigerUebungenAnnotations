package com.senacor.schulung.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/*
TODO: Diese Klasse mit allen Attributen und Referenzen als Entität mappen
TODO: Bidirektionale Relation beachten (Methode addFoto(Foto f)
 */
public class Person {
    private long id;

    private String vorname;

    private String nachname;

    private String username;

    private Adresse adresse;

    private Set<Foto> fotos = new HashSet<Foto>();

    public void addFoto(Foto f) {
        //TODO implemtieren
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    private void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (username != null ? !username.equals(person.username) : person.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
