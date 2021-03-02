package com.api.jikan.apiJikan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.jikan.apiJikan.services.impl.ApiJikanServiceImpl;

@EnableAutoConfiguration
@SpringBootApplication
public class ApiJikanApplication implements ApplicationRunner {
	
	@Autowired
	private ApiJikanServiceImpl apiJikanServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiJikanApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		apiJikanServiceImpl.synchronizeDatabase();
	}
}