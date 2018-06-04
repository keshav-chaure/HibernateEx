package com.kc.hib;

import com.kc.hib.entity.Department;
import com.kc.hib.entity.Employee;
import com.kc.hib.entity.MyObject;
import com.kc.hib.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
                .addAnnotatedClass(com.kc.hib.entity.MyObject.class)
                .addAnnotatedClass(com.kc.hib.entity.Department.class);

        builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
        //addDempartmentEmployee();
        Session session = sessionFactory.openSession();
//        List<Department> depts=session.createQuery("From Department").list();
//        fetchSelectAndJoinTest();
//        fetchBatchSizeTest();
//        hqlFetchNPlusOneProbblem();
//        criteriaNPlusOneProblem();
//         cascadeTypeTest();
//        addDempartmentEmployee();
          objectStateTest();

        //  Department g=depts.get(1);
        // g.getDept();
        //    for(Department e:depts){
        //  System.out.println("empId: "+e.getEmpId()+", empDemp: "+e.getDept().getDeptId());
        //  }

    }

    private static void objectStateTest() {
        // tranciant state
        MyObject obj=new MyObject();
        obj.setName("Keshav's Table");

        Session session = sessionFactory.openSession();
        Transaction txn=session.getTransaction();
        txn.begin();
      //  session.save(obj);

        session.save("some",obj);
        // Object in persistance state

        txn.commit();


        // Object in detached state
        System.out.println("saved myobject !" );

        session.close();
        System.out.println("saved myobject !"+obj.getName());
    }

    private static void criteriaNPlusOneProblem() {
    /* criteria to resolve n+1 probblem  */
 /*       Criteria criteria = session.createCriteria(Employee.class);
        criteria.setFetchMode("dept", FetchMode.EAGER);
        List<Employee> list = criteria.list();
        for (Employee e:list) {
              System.out.println("empId: "+e.getEmpId()+", empDemp: "+e.getDept().getDeptId());
        }
*/
    }

    private static void hqlFetchNPlusOneProbblem() {
    /* resolutin of N + 1 problem */
        //  List<Department> list = session.createQuery("from Department").list();
        // insted of above use below line of code
        //1. using HQL fetch join
/*

        String hql = "from Department dept join fetch dept.employees emp";
        Query query = session.createQuery(hql);
        List<Department> results = query.list();
        for(Department department : results){

            Set employeeSet = department.getEmployees();

            for (Iterator iter = employeeSet.iterator(); iter.hasNext(); ) {
                Employee emp = (Employee) iter.next();
                System.out.println(emp.getEmpId());
                System.out.println(emp.getEmpName());
            }
        }

*/
    }

    private static void fetchBatchSizeTest() {
    /* for fetch statergy @batch-size(size=10)*/
/*        List<Department> list = session.createQuery("from Department").list();

        for(Department department : list){

            Set employeeSet = department.getEmployees();

            for (Iterator iter = employeeSet.iterator(); iter.hasNext(); ) {
                Employee emp = (Employee) iter.next();
                System.out.println(emp.getEmpId());
                System.out.println(emp.getEmpName());
            }
        }
*/
    }

    private static void fetchSelectAndJoinTest() {
    /*  for fetch statergy select and join testing
            Department d=(Department)session.get(Department.class,2);
            Set<Employee> sets=d.getEmployees();
            for (Employee e:sets) {
                System.out.println(e.getEmpId());
            }
    */
    }
    private  static void cascadeTypeTest(){
        // in addDempartmentEmployee() method we have to store Department and Employee indivusually.
        // With save-update cascade we dont need to save Employee as soon as we save Department , it will save employee also
        //create session
        Session session=sessionFactory.openSession();
        //create transaction
        Transaction txn=session.beginTransaction();

        Department department=new Department();
        Employee employee=new Employee();

        department.setDeptName("Legal Department");
        department.setDeptCode("DLEG");
        employee.setDept(department);
        employee.setEmpName("shyam");
        employee.setEmpCity("basmat");


        department.getEmployees().add(employee);

        session.save(department);


        txn.commit();
        session.close();
        System.out.println("successfully saved");





    }
    private static void addDempartmentEmployee() {
        //creating session object
        Session session = sessionFactory.openSession();
        //creating transaction object
        Transaction t = session.beginTransaction();
        //App.getDepartmentData();
        /* one to many */
        Department dept = new Department();
        dept.setDeptName("Test Department");
        dept.setDeptCode("DTD");
        //Save the Model object
        session.save(dept);
        Employee employee1 = new Employee("Swamikumar", "bnaglore", dept);
        dept.getEmployees().add(employee1);
        session.save(employee1);
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
