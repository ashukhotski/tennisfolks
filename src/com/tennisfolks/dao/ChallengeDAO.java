package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.ChallengeMatch;
import com.tennisfolks.entities.OpenChallenge;

@Remote
public interface ChallengeDAO {
	
	public ChallengeMatch createChallengeMatch(ChallengeMatch challengeMatch);
	
	public OpenChallenge createOpenChallenge(OpenChallenge openChallenge);
	
	public ChallengeMatch updateChallengeMatch(ChallengeMatch challengeMatch);
	
	public OpenChallenge updateOpenChallenge(OpenChallenge openChallenge);
	
	public ChallengeMatch findChallengeMatch(int id);
	
	public OpenChallenge findOpenChallenge(int id);
	
	public List<ChallengeMatch> findChallengeMatches(Map<String, Object> params, int startPosition, int maxResults);
	
	public List<OpenChallenge> findOpenChallenges(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countChallengeMatches(Map<String, Object> params);
	
	public int countOpenChallenges(Map<String, Object> params);

}
