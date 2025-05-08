package com.singlutengo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EntityScan(basePackages = "com.singlutengo.entity")
@SpringBootApplication
public class SinGlutenGoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinGlutenGoBackendApplication.class, args);
	}

}
