package com.api.jikan.apiJikan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Producer;
import com.api.jikan.apiJikan.model.repository.ProducerRepository;
import com.api.jikan.apiJikan.services.interfaces.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	@Override
	public void deleteProducers() {
		producerRepository.deleteAll();
	}

	@Override
	public Iterable<Producer> getAllProducers() {
		return producerRepository.findAll();
	}

	@Override
	public Producer existsProducerByName(String name) {
		
		if(producerRepository.findByName(name) != null) { //exists producer
			return producerRepository.findByName(name);
		} else {			
			return null;
		}
	}
}