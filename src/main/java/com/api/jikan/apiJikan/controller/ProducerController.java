package com.api.jikan.apiJikan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jikan.apiJikan.model.entities.Producer;
import com.api.jikan.apiJikan.services.impl.ProducerServiceImpl;

@RestController
@RequestMapping("api/producer")
public class ProducerController {
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	@GetMapping("/list")
	public Iterable<Producer> getAllProducer() {
		return producerServiceImpl.getAllProducers();
	}
}