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
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        //TODO Hier Code schreiben, der eine NonUniqueObjectException provoziert
        t.commit();

    }

    @org.junit.Test
    public void testAvoidNonUniqueObjectException() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        //TODO Hier Code schreiben, der die oben provozierte NonUniqueObjectException vermeidet
        t.commit();

    }

    @org.junit.Test
    public void testProvokeLazyInitException() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        //TODO Hier Code schreiben, der eine LazyInitializationException provoziert
        t.commit();
    }

    @org.junit.Test
    public void testReattach() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();
        //TODO Hier Code schreiben, der ein Reattachment eines Objekts im Zustand "Detached" durchführt
        t.commit();    
    }
    
}
