package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.Gender;

public interface GenderRepository extends CrudRepository<Gender, Long> {
	
	@Query(value = "SELECT DISTINCT name,id FROM genders", nativeQuery = true)
	public Iterable<Gender> findAll();
	public Gender findByName(String name);
}