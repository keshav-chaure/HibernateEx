package com.kc.hib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
