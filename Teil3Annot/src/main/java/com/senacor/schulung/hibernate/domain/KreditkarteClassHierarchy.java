package com.senacor.schulung.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class KreditkarteClassHierarchy extends ZahlungsartClassHierarchy {
    @Column(name="CC_NUMMER")
    private String nummer;

    @Column(name="CC_VALID_TO")
    @Temporal(TemporalType.DATE)
    private Date gueltigBis;


    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public Date getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }
}