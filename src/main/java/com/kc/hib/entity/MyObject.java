package com.kc.hib.entity;

import javax.persistence.*;

/**
 * Created by keshav.chaure on 6/4/2018.
 */
@Entity
@Table(name = "my_table")
public class MyObject {
    String name;
    String id;

    @Column(name = "my_table_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "my_table_id")
    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
