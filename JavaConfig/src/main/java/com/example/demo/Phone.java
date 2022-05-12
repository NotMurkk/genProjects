package com.example.demo;

public class Phone {
    private String mob;

    public Phone(String mob) {
        this.mob = mob;
    }
    @Override
    public String toString() {
        return "mobile: " + mob;
    }
}