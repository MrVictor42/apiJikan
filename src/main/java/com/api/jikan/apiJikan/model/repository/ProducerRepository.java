package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.Producer;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

	@Query(value = "SELECT DISTINCT name,id FROM producers", nativeQuery = true)
	public Iterable<Producer> findAll();
	public Producer findByName(String name);
}
