package com.senacor.schulung.hibernate.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Adresse implements Serializable {
    private String strasse;
    private String plz;
    private String stadt;

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adresse adresse = (Adresse) o;

        if (plz != null ? !plz.equals(adresse.plz) : adresse.plz != null) return false;
        if (stadt != null ? !stadt.equals(adresse.stadt) : adresse.stadt != null) return false;
        if (strasse != null ? !strasse.equals(adresse.strasse) : adresse.strasse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = strasse != null ? strasse.hashCode() : 0;
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (stadt != null ? stadt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", stadt='" + stadt + '\'' +
                '}';
    }
}
