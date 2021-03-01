package com.api.jikan.apiJikan.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.jikan.apiJikan.model.entities.Anime;

public interface AnimeRepository extends CrudRepository<Anime, Integer> {
	
	public Anime findBySlug(String slug);
	@Query(value = "SELECT DISTINCT A.* FROM anime A LEFT JOIN anime_genders AG ON A.id = AG.anime_id "
			+ "LEFT JOIN anime_producers AP ON A.id = AP.anime_id WHERE AG.gender_id = (?) AND AP.producer_id = (?)", 
			nativeQuery = true)
	public Iterable<Anime> findByGendersAndByProducerId(int genderId, int producerId);
	@Query(value = "SELECT DISTINCT A.* FROM anime A LEFT JOIN anime_genders AG ON A.id = AG.anime_id "
			+ "LEFT JOIN anime_producers AP ON A.id = AP.anime_id WHERE AG.gender_id = (?)", 
			nativeQuery = true)
	public Iterable<Anime> findByGendersById(int genderId);
	@Query(value = "SELECT DISTINCT A.* FROM anime A LEFT JOIN anime_genders AG ON A.id = AG.anime_id "
			+ "LEFT JOIN anime_producers AP ON A.id = AP.anime_id WHERE AP.producer_id = (?)", 
			nativeQuery = true)
	public Iterable<Anime> findByProducersById(int producersId);
	@Query(value = "DROP DATABASE IF EXISTS apiJikanBoot", nativeQuery = true)
	public void deleteDatabase();
}