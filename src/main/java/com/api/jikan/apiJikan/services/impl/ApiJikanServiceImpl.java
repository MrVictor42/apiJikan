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
import com.api.jikan.apiJikan.model.entities.Manga;
import com.api.jikan.apiJikan.model.entities.Producer;
import com.api.jikan.apiJikan.model.entities.TopAnime;
import com.api.jikan.apiJikan.services.interfaces.ApiJikanService;

@Service
public class ApiJikanServiceImpl implements ApiJikanService {

	private DateServiceImpl dateServiceImpl = new DateServiceImpl();		
	private final String JIKAN_API = 
			"https://api.jikan.moe/v3/season/" + dateServiceImpl.getYear() + "/" + dateServiceImpl.getSeason();
	private final String TOP_MANGA = "https://api.jikan.moe/v3/top/manga";
	private final String TOP_ANIME = "https://api.jikan.moe/v3/top/anime";
	private final String ANIME_DETAILS = "https://api.jikan.moe/v3/anime/";
	private final String MANGA_DETAILS = "https://api.jikan.moe/v3/manga/";
	
	@Autowired
	private AnimeServiceImpl animeServiceImpl;
	@Autowired
	private GenderServiceImpl genderServiceImpl;
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	@Autowired
	private MangaServiceImpl mangaServiceImpl;
	@Autowired
	private TopAnimeServiceImpl topAnimeServiceImpl;
	
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
		animeServiceImpl.deleteDatabase();
	}
	
	@Override
	public void createDatabase() {
		animeServiceImpl.createDatabase();		
	}

	@Override
	public void getAnimeFromJikanService() throws JSONException, ParseException {
	
		HttpHandlerServiceImpl handlerServiceImpl = new HttpHandlerServiceImpl();
		SlugServiceImpl slugServiceImpl = new SlugServiceImpl();
		String jsonJikan = handlerServiceImpl.makeServiceCall(JIKAN_API);
		String jsonManga = handlerServiceImpl.makeServiceCall(TOP_MANGA);
		String jsonAnime = handlerServiceImpl.makeServiceCall(TOP_ANIME);
		JSONObject jikanAnimeObject = new JSONObject(jsonJikan);
		JSONObject jikanMangaObject = new JSONObject(jsonManga);
		JSONObject jikanTopAnimeObject = new JSONObject(jsonAnime);
		JSONArray jikanArrayAnime = jikanAnimeObject.getJSONArray("anime");
		JSONArray jikanArrayManga = jikanMangaObject.getJSONArray("top");
		JSONArray jikanArrayAnimeTop = jikanTopAnimeObject.getJSONArray("top");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int aux = 0; aux < jikanArrayAnime.length(); aux ++) {
						
			JSONObject jikanJSONObject = jikanArrayAnime.getJSONObject(aux);
			String mal_id = jikanJSONObject.getString("mal_id");
			String jsonAnimeDetails = handlerServiceImpl.makeServiceCall(ANIME_DETAILS + mal_id);
			
			if(jsonAnimeDetails == null) {
				
			} else {
				
				Anime anime = new Anime();
				JSONObject jikanAnimeDetailObject = new JSONObject(jsonAnimeDetails);
				JSONArray jikanProducersArray = jikanJSONObject.getJSONArray("producers");
				JSONArray jikanGenresArray = jikanJSONObject.getJSONArray("genres");	
				
				anime.setTitle(jikanJSONObject.getString("title"));
				anime.setMal_id(jikanJSONObject.getString("mal_id"));
				anime.setSlug(slugServiceImpl.makeSlug(anime.getTitle()));
				anime.setImage_url(jikanJSONObject.getString("image_url"));
				anime.setSynopsis(jikanJSONObject.getString("synopsis"));
				
				if(jikanAnimeDetailObject.getString("trailer_url").equals("null") ||
						jikanAnimeDetailObject.getString("trailer_url").isEmpty()) {
					anime.setTrailer_url("Not Found Trailer For This Anime :/");
				} else {
					anime.setTrailer_url(jikanAnimeDetailObject.getString("trailer_url"));				
				}
				
				if(jikanAnimeDetailObject.getString("status").equals("null")) {
					anime.setStatus("Undefined");
				} else {
					anime.setStatus(jikanAnimeDetailObject.getString("status"));
				}
							
				if(jikanJSONObject.getString("airing_start").equals("null")) {
					anime.setAiring_start("Undefined");
				} else {
					
					Date date = sdf.parse(jikanJSONObject.getString("airing_start"));
					String formattedTime = output.format(date);
					anime.setAiring_start(formattedTime);
				}
							
				if(jikanJSONObject.getString("episodes").equals("null")) {
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
							
		for(int aux = 0; aux < jikanArrayManga.length(); aux ++) {
			
			JSONObject jikanMangaJSONObject = jikanArrayManga.getJSONObject(aux);
			String mal_id = jikanMangaJSONObject.getString("mal_id");
			String jsonMangaDetails = handlerServiceImpl.makeServiceCall(MANGA_DETAILS + mal_id);
			
			if(jsonMangaDetails == null) {
				
			} else {
				
				Manga manga = new Manga();
				JSONObject jikanMangaDetailObject = new JSONObject(jsonMangaDetails);
				
				manga.setTitle(jikanMangaJSONObject.getString("title"));
				manga.setSlug(slugServiceImpl.makeSlug(manga.getTitle()));
				manga.setStart_date(jikanMangaJSONObject.getString("start_date"));
				manga.setImage_url(jikanMangaJSONObject.getString("image_url"));
				
				if(jikanMangaDetailObject.getString("type").equals("null")) {
					manga.setType("Undefined");
				} else {
					manga.setType(jikanMangaDetailObject.getString("type"));
				}
				
				if(jikanMangaDetailObject.getString("status").equals("null")) {
					manga.setStatus("Undefined");
				} else {
					manga.setStatus(jikanMangaDetailObject.getString("status"));
				}
				
				if(jikanMangaDetailObject.getString("chapters").equals("null")) {
					manga.setChapter(0);
				} else {
					manga.setChapter(jikanMangaDetailObject.getInt("chapters"));
				}
				
				if(jikanMangaJSONObject.getString("volumes").equals("null")) {
					manga.setVolumes(0);
				} else {
					manga.setVolumes(jikanMangaJSONObject.getInt("volumes"));
				}
				
				if(jikanMangaJSONObject.getString("end_date").equals("null")) {
					manga.setEnd_date("Undefined");
				} else {
					manga.setEnd_date(jikanMangaJSONObject.getString("end_date"));
				}
				
				if(jikanMangaJSONObject.getString("score").equals("null")) {
					manga.setScore(0.0);
				} else {
					manga.setScore(jikanMangaJSONObject.getDouble("score"));
				}
				
				mangaServiceImpl.createManga(manga);
			}
		}

		for(int aux = 0; aux < jikanArrayAnimeTop.length(); aux ++) {
			
			TopAnime anime = new TopAnime();
			
			JSONObject jikanAnimeTopJSONObject = jikanArrayAnimeTop.getJSONObject(aux);
			
			anime.setTitle(jikanAnimeTopJSONObject.getString("title"));
			anime.setSlug(slugServiceImpl.makeSlug(anime.getTitle()));
			anime.setImage_url(jikanAnimeTopJSONObject.getString("image_url"));
			anime.setStart_date(jikanAnimeTopJSONObject.getString("start_date"));
			
			if(jikanAnimeTopJSONObject.getString("episodes").equals("null")) {
				anime.setEpisodes(0);
			} else {				
				anime.setEpisodes(jikanAnimeTopJSONObject.getInt("episodes"));
			}
			
			if(jikanAnimeTopJSONObject.getString("end_date").equals("null")) {
				anime.setEnd_date("Undefined");
			} else {
				anime.setEnd_date(jikanAnimeTopJSONObject.getString("end_date"));
			}
			
			if(jikanAnimeTopJSONObject.getString("score").equals("null")) {
				anime.setScore(0.0);
			} else {
				anime.setScore(jikanAnimeTopJSONObject.getDouble("score"));
			}
			
			topAnimeServiceImpl.createTopAnime(anime);
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
			JSONArray jikanArrayAnime = jikanAnimeObject.getJSONArray("anime");
						
			if(animeServiceImpl.countAnimeInDatabase() == jikanArrayAnime.length()) {
				return;
			} else {

				deleteDatabase();
				createDatabase();
				getAnimeFromJikanService();
			}
		}
	}
}