
package rw.motardmis.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.motardmis.domain.Umusanzu;

public class UmusanzuDao extends GenericDao<Umusanzu> {

    public List<Umusanzu> findAllUmusanzu() {
          Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from Umusanzu a order by deposit_Date");
        return qry.list();
    }
    
    public Umusanzu findByPlateNo(String umusanzuNo) {
        Session session = HibernateUtil2.getSession();
        Umusanzu mt = (Umusanzu) session.get(Umusanzu.class, umusanzuNo);
        return mt;
    } 
     
    
}