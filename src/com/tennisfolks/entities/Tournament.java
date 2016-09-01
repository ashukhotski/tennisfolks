package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tournament
 *
 */
@Entity
@Table(name = "tournament")
public class Tournament implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private int targetLevel = 3;
	
	private int levelShift = 1;
	
	private int numberOfPlayers = 16;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	private League league;
	
	@Transient
	private int leagueId;
	
	private Date startDate;
	
	@Transient
	private int startDateDay;
	
	@Transient
	private int startDateMonth;
	
	@Transient
	private int startDateYear;
	
	private Date endDate;
	
	@Transient
	private int endDateDay;
	
	@Transient
	private int endDateMonth;
	
	@Transient
	private int endDateYear;
	
	private boolean finished = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supervisor")
	private User supervisor;
	
	@Transient
	private int supervisorId;
	
	private String jsonData = "";
	
	private boolean deleted = false;

	public Tournament() {
		super();
	}

	public Tournament(int id, String name, String description, int targetLevel, int levelShift,
			int numberOfPlayers, League league, Date startDate, Date endDate,
			boolean finished, User supervisor, String jsonData, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.targetLevel = targetLevel;
		this.levelShift = levelShift;
		this.numberOfPlayers = numberOfPlayers;
		this.league = league;
		this.startDate = startDate;
		this.endDate = endDate;
		this.finished = finished;
		this.supervisor = supervisor;
		this.jsonData = jsonData;
		this.deleted = deleted;
	}

	@PostLoad
	private void initTransientFields() {
		if (league != null) { leagueId = league.getId(); }
		if (startDate != null) {
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			startDateDay = start.get(Calendar.DATE);
			startDateMonth = start.get(Calendar.MONTH) + 1;
			startDateYear = start.get(Calendar.YEAR);
		}
		if (endDate != null) {
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			endDateDay = end.get(Calendar.DATE);
			endDateMonth = end.get(Calendar.MONTH) + 1;
			endDateYear = end.get(Calendar.YEAR);
		}
		if (supervisor != null) { supervisorId = supervisor.getId(); }
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(int targetLevel) {
		this.targetLevel = targetLevel;
	}

	public int getLevelShift() {
		return levelShift;
	}

	public void setLevelShift(int levelShift) {
		this.levelShift = levelShift;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getEndDateDay() {
		return endDateDay;
	}

	public void setEndDateDay(int endDateDay) {
		this.endDateDay = endDateDay;
	}

	public int getEndDateMonth() {
		return endDateMonth;
	}

	public void setEndDateMonth(int endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	public int getEndDateYear() {
		return endDateYear;
	}

	public void setEndDateYear(int endDateYear) {
		this.endDateYear = endDateYear;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
   
}
