package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@AttributeOverride(name="eigentuemer", column = @Column(name="CC_EIGENTUEMER"))
@Table(name="KREDITKARTE_CONCRETE_CLASS")
public class KreditkarteConcreteClass extends ZahlungsartConcreteClass {
    @Id
    @GeneratedValue
    @Column(name="CC_ID")
    private long id;

    @Column(name="CC_NUMMER")
    private String nummer;

    @Column(name="CC_VALID_TO")
    @Temporal(TemporalType.DATE)
    private Date gueltigBis;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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