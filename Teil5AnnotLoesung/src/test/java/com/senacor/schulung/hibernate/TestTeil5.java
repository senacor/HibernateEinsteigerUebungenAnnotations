package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Adresse;
import com.senacor.schulung.hibernate.domain.Foto;
import com.senacor.schulung.hibernate.domain.Person;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetUtils;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TestTeil5 extends DBTestCase {

    private EntityManagerFactory entityManagerFactory = null;

    public TestTeil5() {
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
        Query q = entityManager.createQuery("select distinct p from Person as p inner join p.fotos as f");

        for (Object o : q.getResultList()) {
            Person p = (Person)o;
            System.out.println(p);
        }


        Criteria c = ((Session)entityManager.getDelegate()).createCriteria(Person.class);
        c.createCriteria("fotos", CriteriaSpecification.INNER_JOIN);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Object o : c.list()) {
            Person p = (Person)o;
            System.out.println(p);
        }
        entityManager.getTransaction().commit();
    }

    @org.junit.Test
    public void testGroupBy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("select p.adresse.stadt, count(p) from Person p group by p.adresse.stadt");
        for(Object o : q.getResultList()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + " - " + arr[1]);
        }

        Criteria c = ((Session)entityManager.getDelegate()).createCriteria(Person.class)
                .setProjection(Projections.projectionList()
                .add(Projections.groupProperty("adresse.stadt"))
                .add(Projections.count("id")));
        for(Object o : c.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + " - " + arr[1]);
        }


        entityManager.getTransaction().commit();
    }

}
