package com.api.jikan.apiJikan.services.interfaces;

import java.text.ParseException;

import org.json.JSONException;

public interface ApiJikanService {
	
	public boolean databaseIsEmpty();
	public void deleteDatabase();
	public void createDatabase();
	public void getAnimeFromJikanService() throws JSONException, ParseException;
	public void synchronizeDatabase() throws JSONException, ParseException;
}