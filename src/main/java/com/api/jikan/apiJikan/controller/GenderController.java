package com.api.jikan.apiJikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jikan.apiJikan.model.entities.Gender;
import com.api.jikan.apiJikan.services.impl.GenderServiceImpl;

@RestController
@RequestMapping("/api/gender")
public class GenderController {
	
	@Autowired
	private GenderServiceImpl genderServiceImpl;
	
	@GetMapping("/list")
	public Iterable<Gender> getAllGender() {
		return genderServiceImpl.getAllGenders();
	}
}