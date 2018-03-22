
package rw.motardmis.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.motardmis.domain.Association;
import rw.motardmis.domain.Moto;


public class MotoDao extends GenericDao<Moto> {

    public List<Moto> findAllMotos() {
         Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from Moto a order by insurance_company, assName, owner ");
        return qry.list();
    }

    public Moto findByPlateNo(String plateNo) {
       Session session = HibernateUtility.getSessionFactory().openSession();
        Moto mt = (Moto) session.get(Moto.class, plateNo);
        return mt;
    }
    public Moto findByMotard(String id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Moto mto = (Moto) session.get(Moto.class, id);
        return mto;
    }
    public List<Moto> getMotoByMotard(Moto id) {
         Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from Moto a where a.motard.permit_no=?");
        qry.setString(0, id.getPlate_no());
        List<Moto> list = qry.list();
        session.close();
        return list;
    }
    
    
}