package com.senacor.schulung.hibernate;

import com.senacor.schulung.hibernate.domain.Adresse;
import com.senacor.schulung.hibernate.domain.Foto;
import com.senacor.schulung.hibernate.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class TestTeil2 {
    @org.junit.Test
    public void testHibernate() {
        SessionFactory sf = new Configuration()
                .configure().buildSessionFactory();
        Session s = sf.getCurrentSession();

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

        Transaction t = s.beginTransaction();
        s.saveOrUpdate(p);
        t.commit();
    }
}
