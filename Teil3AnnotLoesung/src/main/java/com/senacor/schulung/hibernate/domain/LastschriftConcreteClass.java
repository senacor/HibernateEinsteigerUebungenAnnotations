package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;

@Entity
@AttributeOverride(name="eigentuemer", column = @Column(name="LAST_EIGENTUEMER"))
@Table(name="LASTSCHRIFT_CONCRETE_CLASS")
public class LastschriftConcreteClass extends ZahlungsartConcreteClass {
    @Id
    @GeneratedValue
    @Column(name="LAST_ID")
    private long id;

    @Column(name="LAST_KTO_NR")
    private String kontoNummer;

    @Column(name="LAST_BLZ")
    private String blz;

    @Column(name="LAST_BANK")
    private String bank;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}