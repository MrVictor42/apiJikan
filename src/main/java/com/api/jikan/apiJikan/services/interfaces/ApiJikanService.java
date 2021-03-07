package com.api.jikan.apiJikan.services.interfaces;

import java.text.ParseException;

import org.json.JSONException;

public interface ApiJikanService {
	
	public boolean databaseIsEmpty();
	public void deleteDatabase();
	public void synchronize() throws JSONException, ParseException;
	public void getAnimeFromJikanService() throws JSONException, ParseException;
}