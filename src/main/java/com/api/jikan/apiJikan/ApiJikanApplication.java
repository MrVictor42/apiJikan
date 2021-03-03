package com.api.jikan.apiJikan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ApiJikanApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiJikanApplication.class, args);
	}
}