package com.intents.preeti.intentsdemo;

import java.io.Serializable;

/**
 * Created by preeti on 1/27/2016.
 */
public class User implements Serializable {
    String name;
    double age;

    public User(String name, double age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
