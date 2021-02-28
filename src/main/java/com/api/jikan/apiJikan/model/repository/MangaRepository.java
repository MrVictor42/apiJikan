package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.Manga;

public interface MangaRepository extends CrudRepository<Manga, Long> {
	
	public Manga findBySlug(String slug);
}