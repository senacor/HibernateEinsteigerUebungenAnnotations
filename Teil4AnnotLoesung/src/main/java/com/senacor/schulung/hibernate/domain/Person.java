package com.senacor.schulung.hibernate.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PERSONEN")
@SequenceGenerator(name = "SEQ_GEN")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="PERS_ID")
    private long id;

    @Column(name="PERS_VORNAME")
    private String vorname;

    @Column(name="PERS_NACHNAME")
    private String nachname;

    @Column(name="PERS_USERNAME")
    private String username;

    @Embedded
    @AttributeOverrides ({
        @AttributeOverride(name="strasse",
          column=@Column(name="PERS_ADR_STRASSE")),
        @AttributeOverride(name="plz",
          column=@Column(name="PERS_ADR_PLZ")),
        @AttributeOverride(name="stadt",
          column=@Column(name="PERS_ADR_STADT"))
      })
    private Adresse adresse;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Foto> fotos = new HashSet<Foto>();

    private long version;

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    public void addFoto(Foto f) {
        if(f == null) {
            throw new IllegalArgumentException("Foto is null");
        }
        if(f.getOwner() != null) {
            f.getOwner().getFotos().remove(f);
        }
        getFotos().add(f);
        f.setOwner(this);
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", username='" + username + '\'' +
                ", adresse=" + adresse +
                '}';
    }
}