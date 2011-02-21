package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="KREDITKARTE_JOINED_SUBCLASS")
public class KreditkarteJoinedSubclass extends ZahlungsartJoinedSubclass {
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