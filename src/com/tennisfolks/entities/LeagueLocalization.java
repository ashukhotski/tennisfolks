package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LeagueLocalization
 *
 */
@Entity
@Table(name = "league_localization")
public class LeagueLocalization implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	private League league;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language")
	private Language language;
	
	private String name;
	
	private String description;

	public LeagueLocalization() {
		super();
	}

	public LeagueLocalization(String id, League league, Language language, String name, String description) {
		super();
		this.id = id;
		this.league = league;
		this.language = language;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
