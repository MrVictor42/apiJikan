package com.api.jikan.apiJikan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Manga;
import com.api.jikan.apiJikan.model.repository.MangaRepository;
import com.api.jikan.apiJikan.services.interfaces.MangaService;

@Service
public class MangaServiceImpl implements MangaService {

	@Autowired
	private MangaRepository mangaRepository;
	
	@Override
	public void createManga(Manga manga) {
		mangaRepository.save(manga);
	}

	@Override
	public Manga getManga(String slug) {
		return mangaRepository.findBySlug(slug);
	}

	@Override
	public void deleteAnime() {
		mangaRepository.deleteAll();
	}

	@Override
	public Iterable<Manga> getAllManga() {
		return mangaRepository.findAll();
	}
}