package com.api.jikan.apiJikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jikan.apiJikan.model.entities.Manga;
import com.api.jikan.apiJikan.services.impl.MangaServiceImpl;

@RestController
@RequestMapping("/api/manga")
public class MangaController {

	@Autowired
	private MangaServiceImpl mangaServiceImpl;
	
	@GetMapping("/list")
	public Iterable<Manga> getAllManga() {
		return mangaServiceImpl.getAllManga();
	}
	
	@GetMapping("/{slug}")
	public Manga getManga(@PathVariable String slug) {
		return mangaServiceImpl.getManga(slug);
	}
}