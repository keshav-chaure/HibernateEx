package com.kc.hib;

import com.kc.hib.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static SessionFactory factory;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //session.get(User);
        Criteria criteria = session.createCriteria(User.class);
         List<User> users= criteria.list();
         for (User user: users){
         System.out.println("users Name :"+user.getName() +" Users Id "+user.getId());
         }


    }
}
