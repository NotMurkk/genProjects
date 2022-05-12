package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class DemoConfig {
    @Bean("student")
    public Student getStudent(Integer id, String name, List<Phone> ph, Address add) {
        return new Student(id, name, ph, add);
    }
    @Bean
    Address address(){
        return new Address("Marietta","GA","USA","07055");
    }

    @Bean("id")
    public Integer id() {return 4252;}
    @Bean("name")
    public String name() {return "Michale";}
    @Bean("ph")
    public List<Phone> ph() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("(862)600-7485"));
        return number;
    }

}
