package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext appCTX = new FileSystemXmlApplicationContext("src\\main\\resources\\demo.xml");
		Student student = (Student) appCTX.getBean("studentTest");
		System.out.println(student);
	}

}
