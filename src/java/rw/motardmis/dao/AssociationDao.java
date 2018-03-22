
package rw.motardmis.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.motardmis.domain.Association;

public class AssociationDao extends GenericDao<Association> {

    public List<Association> findAllAssociation() {
          Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from Association a ");
        return qry.list();
    }

    public Association findByName(String assName) {
         Session session = HibernateUtility.getSessionFactory().openSession();
        Association ass = (Association) session.get(Association.class, assName);
        return ass;
    }
}
