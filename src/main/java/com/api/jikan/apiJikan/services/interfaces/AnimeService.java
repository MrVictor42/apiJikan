package com.api.jikan.apiJikan.services.interfaces;

import com.api.jikan.apiJikan.model.entities.Anime;

public interface AnimeService {
	
	public void createAnime(Anime anime);
	public void deleteAnime();
	public long countAnimeInDatabase();
	public Iterable<Anime> getAllAnime();
	public Anime getAnime(String slug);
	public Iterable<Anime> filterAnime(int genderId, int producerId);
	public Iterable<Anime> filterAnimeByGender(int genderId);
	public Iterable<Anime> filterAnimeByProducer(int producerId);
}