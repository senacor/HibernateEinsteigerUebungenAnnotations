package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="ZAHLUNGSARTEN")
public abstract class ZahlungsartJoinedSubclass implements Zahlungsart {

    @Id
    @GeneratedValue
    private long id;

    private String eigentuemer;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEigentuemer() {
        return eigentuemer;
    }

    public void setEigentuemer(String eigentuemer) {
        this.eigentuemer = eigentuemer;
    }
}