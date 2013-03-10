package com.senacor.schulung.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {
    @Id
    private long id;

    private String test;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
