package com.api.jikan.apiJikan.services.interfaces;

import com.api.jikan.apiJikan.model.entities.Producer;

public interface ProducerService {
	
	public void deleteProducers();
	public Iterable	<Producer> getAllProducers();
	public Producer existsProducerByName(String name);
}