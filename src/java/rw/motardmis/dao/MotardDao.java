
package rw.motardmis.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.motardmis.domain.Motard;


public class MotardDao extends GenericDao<Motard> {

    public List<Motard> findAllMotard() {
          Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from Motard a ");
        return qry.list();
    }

    public Motard findByPermitNo(String permitNo) {
       Session session = HibernateUtility.getSessionFactory().openSession();
        Motard mt = (Motard) session.get(Motard.class, permitNo);
        return mt;
    }
}