package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TournamentMatchPhotos
 *
 */
@Entity
@Table(name = "tournament_match_photo")
public class TournamentMatchPhoto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "match")
	private TournamentMatch match;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tournament")
	private Tournament tournament;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	private League league;
	
	private Date uploadDate;
	
	private String description;
	
	private boolean deleted = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private User author;

	public TournamentMatchPhoto() {
		super();
	}

	public TournamentMatchPhoto(int id, String url, TournamentMatch match, Tournament tournament, League league, Date uploadDate, String description, 
			boolean deleted, User author) {
		super();
		this.id = id;
		this.url = url;
		this.match = match;
		this.tournament = tournament;
		this.league = league;
		this.uploadDate = uploadDate;
		this.description = description;
		this.deleted = deleted;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TournamentMatch getMatch() {
		return match;
	}

	public void setMatch(TournamentMatch match) {
		this.match = match;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	@PrePersist
	public void onCreate() {
		this.uploadDate = new Date();
	}
   
}
