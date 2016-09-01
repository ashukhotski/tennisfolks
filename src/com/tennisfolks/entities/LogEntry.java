package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Log
 *
 */
@Entity
@Table(name = "log_entry")
public class LogEntry implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date activityDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	private String notes;

	public LogEntry() {
		super();
	}

	public LogEntry(int id, Date activityDate, User user, String notes) {
		super();
		this.id = id;
		this.activityDate = activityDate;
		this.user = user;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@PrePersist
	public void onCreate() {
		this.activityDate = new Date();
	}
   
}
