package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: LeagueMember
 *
 */
@Entity
@Table(name = "league_member")
public class LeagueMember implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "league")
	private League league; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	private boolean accepted = false; 
	
	private boolean rejected = false;
	
	private boolean deleted = false;
	
	private Date paymentDate;
	
	private Date expirationDate;
	
	private int tournamentPoints = 0;
	
	private int challengePoints = 0;

	public LeagueMember() {
		super();
	}

	public LeagueMember(String id, League league, User user, boolean accepted, boolean rejected,
			boolean deleted, Date paymentDate, Date expirationDate, int tournamentPoints, int challengePoints) {
		super();
		this.id = id;
		this.league = league;
		this.user = user;
		this.accepted = accepted;
		this.rejected = rejected;
		this.deleted = deleted;
		this.paymentDate = paymentDate;
		this.expirationDate = expirationDate;
		this.tournamentPoints = tournamentPoints;
		this.challengePoints = challengePoints;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getTournamentPoints() {
		return tournamentPoints;
	}

	public void setTournamentPoints(int tournamentPoints) {
		this.tournamentPoints = tournamentPoints;
	}

	public int getChallengePoints() {
		return challengePoints;
	}

	public void setChallengePoints(int challengePoints) {
		this.challengePoints = challengePoints;
	}
   
}
