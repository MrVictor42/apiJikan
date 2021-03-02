package com.api.jikan.apiJikan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Anime;
import com.api.jikan.apiJikan.model.repository.AnimeRepository;
import com.api.jikan.apiJikan.services.interfaces.AnimeService;

@Service
public class AnimeServiceImpl implements AnimeService {
	
	@Autowired
	private AnimeRepository animeRepository; 

	@Override
	public void createAnime(Anime anime) {
		animeRepository.save(anime);
	}

	@Override
	public Iterable<Anime> getAllAnime() {
		return animeRepository.findAll();
	}

	@Override
	public void deleteDatabase() {
		animeRepository.deleteDatabase();
	}

	@Override
	public long countAnimeInDatabase() {
		return animeRepository.count();
	}

	@Override
	public Anime getAnime(String slug) {
		return animeRepository.findBySlug(slug);
	}

	@Override
	public Iterable<Anime> filterAnime(int genderId, int producerId) {
		return animeRepository.findByGendersAndByProducerId(genderId, producerId);
	}

	@Override
	public Iterable<Anime> filterAnimeByGender(int genderId) {
		return animeRepository.findByGendersById(genderId);
	}

	@Override
	public Iterable<Anime> filterAnimeByProducer(int producerId) {
		return animeRepository.findByProducersById(producerId);
	}

	@Override
	public void createDatabase() {
		animeRepository.createDatabase();		
	}
}