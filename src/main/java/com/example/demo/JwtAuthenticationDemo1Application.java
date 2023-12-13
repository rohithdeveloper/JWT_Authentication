package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.jwtfilter.JwtAuthFilter;

@Configuration
@SpringBootApplication
public class JwtAuthenticationDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationDemo1Application.class, args);
	}
	
	
	 

}
