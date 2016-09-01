package com.tennisfolks.service;

import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.tennisfolks.dao.ChallengeDAO;
import com.tennisfolks.dao.MessageDAO;
import com.tennisfolks.dao.TournamentDAO;
import com.tennisfolks.dao.UserFollowerDAO;
import com.tennisfolks.util.UserSessionService;

@Path("/helper") @Stateless
public class HelperService {
	
	@EJB
	MessageDAO messageDAO;
	
	@EJB
	ChallengeDAO challengeDAO;
	
	@EJB
	TournamentDAO tournamentDAO;
	
	@EJB
	UserFollowerDAO followerDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@GET
	@Path("/{id}/messages")
	public int newMessages(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() == id)
		) {
			int size = messageDAO.countNewMessages(id);
			return size;
		}
		return 0;
	}
	
	@GET
	@Path("/{id}/challenges")
	public int newChallenges(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() == id)
		) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("secondPlayer", id);
			params.put("accepted", false);
			params.put("rejected", false);
			int size = challengeDAO.countChallengeMatches(params);
			return size;
		}
		return 0;
	}

}
