package com.example.demo;
import org.springframework.stereotype.Component;

@Component
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