package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    @org.junit.Test
    public void testJPA() {

        //EntityManagerFactory mit Name "test" initialisieren
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "test" );

        //EntityManager holen
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person p = new Person();
        p.setVorname("Sepp");
        p.setNachname("Hammer");

        //Transaktion starten
        entityManager.getTransaction().begin();

        //Speichern
        entityManager.persist(p);

        //Transaktion Commit
        entityManager.getTransaction().commit();
    }
}
