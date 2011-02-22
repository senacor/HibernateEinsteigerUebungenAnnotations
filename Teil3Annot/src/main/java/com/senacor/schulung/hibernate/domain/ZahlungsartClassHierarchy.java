package com.senacor.schulung.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZAHLUNGSARTEN_CLASS_HIERARCHY")
// TODO Vererbungshierarchie mit Table per Class Hierarchy mappen
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