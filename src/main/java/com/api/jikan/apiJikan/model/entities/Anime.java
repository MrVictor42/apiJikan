package com.api.jikan.apiJikan.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "anime")
public class Anime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mal_id;
	private String title;
	private String slug;
	private String image_url;
	private String trailer_url;
	private String status;
	@Lob
	@Column(length = 1000)
	private String synopsis;
	private String airing_start;
	private int episodes;
	private double score;
	private boolean kids;
	private boolean continuing;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "anime_producers", 
    joinColumns = { @JoinColumn(name = "anime_id") }, inverseJoinColumns = { @JoinColumn(name = "producer_id") }, 
	uniqueConstraints = {@UniqueConstraint(columnNames = {"anime_id", "producer_id"})})
	@JsonManagedReference
	private Set<Producer> producers = new HashSet<Producer>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "anime_genders", 
    joinColumns = { @JoinColumn(name = "anime_id") }, inverseJoinColumns = { @JoinColumn(name = "gender_id") }, 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"anime_id", "gender_id"})})
	@JsonManagedReference
	private Set<Gender> genders = new HashSet<Gender>();
	
	public Anime() {
		
	}
	
	public void removeGender(Gender gender) {
		genders.remove(gender);
		gender.getAnimes().remove(this);
	}
	
	public void removeProducer(Producer producer) {
		producers.remove(producer);
		producer.getAnimes().remove(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Set<Producer> getProducers() {
		return producers;
	}

	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}

	public Set<Gender> getGenders() {
		return genders;
	}

	public void setGenders(Set<Gender> genders) {
		this.genders = genders;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isKids() {
		return kids;
	}

	public void setKids(boolean kids) {
		this.kids = kids;
	}

	public boolean isContinuing() {
		return continuing;
	}

	public void setContinuing(boolean continuing) {
		this.continuing = continuing;
	}

	public String getAiring_start() {
		return airing_start;
	}

	public void setAiring_start(String airing_start) {
		this.airing_start = airing_start;
	}

	public String getMal_id() {
		return mal_id;
	}

	public void setMal_id(String mal_id) {
		this.mal_id = mal_id;
	}

	public String getTrailer_url() {
		return trailer_url;
	}

	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}