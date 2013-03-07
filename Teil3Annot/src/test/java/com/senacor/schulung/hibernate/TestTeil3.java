package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.*;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class TestTeil3 extends TestCase {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");

    @org.junit.Test
    public void testPersonFoto() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

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

        entityManager.persist(p);
        assertTrue("Person wurde nicht gespeichert", entityManager.contains(p));
        assertTrue("Wahrscheinlich feht ein cascade=all auf Person#fotos oder die Collection ist nicht gemappt", entityManager.contains(foto1));
        assertTrue("Wahrscheinlich feht ein cascade=all auf Person#fotos oder die Collection ist nicht gemappt",entityManager.contains(foto2));
        entityManager.getTransaction().commit();

    }


    @org.junit.Test
    public void testConcreteClass() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

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
        entityManager.persist(lastCC);

        KreditkarteConcreteClass creditCC = new KreditkarteConcreteClass();
        creditCC.setEigentuemer("Sepp");
        creditCC.setGueltigBis(new Date());
        creditCC.setNummer("12344232");
        entityManager.persist(creditCC);
        assertTrue(entityManager.contains(creditCC));
        assertTrue(entityManager.contains(lastCC));
        entityManager.getTransaction().commit();
    }

    @org.junit.Test
    public void testClassHierarchy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


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
        entityManager.persist(lastCH);

        KreditkarteClassHierarchy creditCH = new KreditkarteClassHierarchy();
        creditCH.setEigentuemer("Sepp");
        creditCH.setGueltigBis(new Date());
        creditCH.setNummer("12344232");
        entityManager.persist(creditCH);
        assertTrue(entityManager.contains(creditCH));
        assertTrue(entityManager.contains(lastCH));
        entityManager.getTransaction().commit();
    }

    @org.junit.Test
    public void testJoinedSubclass() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

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
        entityManager.persist(lastJS);

        KreditkarteJoinedSubclass creditJS = new KreditkarteJoinedSubclass();
        creditJS.setEigentuemer("Sepp");
        creditJS.setGueltigBis(new Date());
        creditJS.setNummer("12344232");
        entityManager.persist(creditJS);
        assertTrue(entityManager.contains(creditJS));
        assertTrue(entityManager.contains(lastJS));
        entityManager.getTransaction().commit();
    }
}
