package com.senacor.schulung.hibernate.domain;

// TODO Vererbungshierarchie mit Table per Concrete Class mappen
public abstract class ZahlungsartConcreteClass implements Zahlungsart {
    private String eigentuemer;

    public String getEigentuemer() {
        return eigentuemer;
    }

    public void setEigentuemer(String eigentuemer) {
        this.eigentuemer = eigentuemer;
    }
}