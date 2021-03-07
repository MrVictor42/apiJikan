package com.api.jikan.apiJikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jikan.apiJikan.model.entities.TopAnime;
import com.api.jikan.apiJikan.services.impl.TopAnimeServiceImpl;

@RestController
@RequestMapping("/api/topAnime")
public class TopAnimeController {

	@Autowired
	private TopAnimeServiceImpl topAnimeServiceImpl;
	
	@GetMapping("/list")
	public Iterable<TopAnime> getAllTopAnime() {																																																																																																																																																																																																																																														
		return topAnimeServiceImpl.getAllTopAnime();
	}
	
	@GetMapping("/{slug}")
	public TopAnime getTopAnime(@PathVariable String slug) {
		return topAnimeServiceImpl.getTopAnime(slug);
	}
}