package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TournamentParticipant
 *
 */
@Entity
@Table(name = "tournament_participant")
public class TournamentParticipant implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tournament")
	private Tournament tournament;
	
	@Transient
	private int tournamentId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	@Transient
	private int participantId;
	
	private boolean confirmed = false;
	
	private Date registrationDate;
	
	private int top = 0;

	public TournamentParticipant() {
		super();
	}

	public TournamentParticipant(String id, Tournament tournament, User user, boolean confirmed,
			Date registrationDate, int top) {
		super();
		this.id = id;
		this.tournament = tournament;
		this.user = user;
		this.confirmed = confirmed;
		this.registrationDate = registrationDate;
		this.top = top;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (tournament != null) { tournamentId = tournament.getId(); }
		if (user != null) { participantId = user.getId(); }
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
	@PrePersist
	protected void onCreate() {
		this.registrationDate = new Date();
	}
   
}
