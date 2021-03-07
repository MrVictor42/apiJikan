package com.api.jikan.apiJikan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Anime;
import com.api.jikan.apiJikan.model.entities.Gender;
import com.api.jikan.apiJikan.model.repository.GenderRepository;
import com.api.jikan.apiJikan.services.interfaces.GenderService;

@Service
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderRepository genderRepository;
	
	@Override
	public void deleteGenders() {
		genderRepository.deleteAll();	
	}

	@Override
	public Iterable<Gender> getAllGenders() {
		return genderRepository.findAll();
	}

	@Override
	public Gender existsGenderByName(String name) {
		
		if(genderRepository.findByName(name) != null) { //exists gender
			return genderRepository.findByName(name);
		} else {			
			return null;
		}
	}
	
	public void removeAnime(Anime anime) {
		
	}
}