package com.mwronski.jsql.test;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public class Entity {

    @Id
    @GeneratedValue
    private Long id;
    private String string;
    private String secondString;

    public Long getId() {
        return id;
    }

    public String getString() {
        return string;
    }

    public String getSecondString() {
        return secondString;
    }

}
