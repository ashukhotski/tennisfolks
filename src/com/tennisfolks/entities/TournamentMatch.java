package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TournamentMatch
 *
 */
@Entity
@Table(name = "tournament_match")
public class TournamentMatch implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tournament")
	private Tournament tournament;
	
	@Transient
	private int tournamentId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "firstPlayer")
	private User firstPlayer;
	
	@Transient
	private int firstPlayerId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "secondPlayer")
	private User secondPlayer;
	
	@Transient
	private int secondPlayerId;
	
	private int firstPlayerScore = 0;
	
	private int secondPlayerScore = 0;
	
	private String detailedScore;
	
	private String matchReview;
	
	@Transient
	private User winner;
	
	private int stage;
	
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
	
	private boolean approved = false;
	
	private boolean deleted = false;
	
	private String surfaceType;
	
	private String description;
	
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

	public TournamentMatch() {
		super();
	}

	public TournamentMatch(int id, Tournament tournament, User firstPlayer, User secondPlayer, int firstPlayerScore,
			int secondPlayerScore, String detailedScore, String matchReview, int stage, Date startDate, Date endDate, Location location, 
			boolean finished, boolean approved, boolean deleted, String surfaceType, String description) {
		super();
		this.id = id;
		this.tournament = tournament;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.firstPlayerScore = firstPlayerScore;
		this.secondPlayerScore = secondPlayerScore;
		this.detailedScore = detailedScore;
		this.matchReview = matchReview;
		this.stage = stage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location =location;
		this.finished = finished;
		this.approved = approved;
		this.deleted = deleted;
		this.surfaceType = surfaceType;
		this.description = description;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (tournament != null) tournamentId = tournament.getId();
		if (firstPlayer != null) firstPlayerId = firstPlayer.getId();
		if (secondPlayer != null) secondPlayerId = secondPlayer.getId();
		if (firstPlayerScore > secondPlayerScore) {
			winner = firstPlayer; 
		} else if (secondPlayerScore > firstPlayerScore) {
			winner = secondPlayer;
		} else { winner = null; }
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

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
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

	public User getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(User secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	
	public int getSecondPlayerId() {
		return secondPlayerId;
	}

	public void setSecondPlayerId(int secondPlayerId) {
		this.secondPlayerId = secondPlayerId;
	}

	public int getFirstPlayerScore() {
		return firstPlayerScore;
	}

	public void setFirstPlayerScore(int firstPlayerScore) {
		this.firstPlayerScore = firstPlayerScore;
	}

	public int getSecondPlayerScore() {
		return secondPlayerScore;
	}

	public void setSecondPlayerScore(int secondPlayerScore) {
		this.secondPlayerScore = secondPlayerScore;
	}

	public String getDetailedScore() {
		return detailedScore;
	}

	public void setDetailedScore(String detailedScore) {
		this.detailedScore = detailedScore;
	}

	public String getMatchReview() {
		return matchReview;
	}

	public void setMatchReview(String matchReview) {
		this.matchReview = matchReview;
	}

	public User getWinner() {
		if (firstPlayerScore > secondPlayerScore) {
			winner = firstPlayer; 
		} else if (secondPlayerScore > firstPlayerScore) {
			winner = secondPlayer;
		} else { winner = null; }
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(String surfaceType) {
		this.surfaceType = surfaceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
   
}
