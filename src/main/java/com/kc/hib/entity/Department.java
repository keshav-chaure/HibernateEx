package com.kc.hib.entity;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by keshav.chaure on 5/31/2018.
 */
@Entity
@Table(name = "Department")
public class Department implements Serializable {

    private int deptId;
    private String deptName;
    private String deptCode;
    // @JoinTable(name = "Dept_Emp", joinColumns = { @JoinColumn(name = "dept_id") }, inverseJoinColumns = { @JoinColumn(name = "emp_id") })
    Set<Employee> employees = new HashSet<>(0);

    public Department() {
        System.out.println("dept loading..");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // use autoincreament
    // @GeneratedValue(strategy = GenerationType.AUTO) // default genrater strategy it will genrate next value as per database dialect.
    // @GeneratedValue(strategy = GenerationType.SEQUENCE) org.hibernate.MappingExcepton : org.hibernate.dialect.MySQLDialect does not support sequence.
    // @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "dept_id")
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Column(name = "dept_name")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    @Column(name = "dept_code")
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @OneToMany(mappedBy = "dept",fetch = FetchType.LAZY)
    //@Fetch(FetchMode.JOIN)
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size = 2)
    @Fetch(FetchMode.SUBSELECT)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
