package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Foto;
import com.senacor.schulung.hibernate.domain.Person;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class TestTeil4 extends DBTestCase {

    private SessionFactory sf = null;

    public TestTeil4() {
        super();
        sf = new Configuration()
                .configure().buildSessionFactory();
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
            Session s = sf.getCurrentSession();
            Transaction t = s.beginTransaction();
            Person p = (Person)s.get(Person.class, 100L);
            Person p2 = new Person();
            p2.setId(100L);
            s.saveOrUpdate(p2);
            org.junit.Assert.fail("No Exception");
            t.commit();
        } catch(NonUniqueObjectException e) {

        }
        
    }

    @org.junit.Test
    public void testAvoidNonUniqueObjectException() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        Person p = (Person)s.get(Person.class, 100L);
        Person p2 = new Person();
        p2.setId(100L);
        s.merge(p2);
        t.commit();

    }

    @org.junit.Test
    public void testProvokeLazyInitException() {
        try {
            Session s = sf.getCurrentSession();
            Transaction t = s.beginTransaction();
            Person p = (Person)s.get(Person.class, 100L);
            t.commit();
            p.getFotos().iterator();
            org.junit.Assert.fail("No Exception");
        } catch(LazyInitializationException e) {
            
        }
    }

    @org.junit.Test
    public void testReattach() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        Person p = (Person)s.get(Person.class, 100L);
        t.commit();
        s = sf.openSession();
        t = s.beginTransaction();
        s.saveOrUpdate(p);
        p.getFotos().iterator();
        t.commit();    
    }

    @org.junit.Test
    public void testSpielwiese() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        Foto f = new Foto();
        s.save(f);
        t.commit();
    }
    
}
