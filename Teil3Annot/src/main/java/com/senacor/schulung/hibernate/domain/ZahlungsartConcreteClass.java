package com.senacor.schulung.hibernate.domain;

public abstract class ZahlungsartConcreteClass implements Zahlungsart {
    private String eigentuemer;

    public String getEigentuemer() {
        return eigentuemer;
    }

    public void setEigentuemer(String eigentuemer) {
        this.eigentuemer = eigentuemer;
    }
}