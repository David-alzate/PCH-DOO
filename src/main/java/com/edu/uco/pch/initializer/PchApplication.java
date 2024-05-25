package com.edu.uco.pch.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.uco.pch.controller" })
public class PchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PchApplication.class, args);
	}

}
