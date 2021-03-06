package com.api.jikan.apiJikan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Anime;
import com.api.jikan.apiJikan.model.entities.Gender;
import com.api.jikan.apiJikan.model.repository.AnimeRepository;
import com.api.jikan.apiJikan.model.repository.GenderRepository;
import com.api.jikan.apiJikan.services.interfaces.AnimeService;

@Service
public class AnimeServiceImpl implements AnimeService {
	
	@Autowired
	private AnimeRepository animeRepository;
	@Autowired
	private GenderRepository genderRepository;

	@Override
	public void createAnime(Anime anime) {
		animeRepository.save(anime);
	}

	@Override
	public Iterable<Anime> getAllAnime() {
		return animeRepository.findAll();
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
	public Iterable<Anime> filterAnimeByGender(int genderId) {
		return animeRepository.findByGendersById(genderId);
	}

	@Override
	public void removeGenders() {
		
		List<Anime> animes = animeRepository.findAll();
		List<Gender> genders = genderRepository.findAll();
		
		for(Anime anime: animes) {
			for(Gender gender: genders) {
				anime.removeGender(gender);
			}
		}
		genderRepository.deleteAll();
	}

	@Override
	public void removeAnime() {
		animeRepository.deleteAll();		
	}
}