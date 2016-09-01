package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GeonamesAdmin
 *
 */
@Entity
@Table(name = "geonames_admin")
public class GeonamesAdmin implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String adminCode;
	
	private String country;
	
	private String name;
	
	private String asciiname;
	
	@Id
	private int geonameId;

	public GeonamesAdmin() {
		super();
	}

	public GeonamesAdmin(String id, String adminCode, String country, String name, String asciiname, int geonameId) {
		super();
		this.id = id;
		this.adminCode = adminCode;
		this.country = country;
		this.name = name;
		this.asciiname = asciiname;
		this.geonameId = geonameId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public int getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(int geonameId) {
		this.geonameId = geonameId;
	}
   
}
