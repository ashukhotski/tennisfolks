package com.tennisfolks.entities;

import java.io.Serializable;
//import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GeonamesCity
 *
 */
@Entity
@Table(name = "geonames_city")
public class GeonamesCity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int geonameId;
	
	private String name;
	
	private String asciiname;
	
	@Column(columnDefinition = "Float(10,6)")
	private float latitude;
	
	@Column(columnDefinition = "Float(10,6)")
	private float longitude;
	
	private String countryCode;
	
	private String adminCode;
	
	private int population;
	
	private String timezone;
	
	public GeonamesCity() {
		super();
	}

	public GeonamesCity(int geonameId, String name, String asciiname, float latitude, float longitude,
			String countryCode, String adminCode, int population, String timezone) {
		super();
		this.geonameId = geonameId;
		this.name = name;
		this.asciiname = asciiname;
		this.latitude = latitude;
		this.longitude = longitude;
		this.countryCode = countryCode;
		this.adminCode = adminCode;
		this.population = population;
		this.timezone = timezone;
	}

	public int getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(int geonameId) {
		this.geonameId = geonameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAsciiname() {
		return asciiname;
	}

	public void setAsciiname(String asciiname) {
		this.asciiname = asciiname;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
   
}
