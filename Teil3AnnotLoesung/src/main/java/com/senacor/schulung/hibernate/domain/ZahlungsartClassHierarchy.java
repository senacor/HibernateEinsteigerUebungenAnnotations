package com.senacor.schulung.hibernate.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ZA_TYP", discriminatorType = DiscriminatorType.STRING)
@Table(name="ZAHLUNGSARTEN_CLASS_HIERARCHY")
public abstract class ZahlungsartClassHierarchy implements Zahlungsart {

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