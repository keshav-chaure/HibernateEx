package com.kc.hib.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by keshav.chaure on 5/31/2018.
 */
@Entity
@Table(name="Employee")
public class Employee implements Serializable{


    private int empId;
    private String empName;
    private String empCity;
    private Department dept;

    public Employee() {
        System.out.println("employee loading..");
    }

    public Employee(String empName, String empCity, Department dept) {
        this.empName = empName;
        this.empCity = empCity;
        this.dept = dept;
    }

    @Id
    @GeneratedValue
    @Column(name="emp_id")
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Column(name="emp_name")
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Column(name="emp_city")
    public String getEmpCity() {
        return empCity;
    }

    public void setEmpCity(String empCity) {
        this.empCity = empCity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_dept")
    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
