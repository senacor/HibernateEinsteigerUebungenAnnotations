package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Person;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * Person: mploed
 */
public class Test extends TestCase{
    @org.junit.Test
    public void testJPA() {
        // EntityManagerFactory mit Name "test" initialisieren

        // EntityManager holen

        // Transaktion starten
        
        Person p = new Person();
        p.setVorname("Sepp");
        p.setNachname("Hammer");



        // Speichern

        // Transaktion committen
    }
}
