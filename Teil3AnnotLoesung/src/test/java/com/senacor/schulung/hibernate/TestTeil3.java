package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.*;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class TestTeil3 extends TestCase {
    private SessionFactory sf = new Configuration()
                .configure().buildSessionFactory();

    @org.junit.Test
    public void testPersonFoto() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();


        System.out.println(
                "##################\n" +
                "    Teil 1: Person und Fotos - Bidirektionale Relation mit Join Table\n" +
                "##################"
                );
        Person p = new Person();
        p.setNachname("Hammer");
        p.setVorname("Sepp");

        Adresse a = new Adresse();
        a.setPlz("99900");
        a.setStadt("Test Stadt");
        a.setStrasse("Test Allee");
        p.setAdresse(a);

        Foto foto1 = new Foto();
        foto1.setTitel("Test foto1");
        foto1.setPfad("/my/path/img1.jpg");
        p.addFoto(foto1);

        Foto foto2 = new Foto();
        foto2.setTitel("Test foto2");
        foto2.setPfad("/my/path/img2.jpg");
        p.addFoto(foto2);

        s.saveOrUpdate(p);
        assertTrue("Person wurde nicht gespeichert", s.contains(p));
        assertTrue("Wahrscheinlich feht ein cascade=all auf Person#fotos oder die Collection ist nicht gemappt", s.contains(foto1));
        assertTrue("Wahrscheinlich feht ein cascade=all auf Person#fotos oder die Collection ist nicht gemappt",s.contains(foto2));
        t.commit();

    }


    @org.junit.Test
    public void testConcreteClass() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();

        System.out.println(
                "\n##################\n" +
                "    Teil 2.1: Zahlungsart - Inheritance Mapping mit Table per Concrete Class\n" +
                "##################"
                );
        LastschriftConcreteClass lastCC = new LastschriftConcreteClass();
        lastCC.setEigentuemer("Sepp");
        lastCC.setBank("Test Bank");
        lastCC.setBlz("1234");
        lastCC.setKontoNummer("1234");
        s.save(lastCC);

        KreditkarteConcreteClass creditCC = new KreditkarteConcreteClass();
        creditCC.setEigentuemer("Sepp");
        creditCC.setGueltigBis(new Date());
        creditCC.setNummer("12344232");
        s.save(creditCC);
        assertTrue(s.contains(creditCC));
        assertTrue(s.contains(lastCC));
        t.commit();
    }

    @org.junit.Test
    public void testClassHierarchy() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();


        System.out.println(
                "\n##################\n" +
                "    Teil 2.2: Zahlungsart - Inheritance Mapping mit Table per Class Hierarchy\n" +
                "##################"
                );
        LastschriftClassHierarchy lastCH = new LastschriftClassHierarchy();
        lastCH.setEigentuemer("Sepp");
        lastCH.setBank("Test Bank");
        lastCH.setBlz("1234");
        lastCH.setKontoNummer("1234");
        s.save(lastCH);

        KreditkarteClassHierarchy creditCH = new KreditkarteClassHierarchy();
        creditCH.setEigentuemer("Sepp");
        creditCH.setGueltigBis(new Date());
        creditCH.setNummer("12344232");
        s.save(creditCH);
        assertTrue(s.contains(creditCH));
        assertTrue(s.contains(lastCH));
        t.commit();
    }

    @org.junit.Test
    public void testJoinedSubclass() {
        Session s = sf.getCurrentSession();
        Transaction t = s.beginTransaction();

        System.out.println(
                "\n##################\n" +
                "    Teil 2.3: Zahlungsart - Inheritance Mapping mit Table per Subclass\n" +
                "##################"
                );
        LastschriftJoinedSubclass lastJS = new LastschriftJoinedSubclass();
        lastJS.setEigentuemer("Sepp");
        lastJS.setBank("Test Bank");
        lastJS.setBlz("1234");
        lastJS.setKontoNummer("1234");
        s.save(lastJS);

        KreditkarteJoinedSubclass creditJS = new KreditkarteJoinedSubclass();
        creditJS.setEigentuemer("Sepp");
        creditJS.setGueltigBis(new Date());
        creditJS.setNummer("12344232");
        s.save(creditJS);
        assertTrue(s.contains(creditJS));
        assertTrue(s.contains(lastJS));
        t.commit();
    }
}