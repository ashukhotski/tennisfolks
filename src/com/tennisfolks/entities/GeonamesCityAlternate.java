package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GeonamesCityAlternate
 *
 */
@Entity
@Table(name = "geonames_city_alternate")
public class GeonamesCityAlternate implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int alternateId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "geonameId")
	private GeonamesCity geonamesCity;
	
	private String language;
	
	private String name;

	public GeonamesCityAlternate() {
		super();
	}

	public GeonamesCityAlternate(int alternateId, GeonamesCity geonamesCity, String language, String name) {
		super();
		this.alternateId = alternateId;
		this.geonamesCity = geonamesCity;
		this.language = language;
		this.name = name;
	}

	public int getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(int alternateId) {
		this.alternateId = alternateId;
	}

	public GeonamesCity getGeonamesCity() {
		return geonamesCity;
	}

	public void setGeonamesCity(GeonamesCity geonamesCity) {
		this.geonamesCity = geonamesCity;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
