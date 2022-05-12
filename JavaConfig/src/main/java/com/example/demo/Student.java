package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Phone> ph;
    private Address address;

    @Autowired
    public Student(int id, String name, List<Phone> ph, Address address) {
        this.id = id;
        this.name = name;
        this.ph = ph;
        this.address= address;
    }

    @Override
    public String toString() {
        return "Student name: " + name +
                ", id: " + id + ' ' +
                "Phone Number: " + ph + ' ' +
                "Address: " + address;
    }

}