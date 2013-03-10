package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Adresse;
import com.senacor.schulung.hibernate.domain.Foto;
import com.senacor.schulung.hibernate.domain.Person;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class TestTeil4 extends DBTestCase {

    private EntityManagerFactory entityManagerFactory = null;

    public TestTeil4() {
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
    public void testProvokeNonUniqueObjectException() {
        try {
            Session s = (Session) entityManagerFactory.createEntityManager().getDelegate();
            Transaction t = s.beginTransaction();
            Person p = (Person) s.get(Person.class, 100L);
            Person p2 = new Person();
            p2.setId(100L);
            s.saveOrUpdate(p2);
            org.junit.Assert.fail("No Exception");
            t.commit();
        } catch (NonUniqueObjectException e) {

        }

    }

    @org.junit.Test
    public void testAvoidNonUniqueObjectException() {
        Session s = (Session) entityManagerFactory.createEntityManager().getDelegate();
        Transaction t = s.beginTransaction();
        Person p = (Person) s.get(Person.class, 100L);
        Person p2 = new Person();
        p2.setId(100L);
        s.merge(p2);
        t.commit();

    }

    @org.junit.Test
    public void testProvokeLazyInitException() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Person p = entityManager.find(Person.class, 100L);
            entityManager.getTransaction().commit();
            entityManager.close();
            p.getFotos().iterator();
            org.junit.Assert.fail("No Exception");
        } catch (LazyInitializationException e) {

        }
    }

    @org.junit.Test
    public void testReattach() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Person p = entityManager.find(Person.class, 100L);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        p = entityManager.merge(p);
        p.getFotos().iterator();
        entityManager.getTransaction().commit();
    }

    @org.junit.Test
    public void testSpielwiese() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ((Session) entityManager.getDelegate()).setFlushMode(FlushMode.MANUAL);
        entityManager.getTransaction().begin();
        Person p = new Person();
        //^transient
        p.setNachname("Plöd");
        p.setVorname("Michael");
        p.setUsername("PloedM");
        Adresse a = new Adresse();
        a.setPlz("23434");
        a.setStadt("Teststadt");
        a.setStrasse("Teststr. 34");
        p.setAdresse(a);
        entityManager.persist(p);
        //^persistent
        entityManager.getTransaction().commit();
        entityManager.close();
        //^detached
        p.setId(0);

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Session session = (Session) entityManager2.getDelegate();

        p.setUsername("test");
        session.saveOrUpdate(p);
        entityManager2.getTransaction().commit();
    }

    @org.junit.Test
    public void testGetAndLoad() {
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Session session = (Session) entityManager2.getDelegate();
        Person p = (Person)session.get(Person.class, 105L);
        entityManager2.getTransaction().commit();
    }

}
