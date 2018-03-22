package rw.motardmis.dao;

import org.hibernate.Session;
import rw.motardmis.domain.Association;

public abstract class GenericDao<X> {

    public void Create(X x) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(x);
        session.getTransaction().commit();
        session.close();
    }

    public void Update(X x) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        //session.refresh(x);
        session.update(x);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(X x) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(x);
        session.getTransaction().commit();
        session.close();
    }
}
