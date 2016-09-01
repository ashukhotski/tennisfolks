package com.tennisfolks.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LeagueBlogEntry
 *
 */
@Entity
@Table(name = "league_blog_entry")
public class LeagueBlogEntry implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date creationDate;
	
	@Transient
	private String formattedDate;
	
	@Transient
	private int dateDay;
	
	@Transient
	private int dateMonth;
	
	@Transient
	private int dateYear;
	
	private String header;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	private League league;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private User author;
	
	private boolean published = false;
	
	private boolean deleted = false;

	public LeagueBlogEntry() {
		super();
	}

	public LeagueBlogEntry(int id, Date creationDate, String header, String text, League league, User author,
			boolean published, boolean deleted) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.header = header;
		this.text = text;
		this.league = league;
		this.author = author;
		this.published = published;
		this.deleted = deleted;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (creationDate != null) {
			formattedDate = (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(creationDate);
			
			Calendar creation = Calendar.getInstance();
			creation.setTime(creationDate);
			dateDay = creation.get(Calendar.DATE);
			dateMonth = creation.get(Calendar.MONTH) + 1;
			dateYear = creation.get(Calendar.YEAR);
		} else { formattedDate = ""; }
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public int getDateDay() {
		return dateDay;
	}

	public void setDateDay(int dateDay) {
		this.dateDay = dateDay;
	}

	public int getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(int dateMonth) {
		this.dateMonth = dateMonth;
	}

	public int getDateYear() {
		return dateYear;
	}

	public void setDateYear(int dateYear) {
		this.dateYear = dateYear;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
   
}
