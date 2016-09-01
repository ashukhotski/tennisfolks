package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GeonamesAdminAlternate
 *
 */
@Entity
@Table(name = "geonames_admin_alternate")
public class GeonamesAdminAlternate implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int alternateId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "geonameId")
	private GeonamesAdmin geonamesAdmin;
	
	private String language;
	
	private String name;

	public GeonamesAdminAlternate() {
		super();
	}

	public GeonamesAdminAlternate(int alternateId, GeonamesAdmin geonamesAdmin, String language, String name) {
		super();
		this.alternateId = alternateId;
		this.geonamesAdmin = geonamesAdmin;
		this.language = language;
		this.name = name;
	}

	public int getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(int alternateId) {
		this.alternateId = alternateId;
	}

	public GeonamesAdmin getGeonamesAdmin() {
		return geonamesAdmin;
	}

	public void setGeonamesAdmin(GeonamesAdmin geonamesAdmin) {
		this.geonamesAdmin = geonamesAdmin;
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
