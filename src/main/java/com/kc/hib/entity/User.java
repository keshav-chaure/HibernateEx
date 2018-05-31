package com.kc.hib.entity;

import javax.persistence.*;

/**
 * Created by keshav.chaure on 5/30/2018.
 */

@Entity
@Table(name = "Test_Table")
public class User implements java.io.Serializable {
    private String name;
    private int id;

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "EmpID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
