package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.TopAnime;

public interface TopAnimeRepository extends CrudRepository<TopAnime, Integer> {
	
	public TopAnime findBySlug(String slug);
}