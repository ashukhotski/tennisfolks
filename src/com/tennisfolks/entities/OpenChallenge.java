package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OpenChallenge
 *
 */
@Entity
@Table(name = "open_challenge")
public class OpenChallenge implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int matchId = 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "firstPlayer")
	private User firstPlayer;
	
	@Transient
	private int firstPlayerId;
	
	private boolean accepted = false;
	
	private boolean deleted = false;
	
	private Date startDate;
	
	@Transient
	private int startDateDay;
	
	@Transient
	private int startDateMonth;
	
	@Transient
	private int startDateYear;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location")
	private Location location;
	
	@Transient
	private String country;
	
	@Transient
	private String state;
	
	@Transient
	private String city;
	
	@Transient
	private int cityGeonameId;
	
	@Transient
	private String address;
	
	private Date creationDate;
	
	private String description;
	
	private String surfaceType;

	public OpenChallenge() {
		super();
	}

	public OpenChallenge(int id, int matchId, User firstPlayer, boolean accepted, boolean deleted, 
			Date startDate, Location location, Date creationDate, String description, String surfaceType) {
		super();
		this.id = id;
		this.matchId = matchId;
		this.firstPlayer = firstPlayer;
		this.accepted = accepted;
		this.deleted = deleted;
		this.startDate = startDate;
		this.location = location;
		this.creationDate = creationDate;
		this.description = description;
		this.surfaceType = surfaceType;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (firstPlayer != null) firstPlayerId = firstPlayer.getId();
		if (startDate != null) {
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			startDateDay = start.get(Calendar.DATE);
			startDateMonth = start.get(Calendar.MONTH) + 1;
			startDateYear = start.get(Calendar.YEAR);
		}
		if (location != null) {
			country = location.getCountry();
			if (location.getGeonamesAdmin() != null) state = location.getGeonamesAdmin().getAdminCode();
			city = location.getCity();
			if (location.getGeonamesCity() != null) cityGeonameId = location.getGeonamesCity().getGeonameId();
			address = location.getAddress();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public User getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(User firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	public int getFirstPlayerId() {
		return firstPlayerId;
	}

	public void setFirstPlayerId(int firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public int getStartDateDay() {
		return startDateDay;
	}

	public void setStartDateDay(int startDateDay) {
		this.startDateDay = startDateDay;
	}

	public int getStartDateMonth() {
		return startDateMonth;
	}

	public void setStartDateMonth(int startDateMonth) {
		this.startDateMonth = startDateMonth;
	}

	public int getStartDateYear() {
		return startDateYear;
	}

	public void setStartDateYear(int startDateYear) {
		this.startDateYear = startDateYear;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityGeonameId() {
		return cityGeonameId;
	}

	public void setCityGeonameId(int cityGeonameId) {
		this.cityGeonameId = cityGeonameId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(String surfaceType) {
		this.surfaceType = surfaceType;
	}

	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
   
}
