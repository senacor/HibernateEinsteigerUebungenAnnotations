package com.senacor.schulung.hibernate;

import com.mysema.query.jpa.impl.JPAQuery;
import com.senacor.schulung.hibernate.domain.Person;
import com.senacor.schulung.hibernate.domain.QPerson;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestQueryDSL extends DBTestCase {

    private EntityManagerFactory entityManagerFactory = null;

    public TestQueryDSL() {
        super();
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:hsql://localhost/testdb");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("dataset.xml"));
    }

    @org.junit.Test
    public void testInnerJoin() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //TODO hier eine QueryDSL Query schreiben, die nur Benutzer mit Fotos ausgibt (Tip: Inner Join)
        QPerson person = QPerson.person;
        JPAQuery query = new JPAQuery(entityManager);
        List<Person> l = query.from(person).innerJoin(person.fotos).distinct().list(person);
        for(Person p : l) {
            System.out.println(p.getUsername());
        }
        entityManager.getTransaction().commit();
    }

    @org.junit.Test
    public void testGroupBy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //TODO hier eine QueryDSL Query schreiben, die zählt in welchen Städten wie viele Benutzer wohnen
        QPerson person = QPerson.person;
        JPAQuery query = new JPAQuery(entityManager);
        query = query.from(person).groupBy(person.adresse.stadt);
        for (Object[] objects : query.listDistinct(person.adresse.stadt, person.adresse.stadt.count())) {
            System.out.println(objects[0] + ": " + objects[1]);
        }
        entityManager.getTransaction().commit();
    }

}
