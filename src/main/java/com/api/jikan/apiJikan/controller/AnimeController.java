package com.api.jikan.apiJikan.controller;

import java.text.ParseException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jikan.apiJikan.model.entities.Anime;
import com.api.jikan.apiJikan.services.impl.AnimeServiceImpl;
import com.api.jikan.apiJikan.services.impl.ApiJikanServiceImpl;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {
	
	@Autowired
	private ApiJikanServiceImpl apiJikanServiceImpl;
	@Autowired
	private AnimeServiceImpl animeServiceImpl;
	
	@GetMapping("/list/update")
	public Iterable<Anime> getAllAnimeUpdate() throws JSONException, ParseException {
		
		apiJikanServiceImpl.synchronize();
		return animeServiceImpl.getAllAnime();
	}
	
	@GetMapping("/list/")
	public Iterable<Anime> getAllAnime() {																																																																																																																																																																																																																																															
		return animeServiceImpl.getAllAnime();
	}
	
	@GetMapping("/count")
	public long getCountDatabase() {
		return animeServiceImpl.countAnimeInDatabase();
	}
	
	@GetMapping("/{slug}")
	public Anime getAnime(@PathVariable String slug) {
		return animeServiceImpl.getAnime(slug);
	}
	
	@GetMapping("filterByGender/{gender_id}")
	public Iterable<Anime> getAnimeByGender(@PathVariable int gender_id) {
		return animeServiceImpl.filterAnimeByGender(gender_id);
	}
}