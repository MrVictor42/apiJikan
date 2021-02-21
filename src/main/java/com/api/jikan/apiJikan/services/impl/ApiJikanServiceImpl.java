package com.api.jikan.apiJikan.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jikan.apiJikan.model.entities.Anime;
import com.api.jikan.apiJikan.model.entities.Gender;
import com.api.jikan.apiJikan.model.entities.Producer;
import com.api.jikan.apiJikan.services.interfaces.ApiJikanService;

@Service
public class ApiJikanServiceImpl implements ApiJikanService {

	private DateServiceImpl dateServiceImpl = new DateServiceImpl();		
	private final String JIKAN_API = 
			"https://api.jikan.moe/v3/season/" + dateServiceImpl.getYear() + "/" + dateServiceImpl.getSeason();
	
	@Autowired
	private AnimeServiceImpl animeServiceImpl;
	@Autowired
	private GenderServiceImpl genderServiceImpl;
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	@Override
	public boolean databaseIsEmpty() {
		
		if(animeServiceImpl.countAnimeInDatabase() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteDatabase() {
		
		animeServiceImpl.deleteAnime();
		genderServiceImpl.deleteGenders();
		producerServiceImpl.deleteProducers();
	}

	@Override
	public void getAnimeFromJikanService() throws JSONException, ParseException {
	
		HttpHandlerServiceImpl handlerServiceImpl = new HttpHandlerServiceImpl();
		SlugServiceImpl slugServiceImpl = new SlugServiceImpl();
		String jsonJikan = handlerServiceImpl.makeServiceCall(JIKAN_API);			
		JSONObject jikanAnimeObject = new JSONObject(jsonJikan);
		JSONArray jikanArrayAnime = jikanAnimeObject.getJSONArray("anime");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int aux = 0; aux < jikanArrayAnime.length(); aux ++) {
			
			Anime anime = new Anime();
			
			JSONObject jikanJSONObject = jikanArrayAnime.getJSONObject(aux);
			JSONArray jikanProducersArray = jikanJSONObject.getJSONArray("producers");
			JSONArray jikanGenresArray = jikanJSONObject.getJSONArray("genres");
			Date date = sdf.parse(jikanJSONObject.getString("airing_start"));
			String formattedTime = output.format(date);
			
			anime.setTitle(jikanJSONObject.getString("title"));
			anime.setSlug(slugServiceImpl.makeSlug(anime.getTitle()));
			anime.setImage_url(jikanJSONObject.getString("image_url"));
			anime.setSynopsis(jikanJSONObject.getString("synopsis"));			
			anime.setAiring_start(formattedTime);
						
			if(jikanJSONObject.getString("episodes").equals("null") || jikanJSONObject.getString("episodes").isEmpty()) {
				anime.setEpisodes(0);
			} else {				
				anime.setEpisodes(jikanJSONObject.getInt("episodes"));
			}
						
			if(jikanJSONObject.getString("score").equals("null")) {
				anime.setScore(5.0);
			} else {
				anime.setScore(jikanJSONObject.getDouble("score"));
			}
						
			anime.setKids(jikanJSONObject.getBoolean("kids"));
			anime.setContinuing(jikanJSONObject.getBoolean("continuing"));
			
			for(int auxProducer = 0; auxProducer < jikanProducersArray.length(); auxProducer ++) {
			
				JSONObject jikanProducerObject = jikanProducersArray.getJSONObject(auxProducer);
				
				if(jikanProducerObject == null) {
					
				} else {
					
					if(producerServiceImpl.existsProducerByName(jikanProducerObject.getString("name")) != null) {
						Producer producerSaved = producerServiceImpl.existsProducerByName(jikanProducerObject.getString("name"));
						
						anime.getProducers().add(producerSaved);
					} else {
						Producer producer = new Producer();					
						
						producer.setName(jikanProducerObject.getString("name"));
						anime.getProducers().add(producer);
					}
				}				
			}
												
			for(int auxGenres = 0; auxGenres < jikanGenresArray.length(); auxGenres ++) {
				
				JSONObject jikanGenresObject = jikanGenresArray.getJSONObject(auxGenres);
							
				if(jikanGenresObject == null) {
					
				} else {
										
					if(genderServiceImpl.existsGenderByName(jikanGenresObject.getString("name")) != null) {
						Gender genderSaved = genderServiceImpl.existsGenderByName(jikanGenresObject.getString("name"));
						
						anime.getGenders().add(genderSaved);		
					} else {
						Gender gender = new Gender();
						
						gender.setName(jikanGenresObject.getString("name"));
						anime.getGenders().add(gender);		
					}
				}
			}
			
			animeServiceImpl.createAnime(anime);
		}
	}

	@Override
	public void synchronizeDatabase() throws JSONException, ParseException {
		
		if(databaseIsEmpty() == true) {
			getAnimeFromJikanService();
		} else {
			
			HttpHandlerServiceImpl handlerServiceImpl = new HttpHandlerServiceImpl();
			String jsonJikan = handlerServiceImpl.makeServiceCall(JIKAN_API);			
			JSONObject jikanAnimeObject = new JSONObject(jsonJikan);
			String season_name = jikanAnimeObject.getString("season_name");
			
			if(dateServiceImpl.getSeason().equalsIgnoreCase(season_name)) {
				
			} else {
				
				deleteDatabase();
				getAnimeFromJikanService();
			}
		}
	}
}