package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.Tournament;
import com.tennisfolks.entities.TournamentMatch;
import com.tennisfolks.entities.TournamentParticipant;
import com.tennisfolks.entities.User;

@Remote
public interface TournamentDAO {
	
	public Tournament create(Tournament t);
	
	public Tournament update(Tournament t);
	
	public Tournament find(int id);
	
	public List<Tournament> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public int count(Map<String, Object> params);
	
	public TournamentParticipant createParticipant(TournamentParticipant tp);
	
	public TournamentParticipant updateParticipant(TournamentParticipant tp);
	
	public TournamentParticipant findParticipant(String id);
	
	public void deleteParticipant(TournamentParticipant tp);
	
	public boolean isParticipant(int tournamentId, int participantId);
	
	public boolean isConfirmedParticipant(int tournamentId, int participantId);
	
	public List<TournamentParticipant> findParticipants(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countParticipants(Map<String, Object> params);
	
	public TournamentMatch createMatch(TournamentMatch tm);
	
	public TournamentMatch updateMatch(TournamentMatch tm);
	
	public TournamentMatch findMatch(int id);
	
	public List<TournamentMatch> findMatches(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countMatches(Map<String, Object> params);

	public List<User> searchParticipantsById(Map<String, Object> params, int startPosition, int maxResults);

}
