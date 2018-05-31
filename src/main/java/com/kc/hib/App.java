package com.kc.hib;

import com.kc.hib.entity.Department;
import com.kc.hib.entity.Employee;
import com.kc.hib.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistryBuilder builder;

    /* static block for hibernate.cfg.xml configuration */
/*
    static {

        Configuration configuration = new Configuration().configure();
        builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

    }
*/
/* static block used  for hibernate.porperties file */
    static {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(com.kc.hib.entity.User.class)
                .addAnnotatedClass(com.kc.hib.entity.Employee.class)
                .addAnnotatedClass(com.kc.hib.entity.Department.class);

        builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
//creating session object
        Session session = sessionFactory.openSession();
        //creating transaction object
        Transaction t = session.beginTransaction();
        //App.insertData(0, "ravi");
        //App.getUserData();
        //    App.insertDepartmentData(2, "Adertisement","DADR");
          App.getDepartmentData();



        /* one to many */
        Department dept = new Department();
        dept.setDeptName("Reaserch and Development");
        dept.setDeptCode("DRD");

        //Save the Model object
        session.save(dept);


        Employee employee1 = new Employee("rajeshwar", "pune", dept);
      //  Employee employee2 = new Employee("ravi", "pune", dept);
     //   Employee employee3 = new Employee("shiva", "pune", dept);
        dept.getEmployees().add(employee1);
        //Set<Employee> employees=new HashSet<>();
        //employees.add(employee1);
    //    employees.add(employee2);
     //   employees.add(employee3);
session.save(employee1);







        // session.save(employee1);
       // session.save(employee2);
       // session.save(employee3);

        //Commit transaction
        t.commit();
        session.close();
        System.out.println("successfully saved");







    }

    private static void getUserData() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        for (User user : users) {
            System.out.println("user Name :" + user.getName() + " user Id " + user.getId());
        }
    }

    private static void getDepartmentData() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Department.class);
        List<Department> departments = criteria.list();
        for (Department dept : departments) {
            System.out.println("dept Name :" + dept.getDeptName() + " dept Id: " + dept.getDeptId() + " dept code: " + dept.getDeptCode());
        }
    }

    private static void insertDepartmentData(int id, String deptName, String deptCode) {
        //creating session object
        Session session = sessionFactory.openSession();
        //creating transaction object
        Transaction t = session.beginTransaction();
        Department d = new Department();
        d.setDeptName(deptName);
        d.setDeptCode(deptCode);
        session.persist(d);//persisting the object
        t.commit();//transaction is commited
        session.close();
        System.out.println("successfully saved");
    }

    private static boolean insertData(int id, String name) {
        //creating session object
        Session session = sessionFactory.openSession();
        //creating transaction object
        Transaction t = session.beginTransaction();
        User e1 = new User();
        //   e1.setId(id);
        e1.setName(name);

        session.persist(e1);//persisting the object

        t.commit();//transaction is commited
        session.close();

        System.out.println("successfully saved");
        return true;
    }
}
