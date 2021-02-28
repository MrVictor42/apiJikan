package com.api.jikan.apiJikan.services.interfaces;

import com.api.jikan.apiJikan.model.entities.Manga;

public interface MangaService {
	
	public void createManga(Manga manga);
	public Manga getManga(String slug);
	public void deleteAnime();
	public Iterable<Manga> getAllManga();
}