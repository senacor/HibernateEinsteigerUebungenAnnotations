package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class Test {
    @org.junit.Test
    public void testHibernate() {
        //Session Factory initialisieren
        SessionFactory sf = new Configuration()
                .configure().buildSessionFactory();

        //Session holen
        Session s = sf.getCurrentSession();
        
        Person p = new Person();
        p.setVorname("Sepp");
        p.setNachname("Hammer");

        //Transaktion starten
        Transaction t = s.beginTransaction();

        //Speichern
        s.saveOrUpdate(p);

        //Transaktion Commit
        t.commit();
    }
}
