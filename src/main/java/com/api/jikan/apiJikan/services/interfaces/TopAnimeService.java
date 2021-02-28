package com.api.jikan.apiJikan.services.interfaces;

import com.api.jikan.apiJikan.model.entities.TopAnime;

public interface TopAnimeService {
	
	public void createTopAnime(TopAnime anime);
	public void deleteTopAnime();
	public TopAnime getTopAnime(String slug);
}