package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.Anime;

public interface AnimeRepository extends CrudRepository<Anime, Long> {
	
	public Iterable<Anime> findByOrderByTitleAsc();
	public Anime findBySlug(String slug);
}