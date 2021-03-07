package com.api.jikan.apiJikan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.TopAnime;
import com.api.jikan.apiJikan.model.repository.TopAnimeRepository;
import com.api.jikan.apiJikan.services.interfaces.TopAnimeService;

@Service
public class TopAnimeServiceImpl implements TopAnimeService {

	@Autowired
	private TopAnimeRepository topAnimeRepository;
	
	@Override
	public void createTopAnime(TopAnime anime) {
		topAnimeRepository.save(anime);
	}

	@Override
	public void deleteTopAnime() {
		topAnimeRepository.deleteAll();
	}

	@Override
	public TopAnime getTopAnime(String slug) {
		return topAnimeRepository.findBySlug(slug);
	}

	@Override
	public Iterable<TopAnime> getAllTopAnime() {
		return topAnimeRepository.findAll();
	}
}