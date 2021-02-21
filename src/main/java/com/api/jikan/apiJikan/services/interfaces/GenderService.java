package com.api.jikan.apiJikan.services.interfaces;

import com.api.jikan.apiJikan.model.entities.Gender;

public interface GenderService {

	public void deleteGenders();
	public Iterable	<Gender> getAllGenders();
	public Gender existsGenderByName(String name);
}