package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Adresse;
import com.senacor.schulung.hibernate.domain.Foto;
import com.senacor.schulung.hibernate.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestTeil2 {
    @org.junit.Test
    public void testJPA() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

        entityManager.getTransaction().begin();
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }
}
