package com.api.jikan.apiJikan.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "top_anime")
public class TopAnime extends Anime {
	
	private String start_date;
	private String end_date;
	
	public TopAnime() {
		
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}