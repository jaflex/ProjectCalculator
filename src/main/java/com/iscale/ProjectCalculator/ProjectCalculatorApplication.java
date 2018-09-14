package com.iscale.ProjectCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.iscale")
public class ProjectCalculatorApplication {

	public static void main(String[] args) {		
		SpringApplication.run(ProjectCalculatorApplication.class, args);
	}
}
