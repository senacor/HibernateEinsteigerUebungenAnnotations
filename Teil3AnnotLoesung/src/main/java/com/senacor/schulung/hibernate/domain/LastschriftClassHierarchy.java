package com.senacor.schulung.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ELV")
public class LastschriftClassHierarchy extends ZahlungsartClassHierarchy {

    @Column(name="LAST_KTO_NR")
    private String kontoNummer;

    @Column(name="LAST_BLZ")
    private String blz;

    @Column(name="LAST_BANK")
    private String bank;

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