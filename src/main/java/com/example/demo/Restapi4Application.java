package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Restapi4Application {

	public static void main(String[] args) {
		SpringApplication.run(Restapi4Application.class, args);
	}
	@Bean
	// The reason we are using ModelMap here is to access the ModelMap throughout the project.
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
