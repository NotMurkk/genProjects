package com.example.demo;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Phone> ph;
    private Address address;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return ph;
    }

    public void setPhones(List<Phone> ph) {
        this.ph = ph;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
