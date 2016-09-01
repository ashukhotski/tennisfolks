package com.tennisfolks.service;

import java.util.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.tennisfolks.dao.LeagueDAO;
import com.tennisfolks.dao.LocationDAO;
import com.tennisfolks.dao.TournamentDAO;
import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.League;
import com.tennisfolks.entities.LeagueMember;
import com.tennisfolks.entities.Location;
import com.tennisfolks.entities.Tournament;
import com.tennisfolks.entities.TournamentMatch;
import com.tennisfolks.entities.TournamentParticipant;
import com.tennisfolks.entities.User;
import com.tennisfolks.util.UserSessionService;

@Path("/tournaments") @Stateless
public class TournamentService {
	
	@EJB
	TournamentDAO tournamentDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	LeagueDAO leagueDAO;
	
	@EJB
	LocationDAO locationDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@Resource
	EJBContext ejbContext;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament createTournament(
		Tournament t, 
		@QueryParam("league") int league,
		@QueryParam("token") String token
	) {
		if (
			(t != null) && (t.getLeagueId() == league) && 
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token)
		) {
			League l = leagueDAO.find(t.getLeagueId());
			if (
				(l != null) && 
				(
					(l.getManagerId() == userSessionService.getSession().getId()) ||
					(l.getCreatorId() == userSessionService.getSession().getId())
				)
			) {
				Tournament tournament = new Tournament();
				tournament.setDeleted(false);
				tournament.setDescription(t.getDescription());
				tournament.setFinished(false);
				tournament.setName(t.getName());
				tournament.setLeague(l);
				tournament.setSupervisor(userDAO.find(userSessionService.getSession().getId()));
				tournament.setNumberOfPlayers(t.getNumberOfPlayers());
				tournament.setTargetLevel(t.getTargetLevel());
				tournament.setLevelShift(t.getLevelShift());
				if (
					(t.getStartDateDay() > 0) && 
					(t.getStartDateMonth() > 0) && 
					(t.getStartDateYear() > 0)
				) {
					Calendar start = Calendar.getInstance();
					start.set(Calendar.DATE, t.getStartDateDay());
					start.set(Calendar.MONTH, t.getStartDateMonth() - 1);
					start.set(Calendar.YEAR, t.getStartDateYear());
					tournament.setStartDate(start.getTime());
					tournament.setEndDate(tournament.getStartDate());
					if (
						(t.getEndDateDay() > 0) && 
						(t.getEndDateMonth() > 0) && 
						(t.getEndDateYear() > 0)
					) {
						Calendar end = Calendar.getInstance();
						end.set(Calendar.DATE, t.getEndDateDay());
						end.set(Calendar.MONTH, t.getEndDateMonth() - 1);
						end.set(Calendar.YEAR, t.getEndDateYear());
						if (end.getTimeInMillis() >= start.getTimeInMillis()) tournament.setEndDate(end.getTime());
					}
					return tournamentDAO.create(tournament);
				}			
			}
		}
		return null;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament updateTournament(
		Tournament t, 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		Tournament tournament = tournamentDAO.find(id);
		if (
			(t != null) && (tournament != null) &&
			(t.getId() == tournament.getId()) && 
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tournament.getSupervisorId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getCreatorId() == userSessionService.getSession().getId())
			)
		) {
			tournament.setDeleted(t.isDeleted());
			tournament.setDescription(t.getDescription());
			tournament.setName(t.getName());
			tournament.setNumberOfPlayers(t.getNumberOfPlayers());
			tournament.setTargetLevel(t.getTargetLevel());
			tournament.setLevelShift(t.getLevelShift());
			if (
				(t.getStartDateDay() > 0) && 
				(t.getStartDateMonth() > 0) && 
				(t.getStartDateYear() > 0)
			) {
				Calendar start = Calendar.getInstance();
				start.set(Calendar.DATE, t.getStartDateDay());
				start.set(Calendar.MONTH, t.getStartDateMonth() - 1);
				start.set(Calendar.YEAR, t.getStartDateYear());
				tournament.setStartDate(start.getTime());
				tournament.setEndDate(tournament.getStartDate());
				if (
					(t.getEndDateDay() > 0) && 
					(t.getEndDateMonth() > 0) && 
					(t.getEndDateYear() > 0)
				) {
					Calendar end = Calendar.getInstance();
					end.set(Calendar.DATE, t.getEndDateDay());
					end.set(Calendar.MONTH, t.getEndDateMonth() - 1);
					end.set(Calendar.YEAR, t.getEndDateYear());
					if (end.getTimeInMillis() >= start.getTimeInMillis()) tournament.setEndDate(end.getTime());
				}
				return tournamentDAO.update(tournament);
			}
		}
		return tournament;
	}
	
	@POST
	@Path("/{id}/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament deleteTournament( 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		Tournament tournament = tournamentDAO.find(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tournament.getSupervisorId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getCreatorId() == userSessionService.getSession().getId())
			)
		) {
			if (!tournament.isDeleted()) {
				tournament.setDeleted(true);
				return tournamentDAO.update(tournament);
			}
		}
		return tournament;
	}
	
	@POST
	@Path("/{id}/restore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament restoreTournament( 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		Tournament tournament = tournamentDAO.find(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tournament.getSupervisorId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
				(tournament.getLeague().getCreatorId() == userSessionService.getSession().getId())
			)
		) {
			if (tournament.isDeleted()) {
				tournament.setDeleted(false);
				return tournamentDAO.update(tournament);
			}
		}
		return tournament;
	}
	
	@POST
	@Path("/{id}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament finishTournament(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		Tournament t = tournamentDAO.find(id);
		if (
			(t != null) && 
			userSessionService.getSession().ok() && 
			(
				(userSessionService.getSession().getId() == t.getSupervisorId()) || 
				(userSessionService.getSession().getId() == t.getLeague().getCreatorId()) ||
				(userSessionService.getSession().getId() == t.getLeague().getManagerId())				
			)
		) {
			t.setFinished(true);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("tournament", id);
			params.put("confirmed", true);
			int size = tournamentDAO.countParticipants(params);
			List<TournamentParticipant> participants = tournamentDAO.findParticipants(params, 0, size);
			if (participants != null) {
				LeagueMember lm;
				for (TournamentParticipant tp : participants) {
					lm = leagueDAO.findLeagueMember(t.getLeagueId() + "-" + tp.getParticipantId());
					lm.setTournamentPoints(lm.getTournamentPoints() + Math.round(t.getNumberOfPlayers() / tp.getTop()));
					lm = leagueDAO.updateLeagueMember(lm);
				}
			}
		}
		return t;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tournament findTournament(@PathParam("id") int id) {
		Tournament tournament = tournamentDAO.find(id);
		if (tournament != null) {
			if (!tournament.isDeleted()) {
				return tournament;
			} else if ((tournament.getLeague().getManagerId() == userSessionService.getSession().getId()) || (tournament.getLeague().getCreatorId() == userSessionService.getSession().getId())) {
				return tournament;
			} else { 
				return null; 
			}
		}
		return null;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findTournaments(
		@QueryParam("league") int league,
		@QueryParam("finished") boolean finished,
		@QueryParam("deleted") boolean deleted, 
		@QueryParam("page") int page	
	) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (league > 0) params.put("league", league);
		if (finished) params.put("finished", finished);
		if (deleted) { } else { params.put("deleted", false); }
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = tournamentDAO.count(params);
		List<Tournament> results = tournamentDAO.find(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUserTournaments(
		@PathParam("id") int id,
		@QueryParam("confirmed") boolean confirmed,
		@QueryParam("unconfirmed") boolean unconfirmed,
		@QueryParam("page") int page	
	) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (confirmed) { params.put("confirmed", true); }
		if (unconfirmed) { params.put("confirmed", false); }
		params.put("participant", id);
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = tournamentDAO.countParticipants(params);
		List<TournamentParticipant> results = tournamentDAO.findParticipants(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/participants")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findParticipants(
		@PathParam("id") int id,
		@QueryParam("confirmed") boolean confirmed,
		@QueryParam("unconfirmed") boolean unconfirmed,
		@QueryParam("page") int page	
	) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (confirmed) { params.put("confirmed", true); }
		if (unconfirmed) { params.put("confirmed", false); }
		params.put("tournament", id);
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = tournamentDAO.countParticipants(params);
		List<TournamentParticipant> results = tournamentDAO.findParticipants(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/{id}/participants")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentParticipant createParticipant(
		int userId,
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			(userId > 0) && 
			userSessionService.getSession().ok() && 
			(userId == userSessionService.getSession().getId()) &&
			userSessionService.getSession().getToken().equals(token) 
		) {
			Tournament t = tournamentDAO.find(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("confirmed", true); 
			params.put("tournament", id);
			if (
				(t != null) && 
				(t.getNumberOfPlayers() > tournamentDAO.countParticipants(params))
			) {
				TournamentParticipant participant = new TournamentParticipant();
				participant.setConfirmed(false);
				participant.setId(null);
				participant.setTournament(t);
				participant.setUser(userDAO.find(userSessionService.getSession().getId()));
				participant.setTop(t.getNumberOfPlayers());
				return tournamentDAO.createParticipant(participant);
			}
		}
		return null;
	}
	
	@POST
	@Path("/{id}/participants/confirm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentParticipant confirmParticipant(
		String participantId,
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			(participantId != null) && !participantId.trim().isEmpty() &&
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) 
		) {
			Tournament t = tournamentDAO.find(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("confirmed", true); 
			params.put("tournament", id);
			if (
				(t != null) && 
				(
					(t.getSupervisorId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getCreatorId() == userSessionService.getSession().getId())
				) && 
				(t.getNumberOfPlayers() > tournamentDAO.countParticipants(params))
			) {
				TournamentParticipant participant = tournamentDAO.findParticipant(participantId);
				if (participant != null) {
					participant.setConfirmed(true);
					participant = tournamentDAO.updateParticipant(participant);
				}
				return participant;
			}
		}
		return null;
	}
	
	@POST
	@Path("/{id}/participants/unconfirm")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentParticipant unconfirmParticipant(
		String participantId,
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			(participantId != null) && !participantId.trim().isEmpty() &&
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) 
		) {
			Tournament t = tournamentDAO.find(id);
			if (
				(t != null) && 
				(
					(t.getSupervisorId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getCreatorId() == userSessionService.getSession().getId())
				)
			) {
				TournamentParticipant participant = tournamentDAO.findParticipant(participantId);
				if (participant != null) {
					participant.setConfirmed(false);
					participant = tournamentDAO.updateParticipant(participant);
				}
				return participant;
			}
		}
		return null;
	}
	
	@POST
	@Path("/{id}/participants/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteParticipant(
		String participantId,
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		if (
			(participantId != null) && !participantId.trim().isEmpty() &&
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) 
		) {
			Tournament t = tournamentDAO.find(id);
			if (
				(t != null) && 
				(
					(t.getSupervisorId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getManagerId() == userSessionService.getSession().getId()) || 
					(t.getLeague().getCreatorId() == userSessionService.getSession().getId())
				)
			) {
				try {
					tournamentDAO.deleteParticipant(tournamentDAO.findParticipant(participantId));
				} catch (Exception e) { }
			}
		}
	}
	
	@GET
	@Path("/{id}/participants/{participantId}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkIfUserIsParticipant(
		@PathParam("id") int id, 
		@PathParam("participantId") int participantId
	) {
		if ((id > 0) && (participantId > 0)) {
			return tournamentDAO.isParticipant(id, participantId);
		} else {
			return false;
		}
	}
	
	@GET
	@Path("/{id}/participants/{participantId}/confirmed")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkIfParticipantIsConfirmed(
		@PathParam("id") int id, 
		@PathParam("participantId") int participantId
	) {
		if ((id > 0) && (participantId > 0)) {
			return tournamentDAO.isConfirmedParticipant(id, participantId);
		} else {
			return false;
		}
	}
	
	@GET
	@Path("/{id}/matches")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMatches(
		@PathParam("id") int id,
		@QueryParam("player") int player,
		@QueryParam("stage") int stage,
		@QueryParam("finished") boolean finished,
		@QueryParam("approved") boolean approved, 
		@QueryParam("deleted") boolean deleted,
		@QueryParam("page") int page
	) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (id > 0) params.put("tournament", id);
		if (player > 0) params.put("firstPlayer", player);
		if (stage > 0) params.put("stage", stage);
		if (deleted) { } else { params.put("deleted", false); }
		if (finished) params.put("finished", true);
		if (approved) params.put("approved", true);
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = tournamentDAO.countMatches(params);
		List<TournamentMatch> results = tournamentDAO.findMatches(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/matches/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch findMatch(
			@PathParam("id") int id
		) {
		return tournamentDAO.findMatch(id);
	}
	
	@POST
	@Path("/matches/{id}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch finishMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		TournamentMatch tm = tournamentDAO.findMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tm.getFirstPlayerId() == userSessionService.getSession().getId()) || 
				(tm.getSecondPlayerId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
			)
		) {
			if (!tm.isApproved() && !tm.isDeleted()) {
				tm.setFinished(true);
				if (
					(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
					(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
					(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
				) {
					//tm.setApproved(true);
				}
				tm = tournamentDAO.updateMatch(tm);
			}
		}
		return tm;
	}
	
	@POST
	@Path("/matches/{id}/approve")
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch approveMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		TournamentMatch tm = tournamentDAO.findMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
			)
		) {
			if (tm.isFinished() && !tm.isDeleted()) {
				tm.setApproved(true);
				tm = tournamentDAO.updateMatch(tm);
				if (tm != null) {
					TournamentParticipant tp = tournamentDAO.findParticipant(tm.getTournamentId() + "-" + tm.getWinner().getId());
					if (tm.getStage() > 1) {
						tp.setTop(tm.getStage() / 2);
						tp = tournamentDAO.updateParticipant(tp);
					}
				}
			}
		}
		return tm;
	}
	
	@POST
	@Path("/matches/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch deleteMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		TournamentMatch tm = tournamentDAO.findMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
			)
		) {
			if (!tm.isDeleted() && !tm.isApproved()) {
				tm.setFinished(false);
				tm.setApproved(false);
				tm.setDeleted(true);
				tm = tournamentDAO.updateMatch(tm);
			}
		}
		return tm;
	}
	
	@POST
	@Path("/matches/{id}/restore")
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch restoreMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		TournamentMatch tm = tournamentDAO.findMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
			)
		) {
			tm.setFinished(false);
			tm.setApproved(false);
			tm.setDeleted(false);
			tm = tournamentDAO.updateMatch(tm);
			if (tm != null) {
				TournamentParticipant tp = tournamentDAO.findParticipant(tm.getTournamentId() + "-" + tm.getWinner().getId());
				tp.setTop(tm.getStage());
				tp = tournamentDAO.updateParticipant(tp);
			}
		}
		return tm;
	}
	
	@PUT
	@Path("/matches/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch updateMatch(
		TournamentMatch match, 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		TournamentMatch tm = tournamentDAO.findMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId()) ||
				(tm.getFirstPlayerId() == userSessionService.getSession().getId()) ||
				(tm.getSecondPlayerId() == userSessionService.getSession().getId())
			) && 
			(tm.getId() == match.getId()) &&
			!tm.isApproved()
		) {
			if (
				(tm.getTournament().getSupervisorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tm.getTournament().getLeague().getManagerId() == userSessionService.getSession().getId())
			) {
				tm.setFirstPlayer(userDAO.find(match.getFirstPlayerId()));
				tm.setSecondPlayer(userDAO.find(match.getSecondPlayerId()));
				if (
					(match.getStartDateDay() > 0) && 
					(match.getStartDateMonth() > 0) && 
					(match.getStartDateYear() > 0)
				) {
					Calendar start = Calendar.getInstance();
					start.set(Calendar.DATE, match.getStartDateDay());
					start.set(Calendar.MONTH, match.getStartDateMonth() - 1);
					start.set(Calendar.YEAR, match.getStartDateYear());
					tm.setStartDate(start.getTime());
				}
				if (
					(match.getEndDateDay() > 0) && 
					(match.getEndDateMonth() > 0) && 
					(match.getEndDateYear() > 0)
				) {
					Calendar end = Calendar.getInstance();
					end.set(Calendar.DATE, match.getEndDateDay());
					end.set(Calendar.MONTH, match.getEndDateMonth() - 1);
					end.set(Calendar.YEAR, match.getEndDateYear());
					tm.setStartDate(end.getTime());
				}
				tm.setSurfaceType(match.getSurfaceType());
				//location
				GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(match.getCountry() + "." + match.getState());
				GeonamesCity gc = locationDAO.findGeonamesCity(match.getCityGeonameId());
				Location loc;
				String fullAddress = "";
				if ((match.getAddress() != null) && !match.getAddress().trim().isEmpty())
					fullAddress += (match.getAddress() + ", ");
				if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
					fullAddress += (gc.getAsciiname() + ", ");
				if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
					fullAddress += (ga.getAsciiname() + ", ");
				if ((match.getCountry() != null) && !match.getCountry().trim().isEmpty())
					fullAddress += match.getCountry();	
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("fullAddress", fullAddress);
					loc = locationDAO.find(params).get(0);
				} catch (Exception e) { loc = null; }
				if ((loc == null) && (fullAddress != "") && !fullAddress.trim().isEmpty()) { 				
					try {
						GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
						GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await();
						if ((results != null) && (results.length > 0)) {
							try {
								loc = new Location();
								loc.setCountry(match.getCountry());
								loc.setState((ga != null) ? ga.getAsciiname() : "");
								loc.setCity((gc != null) ? gc.getAsciiname() : "");
								if (ga != null) loc.setGeonamesAdmin(ga);
								if (gc != null) loc.setGeonamesCity(gc);
								loc.setAddress(match.getAddress());
								loc.setFullAddress(fullAddress);
								loc.setFormattedAddress(results[0].formattedAddress);
								loc.setLatitude((float) results[0].geometry.location.lat);
								loc.setLongitude((float) results[0].geometry.location.lng);
								loc = locationDAO.create(loc);
								System.out.println("test (id): " + loc.getId());
							} catch (Throwable t) { 
								ejbContext.setRollbackOnly(); 
								loc = null; 
							}
						}
					} catch (Exception e) { System.out.println("Geocoding error: " + e.getMessage()); }
				}
				if (loc != null) tm.setLocation(loc);
			}
			tm.setFirstPlayerScore(match.getFirstPlayerScore());
			tm.setSecondPlayerScore(match.getSecondPlayerScore());
			tm.setDetailedScore(match.getDetailedScore());
			tm.setMatchReview(match.getMatchReview());
			tm.setStage(match.getStage());
			tm = tournamentDAO.updateMatch(tm);
		}
		return tm;
	}
	
	@POST
	@Path("/{id}/matches")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TournamentMatch createMatch(
			TournamentMatch match, 
			@PathParam("id") int id,
			@QueryParam("token") String token
		) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(tournamentDAO.find(id) != null) &&
			(
				(tournamentDAO.find(id).getSupervisorId() == userSessionService.getSession().getId()) || 
				(tournamentDAO.find(id).getLeague().getCreatorId() == userSessionService.getSession().getId()) || 
				(tournamentDAO.find(id).getLeague().getManagerId() == userSessionService.getSession().getId())
			) 
		) {			
			TournamentMatch tm = new TournamentMatch();
			tm.setTournament(tournamentDAO.find(id));
			if (match.getStage() > 0) { 
				tm.setStage(match.getStage()); 
			} else {
				tm.setStage(tournamentDAO.find(id).getNumberOfPlayers());
			}
			tm.setFirstPlayer(userDAO.find(match.getFirstPlayerId()));
			tm.setSecondPlayer(userDAO.find(match.getSecondPlayerId()));
			if (
				(match.getStartDateDay() > 0) && 
				(match.getStartDateMonth() > 0) && 
				(match.getStartDateYear() > 0)
			) {
				Calendar start = Calendar.getInstance();
				start.set(Calendar.DATE, match.getStartDateDay());
				start.set(Calendar.MONTH, match.getStartDateMonth() - 1);
				start.set(Calendar.YEAR, match.getStartDateYear());
				tm.setStartDate(start.getTime());
				tm.setEndDate(start.getTime());
			}
			tm.setDescription(match.getDescription());
			tm.setSurfaceType(match.getSurfaceType());
			//location
			GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(match.getCountry() + "." + match.getState());
			GeonamesCity gc = locationDAO.findGeonamesCity(match.getCityGeonameId());
			Location loc;
			String fullAddress = "";
			if ((match.getAddress() != null) && !match.getAddress().trim().isEmpty())
				fullAddress += (match.getAddress() + ", ");
			if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
				fullAddress += (gc.getAsciiname() + ", ");
			if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
				fullAddress += (ga.getAsciiname() + ", ");
			if ((match.getCountry() != null) && !match.getCountry().trim().isEmpty())
				fullAddress += match.getCountry();	
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("fullAddress", fullAddress);
				loc = locationDAO.find(params).get(0);
			} catch (Exception e) { loc = null; }
			if ((loc == null) && (fullAddress != "") && !fullAddress.trim().isEmpty()) { 				
				try {
					GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
					GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await();
					if ((results != null) && (results.length > 0)) {
						try {
							loc = new Location();
							loc.setCountry(match.getCountry());
							loc.setState((ga != null) ? ga.getAsciiname() : "");
							loc.setCity((gc != null) ? gc.getAsciiname() : "");
							if (ga != null) loc.setGeonamesAdmin(ga);
							if (gc != null) loc.setGeonamesCity(gc);
							loc.setAddress(match.getAddress());
							loc.setFullAddress(fullAddress);
							loc.setFormattedAddress(results[0].formattedAddress);
							loc.setLatitude((float) results[0].geometry.location.lat);
							loc.setLongitude((float) results[0].geometry.location.lng);
							loc = locationDAO.create(loc);
							System.out.println("test (id): " + loc.getId());
						} catch (Throwable t) { 
							ejbContext.setRollbackOnly(); 
							loc = null; 
						}
					}
				} catch (Exception e) { System.out.println("Geocoding error: " + e.getMessage()); }
			}
			if (loc != null) tm.setLocation(loc);
			return tournamentDAO.createMatch(tm);
		} else { return null; }		
	}
	
	@GET
	@Path("/{id}/participants/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> searchParticipantsById(
		@PathParam("id") int id, 
		@QueryParam("userId") int userId,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tournament", id);
		params.put("confirmed", true);
		params.put("userId", userId);
		
		List<User> results = tournamentDAO.searchParticipantsById(params, startPosition, maxResults);

		return results;
	}

}
