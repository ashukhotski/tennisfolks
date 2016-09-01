package com.tennisfolks.service;

import java.util.*;

import javax.ejb.EJB;
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
import com.tennisfolks.dao.ImageDAO;
import com.tennisfolks.dao.LeagueBlogEntryDAO;
import com.tennisfolks.dao.LeagueDAO;
import com.tennisfolks.dao.LocationDAO;
import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.entities.League;
import com.tennisfolks.entities.LeagueMember;
import com.tennisfolks.entities.User;
import com.tennisfolks.entities.LeagueBlogEntry;
import com.tennisfolks.entities.LeagueBlogEntryComment;
import com.tennisfolks.util.UserSessionService;

@Path("/leagues") @Stateless
public class LeagueService {
	
	@EJB
	LeagueDAO leagueDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	LocationDAO locationDAO;
	
	@EJB
	ImageDAO imageDAO;
	
	@EJB
	LeagueBlogEntryDAO blogDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll(
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleted", false);
		int size = leagueDAO.countLeagues(params);
		List<League> results = leagueDAO.find(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchForLeagues(
		@QueryParam("country") String country, 
		@QueryParam("stateId") int stateId, 
		@QueryParam("cityId") int cityId, 
		@QueryParam("open") boolean open,
		@QueryParam("creator") int creator,
		@QueryParam("manager") int manager, 
		@QueryParam("creatorOrManager") int userId,
		@QueryParam("nearby") boolean nearby,
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		if (country != null) params.put("country", country);
		if (stateId > 0) params.put("stateId", stateId);
		if (cityId > 0) params.put("cityId", stateId);
		if (userId > 0) params.put("creatorOrManager", userId);
		if (manager > 0) params.put("manager", manager);
		if (creator > 0) params.put("creator", creator);
		params.put("deleted", false);
		
		if (nearby && userSessionService.getSession().ok()) {
			User u = userDAO.find(userSessionService.getSession().getId());
			if (u != null) {
				//6371;
				float radius = new Float(0.045);
				float minlat = u.getLatitude() - radius;
				float minlon = u.getLongitude() - radius;
				float maxlat = u.getLatitude() + radius;
				float maxlon = u.getLongitude() + radius;

				String coords = minlat + "," + minlon + "," + maxlat + "," + maxlon;
				params.put("nearby", coords);
			}
		}
		
		int size = leagueDAO.countLeagues(params);
		List<League> results = leagueDAO.find(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/member/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMemberLeagues(
		@PathParam("id") int id, 
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", id);
		params.put("accepted", true);
		params.put("rejected", false);
		params.put("deleted", false);
		
		int size = leagueDAO.countMemberLeagues(params);
		List<LeagueMember> results = leagueDAO.getMemberLeagues(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/managed/by/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getManagedLeagues(
		@PathParam("id") int id, 
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("creatorOrManager", id);
		
		int size = leagueDAO.countLeagues(params);
		List<League> results = leagueDAO.find(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public League createLeague(League l, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {
			User currentUser = userDAO.find(userSessionService.getSession().getId());
			GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(l.getCountry() + "." + l.getState());
			
			Map<String, Object> cityParams = new HashMap<String, Object>();
			cityParams.put("countryCode", l.getCountry());
			cityParams.put("adminCode", l.getState());
			GeonamesCityAlternate gca = locationDAO.findGeonamesCityAlternateByName(l.getCity(), cityParams);
			GeonamesCity gc = gca.getGeonamesCity();
			l.setCityGeonameId(gc.getGeonameId());
			//GeonamesCity gc = locationDAO.findGeonamesCity(l.getCityGeonameId());
			
			League league = new League();
			league.setName(l.getName());
			league.setDescription(l.getDescription());
			league.setOpen(l.isOpen());
			league.setCreator(currentUser);
			if (l.getManagerId() > 0) { 
				league.setManager(userDAO.find(l.getManagerId()));
			} else {
				league.setManager(currentUser);
			}
			league.setCountry(l.getCountry());
			if (ga != null) league.setGeonamesAdmin(ga);
			if (gc != null) league.setGeonamesCity(gc);
			
			return leagueDAO.create(league);
		} else { return null; }
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public League getLeagueById(@PathParam("id") int id) {
		return leagueDAO.find(id);
	}
	
	@GET
	@Path("/{id}/members")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeagueMembers(
		@PathParam("id") int id,  
		@QueryParam("accepted") boolean accepted, 
		@QueryParam("rejected") boolean rejected, 
		@QueryParam("deleted") boolean deleted, 
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		if (!String.valueOf(accepted).trim().isEmpty()) { params.put("accepted", accepted); } else { params.put("accepted", true); }
		if (!String.valueOf(rejected).trim().isEmpty()) { params.put("rejected", rejected); } else { params.put("rejected", false); }
		if (!String.valueOf(deleted).trim().isEmpty()) { params.put("deleted", deleted); } else { params.put("deleted", false); }
		int size = leagueDAO.countMembers(params);
		List<LeagueMember> results = leagueDAO.getMembers(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	/*
	@GET
	@Path("/{id}/members/accepted/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> searchAcceptedMembersByName(
		@PathParam("id") int id, 
		@QueryParam("name") String name,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("accepted", true);
		params.put("rejected", false);
		params.put("deleted", false);
		params.put("name", name);
		
		List<User> results = leagueDAO.searchAcceptedMembersByName(params, startPosition, maxResults);

		return results;
	}
	*/
	
	@GET
	@Path("/{id}/members/accepted/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> searchAcceptedMembersById(
		@PathParam("id") int id, 
		@QueryParam("userId") int userId,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("accepted", true);
		params.put("rejected", false);
		params.put("deleted", false);
		params.put("userId", userId);
		
		List<User> results = leagueDAO.searchAcceptedMembersById(params, startPosition, maxResults);

		return results;
	}
	
	@GET
	@Path("/{id}/members/accepted")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAcceptedLeagueMembers(
		@PathParam("id") int id,   
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("accepted", true);
		params.put("rejected", false);
		params.put("deleted", false);
		
		int size = leagueDAO.countMembers(params);
		List<LeagueMember> results = leagueDAO.getMembers(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/members/rejected")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvitedLeagueMembers(
		@PathParam("id") int id,  
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("accepted", false);
		params.put("rejected", true);
		params.put("deleted", false);
		
		int size = leagueDAO.countMembers(params);
		List<LeagueMember> results = leagueDAO.getMembers(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/members/requests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeagueMembersRequests(
		@PathParam("id") int id,  
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("accepted", false);
		params.put("rejected", false);
		params.put("deleted", false);
		
		int size = leagueDAO.countMembers(params);
		List<LeagueMember> results = leagueDAO.getMembers(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/members/deleted")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeletedLeagueMembers(
		@PathParam("id") int id,  
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leagueId", id);
		params.put("deleted", true);
		
		int size = leagueDAO.countMembers(params);
		List<LeagueMember> results = leagueDAO.getMembers(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/membership/accepted")
	@Produces(MediaType.APPLICATION_JSON) 
	public boolean isAcceptedMember(@PathParam("id") int leagueId, @QueryParam("userId") int userId) {
		return leagueDAO.isMember(leagueId, userId);
	}
	
	@GET
	@Path("/{id}/membership/requestor")
	@Produces(MediaType.APPLICATION_JSON) 
	public boolean isRequestor(@PathParam("id") int leagueId, @QueryParam("userId") int userId) {
		return leagueDAO.isRequestor(leagueId, userId);
	}
	
	@GET
	@Path("/{id}/membership/rejected")
	@Produces(MediaType.APPLICATION_JSON) 
	public boolean isRejected(@PathParam("id") int leagueId, @QueryParam("userId") int userId) {
		return leagueDAO.isRejected(leagueId, userId);
	}
	
	@GET
	@Path("/{id}/membership/deleted")
	@Produces(MediaType.APPLICATION_JSON) 
	public boolean isInvitee(@PathParam("id") int leagueId, @QueryParam("userId") int userId) {
		return leagueDAO.isDeleted(leagueId, userId);
	}
	
	@POST
	@Path("/{id}/join")
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueMember joinTheLeague(@PathParam("id") int id, @QueryParam("token") String token) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(league != null) && 
			!leagueDAO.isMember(id, userSessionService.getSession().getId()) &&
			!leagueDAO.isRequestor(id, userSessionService.getSession().getId())
		) {
			LeagueMember lm = leagueDAO.findLeagueMember(id + "-" + userSessionService.getSession().getId());
			if (lm != null) { 
				return leagueDAO.updateLeagueMember(lm);
			} else {
				lm = new LeagueMember();
				lm.setId(null);
				lm.setLeague(league);
				lm.setUser(userDAO.find(userSessionService.getSession().getId()));
				lm.setRejected(false);
				lm.setDeleted(false);
				if (
					(league.getCreatorId() == userSessionService.getSession().getId()) || 
					(league.getManagerId() == userSessionService.getSession().getId()) || 
					league.isOpen()
				) { lm.setAccepted(true); } else { lm.setAccepted(false); }
				return leagueDAO.createLeagueMember(lm);
			}
		}
		return null;
	}
	
	@POST
	@Path("/{id}/management/accept")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueMember acceptMembershipRequest(int userId, @PathParam("id") int id, @QueryParam("token") String token) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(league != null) && 
			(
				(league.getCreatorId() == userSessionService.getSession().getId()) ||
				(league.getManagerId() == userSessionService.getSession().getId())
			) &&
			(
				leagueDAO.isRequestor(id, userId) || 
				leagueDAO.isRejected(id, userId)
			)
		) {
			LeagueMember lm = leagueDAO.findLeagueMember(id + "-" + userId);
			if ((lm != null) && !lm.isAccepted() && !lm.isDeleted()) {
				lm.setAccepted(true);
				lm.setRejected(false);
				lm.setDeleted(false);
				return leagueDAO.updateLeagueMember(lm);
			} else { return lm; }
		}
		return null;
	}
	
	@POST
	@Path("/{id}/management/reject")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueMember rejectMembershipRequest(int userId, @PathParam("id") int id, @QueryParam("token") String token) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(league != null) && 
			(
				(league.getCreatorId() == userSessionService.getSession().getId()) ||
				(league.getManagerId() == userSessionService.getSession().getId())
			) &&
			leagueDAO.isRequestor(id, userId)
		) {
			LeagueMember lm = leagueDAO.findLeagueMember(id + "-" + userId);
			if ((lm != null) && !lm.isAccepted() && !lm.isDeleted()) {
				lm.setAccepted(false);
				lm.setRejected(true);
				lm.setDeleted(false);
				return leagueDAO.updateLeagueMember(lm);
			} else { return lm; }
		}
		return null;
	}
	
	@POST
	@Path("/{id}/management/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueMember deleteLeagueMember(int userId, @PathParam("id") int id, @QueryParam("token") String token) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(league != null) && 
			(
				(league.getCreatorId() == userSessionService.getSession().getId()) ||
				(league.getManagerId() == userSessionService.getSession().getId())
			) &&
			leagueDAO.isMember(id, userId)
		) {
			LeagueMember lm = leagueDAO.findLeagueMember(id + "-" + userId);
			if ((lm != null) && !lm.isDeleted()) {
				lm.setAccepted(true);
				lm.setRejected(false);
				lm.setDeleted(true);
				return leagueDAO.updateLeagueMember(lm);
			} else { return lm; }
		}
		return null;
	}
	
	@POST
	@Path("/{id}/management/restore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueMember restoreLeagueMember(int userId, @PathParam("id") int id, @QueryParam("token") String token) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) &&
			(league != null) && 
			(
				(league.getCreatorId() == userSessionService.getSession().getId()) ||
				(league.getManagerId() == userSessionService.getSession().getId())
			) &&
			leagueDAO.isDeleted(id, userId)
		) {
			LeagueMember lm = leagueDAO.findLeagueMember(id + "-" + userId);
			if ((lm != null) && lm.isDeleted()) {
				lm.setAccepted(true);
				lm.setRejected(false);
				lm.setDeleted(false);
				return leagueDAO.updateLeagueMember(lm);
			} else { return lm; }
		}
		return null;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public League updateLeague(
		League l, 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		League league = leagueDAO.find(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(l.getId() == league.getId())
		) {
			if (
				(league.getCreatorId() == userSessionService.getSession().getId()) || 
				(league.getManagerId() == userSessionService.getSession().getId())
			) {
				GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(l.getCountry() + "." + l.getState());
				Map<String, Object> cityParams = new HashMap<String, Object>();
				cityParams.put("countryCode", l.getCountry());
				cityParams.put("adminCode", l.getState());
				GeonamesCityAlternate gca = locationDAO.findGeonamesCityAlternateByName(l.getCity(), cityParams);
				GeonamesCity gc = gca.getGeonamesCity();
				l.setCityGeonameId(gc.getGeonameId());
				//GeonamesCity gc = locationDAO.findGeonamesCity(l.getCityGeonameId());
				
				if (league.getCreatorId() == userSessionService.getSession().getId()) {
					System.out.println("manager: " + l.getManagerId());
					if ((league.getManagerId() != l.getManagerId()) && (userDAO.find(l.getManagerId()) != null)) league.setManager(userDAO.find(l.getManagerId()));
				}
				
				league.setCountry(l.getCountry());
				league.setGeonamesAdmin(ga);
				league.setGeonamesCity(gc);
				league.setName(l.getName());
				league.setDescription(l.getDescription());
				league.setOpen(l.isOpen());
				
				league = leagueDAO.update(league);
			}
		} 
		return league;	
	}
	
	@POST
	@Path("/{id}/logo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public League updateLogo(int imageId, @PathParam("id") int id, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {
			League league = leagueDAO.find(id);
			if (
				(league != null) && 
				(
					(league.getCreatorId() == userSessionService.getSession().getId()) || 
					(league.getManagerId() == userSessionService.getSession().getId())
				) && 
				(imageDAO.find(imageId) != null)
			) {
				league.setLogo(imageDAO.find(imageId));
				return leagueDAO.update(league);
			} else { return league; }
		} else { return leagueDAO.find(id); }
	}
	
	@POST
	@Path("/{id}/cover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public League updateCover(int imageId, @PathParam("id") int id, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {
			League league = leagueDAO.find(id);
			if (
				(league != null) && 
				(
					(league.getCreatorId() == userSessionService.getSession().getId()) || 
					(league.getManagerId() == userSessionService.getSession().getId())
				) && 
				(imageDAO.find(imageId) != null)
			) {
				league.setCoverPhoto(imageDAO.find(imageId));
				return leagueDAO.update(league);
			} else { return league; }
		} else { return leagueDAO.find(id); }
	}
	
	@GET
	@Path("/{id}/blog")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeagueBlogEntries(
		@PathParam("id") int id,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("league", id);
		params.put("deleted", false);
		if (
			!userSessionService.getSession().ok() || 
			(leagueDAO.find(id).getCreatorId() != userSessionService.getSession().getId()) || 
			(leagueDAO.find(id).getManagerId() != userSessionService.getSession().getId())
		) {
			params.put("published", true);
		}
		
		int size = blogDAO.count(params);
		List<LeagueBlogEntry> results = blogDAO.find(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/{id}/blog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueBlogEntry createLeagueBlogEntry(
		LeagueBlogEntry post,
		@PathParam("id") int id,
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(
				(leagueDAO.find(id).getCreatorId() == userSessionService.getSession().getId()) || 
				(leagueDAO.find(id).getManagerId() == userSessionService.getSession().getId())
			) && 
			(post.getText() != null) && !post.getText().trim().isEmpty() && 
			(post.getHeader() != null) && !post.getHeader().trim().isEmpty()
		) {
			LeagueBlogEntry entry = new LeagueBlogEntry();
			entry.setAuthor(userDAO.find(userSessionService.getSession().getId()));
			entry.setLeague(leagueDAO.find(id));
			entry.setDeleted(false);
			entry.setPublished(true);
			entry.setText(post.getText());
			entry.setHeader(post.getHeader());
			return blogDAO.create(entry);
		} else { return null; }
	}
	
	@GET
	@Path("/blog/{entryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueBlogEntry getLeagueBlogEntry(
		@PathParam("entryId") int id, 
		@QueryParam("token") String token
	) {
		LeagueBlogEntry entry = blogDAO.find(id);
		if (
			(entry != null) && 
			entry.isPublished() && 
			!entry.isDeleted()
		) {
			return entry;
		} else if (
			(entry != null) && 
			(!entry.isPublished() || entry.isDeleted()) &&
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(
				(leagueDAO.find(id).getCreatorId() == userSessionService.getSession().getId()) || 
				(leagueDAO.find(id).getManagerId() == userSessionService.getSession().getId())
			)
		) {
			return entry;
		} else { return null; }
	}
	
	@GET
	@Path("/blog/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeagueBlogEntryComments(
		@PathParam("id") int id,
		@QueryParam("page") int page
	) {
		int maxResults = 10;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entry", id);
		params.put("deleted", false);
		
		int size = blogDAO.countComments(params);
		List<LeagueBlogEntryComment> results = blogDAO.findComments(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/blog/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueBlogEntryComment createLeagueBlogEntryComment(
		String text,
		@PathParam("id") int id,
		@QueryParam("token") String token
	) {
		if (
			blogDAO.find(id).isPublished() && 
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(text != null) && !text.trim().isEmpty()
		) {
			LeagueBlogEntryComment comment = new LeagueBlogEntryComment();
			comment.setAuthor(userDAO.find(userSessionService.getSession().getId()));
			comment.setEntry(blogDAO.find(id));
			comment.setDeleted(false);
			comment.setText(text);
			return blogDAO.createComment(comment);
		} else { return null; }
	}

}
