
package rw.motardmis.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import rw.motardmis.domain.User;

public class UserDao extends GenericDao<User> {

    public List<User> findAllUsers() {
         Session session = HibernateUtility.getSessionFactory().openSession();
        Query qry = session.createQuery("select a from User a order by post ");
        return qry.list();
    }

    public User findByUsername(String username) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        User us = (User) session.get(User.class, username);
        return us;
    }
    
    public User getOne(String username){
         Session ss = HibernateUtility.getSessionFactory().openSession();
        Query sql = ss.createQuery("from User s where s.username = ?");
        sql.setString(0, username);
        User s=(User) sql.uniqueResult();
        ss.close();
        return s;
    }
     public User getOneUser(String password){
         Session ss = HibernateUtility.getSessionFactory().openSession();
        Query sql = ss.createQuery("select password from User s where s.username = ? ");
        sql.setString(0, password);
        User s=(User) sql.uniqueResult();
        ss.close();
        return s;
    }
    
     public List<User> updAll(){
        List<User> list = new ArrayList<>();
        
        for(User am : new UserDao().findAllUsers()){
            if(am.getStatus().equalsIgnoreCase("ACTIVE")){
                am.setAction("BLOCKED");
            }else if(am.getStatus().equalsIgnoreCase("BLOCKED")){
                am.setAction("ACTIVE");
            }
            list.add(am);
        }
        return list;
    }
     
       public User loginUser(String username, String password) {
         Session ss = HibernateUtility.getSessionFactory().openSession();
        Query q = ss.createQuery("select a from User a where a.username=? and a.password=?");
        q.setString(0, username);
        q.setString(1, password);
        return (User) q.uniqueResult();
        
    }  

}
