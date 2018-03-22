
package rw.motardmis.dao;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil2 {

//    private static final SessionFactory sessionFactory;
//    
//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml) 
//            // config file.
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//    
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    
//    
    
    private static  SessionFactory fact = null;

    private HibernateUtil2() {
    }

    public static Session getSession() {
        if (fact == null) {
            Configuration conf = new Configuration().configure();
            fact = conf.buildSessionFactory();
        }
        return fact.openSession();
    }
    
    
}
