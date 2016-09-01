package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.League;
import com.tennisfolks.entities.LeagueMember;
import com.tennisfolks.entities.User;

@Remote
public interface LeagueDAO {
	
	public League create(League league);
	
	public League update(League league);
	
	public League find(int id);
	
	public List<League> find();
	
	public List<League> find(Map<String, Object> params);
	
	public List<League> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public LeagueMember createLeagueMember(LeagueMember leagueMember);
	
	public LeagueMember updateLeagueMember(LeagueMember leagueMember);
	
	public LeagueMember findLeagueMember(String id);
	
	public List<LeagueMember> getMembers(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countLeagues(Map<String, Object> params);
	
	public int countMembers();
	
	public int countMembers(Map<String, Object> params);
	
	public List<LeagueMember> getMemberLeagues(Map<String, Object> params, int startPosition, int maxResults);

	public int countMemberLeagues(Map<String, Object> params);
	
	public boolean isMember(int leagueId, int userId);
	
	public boolean isRequestor(int leagueId, int userId);

	boolean isDeleted(int leagueId, int userId);

	boolean isRejected(int leagueId, int userId);

	List<User> searchAcceptedMembersByName(Map<String, Object> params, int startPosition, int maxResults);

	List<User> searchAcceptedMembersById(Map<String, Object> params, int startPosition, int maxResults);

}
