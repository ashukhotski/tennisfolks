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
import com.tennisfolks.dao.ChallengeDAO;
import com.tennisfolks.dao.LocationDAO;
import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.entities.ChallengeMatch;
import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.entities.Location;
import com.tennisfolks.entities.OpenChallenge;
import com.tennisfolks.util.UserSessionService;

@Path("/challenges") @Stateless
public class ChallengeService {
	
	@EJB
	ChallengeDAO challengeDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	LocationDAO locationDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@Resource
	EJBContext ejbContext;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChallengeMatches(
			@QueryParam("firstPlayer") int firstPlayer,
			@QueryParam("secondPlayer") int secondPlayer,
			@QueryParam("status") int status,
			@QueryParam("accepted") boolean accepted,
			@QueryParam("rejected") boolean rejected,
			@QueryParam("deleted") boolean deleted,
			@QueryParam("country") String country, 
			@QueryParam("page") int page
		) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (firstPlayer > 0) params.put("firstPlayer", firstPlayer);
		if (secondPlayer > 0) params.put("secondPlayer", firstPlayer);
		params.put("deleted", false);
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = challengeDAO.countChallengeMatches(params);
		List<ChallengeMatch> results = challengeDAO.findChallengeMatches(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch getChallengeMatch(
			@PathParam("id") int id
		) {
		return challengeDAO.findChallengeMatch(id);
	}
	
	@POST
	@Path("/{id}/finish")
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch finishChallengeMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		ChallengeMatch ch = challengeDAO.findChallengeMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(ch.getFirstPlayerId() == userSessionService.getSession().getId())
		) {
			if (ch.isAccepted() && !ch.isRejected() && !ch.isDeleted()) {
				ch.setFinished(true);
				ch = challengeDAO.updateChallengeMatch(ch);
			}
		}
		return ch;
	}
	
	@POST
	@Path("/{id}/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch acceptChallengeMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		ChallengeMatch ch = challengeDAO.findChallengeMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(ch.getSecondPlayerId() == userSessionService.getSession().getId())
		) {
			ch.setRejected(false);
			ch.setAccepted(true);
			ch.setFinished(false);
			ch = challengeDAO.updateChallengeMatch(ch);
		}
		return ch;
	}
	
	@POST
	@Path("/{id}/reject")
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch rejectChallengeMatch(
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		ChallengeMatch ch = challengeDAO.findChallengeMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(ch.getSecondPlayerId() == userSessionService.getSession().getId())
		) {
			ch.setRejected(true);
			ch.setAccepted(false);
			ch.setFinished(false);
			ch = challengeDAO.updateChallengeMatch(ch);
		}
		return ch;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch updateChallengeMatch(
		ChallengeMatch challenge, 
		@PathParam("id") int id, 
		@QueryParam("token") String token
	) {
		ChallengeMatch ch = challengeDAO.findChallengeMatch(id);
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(
				(ch.getFirstPlayerId() == userSessionService.getSession().getId()) ||
				(ch.getSecondPlayerId() == userSessionService.getSession().getId())
			) && 
			(ch.getId() == challenge.getId())
		) {
			ch.setFirstPlayerScore(challenge.getFirstPlayerScore());
			ch.setSecondPlayerScore(challenge.getSecondPlayerScore());
			ch.setDetailedScore(challenge.getDetailedScore());
			ch.setMatchReview(challenge.getMatchReview());
			if (
				(challenge.getEndDateDay() > 0) && 
				(challenge.getEndDateMonth() > 0) && 
				(challenge.getEndDateYear() > 0)
			) {
				Calendar end = Calendar.getInstance();
				end.set(Calendar.DATE, challenge.getEndDateDay());
				end.set(Calendar.MONTH, challenge.getEndDateMonth() - 1);
				end.set(Calendar.YEAR, challenge.getEndDateYear());
				ch.setStartDate(end.getTime());
			}
			ch.setSurfaceType(challenge.getSurfaceType());
			ch = challengeDAO.updateChallengeMatch(ch);
		}
		return ch;
	}
	
	@POST
	@Path("/newmatch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ChallengeMatch createChallengeMatch(
			ChallengeMatch challenge, 
			@QueryParam("token") String token
		) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {			
			ChallengeMatch challengeMatch = new ChallengeMatch();
			challengeMatch.setFirstPlayer(userDAO.find(userSessionService.getSession().getId()));
			challengeMatch.setSecondPlayer(userDAO.find(challenge.getSecondPlayerId()));
			if (
				(challenge.getStartDateDay() > 0) && 
				(challenge.getStartDateMonth() > 0) && 
				(challenge.getStartDateYear() > 0)
			) {
				Calendar start = Calendar.getInstance();
				start.set(Calendar.DATE, challenge.getStartDateDay());
				start.set(Calendar.MONTH, challenge.getStartDateMonth() - 1);
				start.set(Calendar.YEAR, challenge.getStartDateYear());
				challengeMatch.setStartDate(start.getTime());
				challengeMatch.setEndDate(start.getTime());
			}
			challengeMatch.setDescription(challenge.getDescription());
			challengeMatch.setSurfaceType(challenge.getSurfaceType());
			//location
			GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(challenge.getCountry() + "." + challenge.getState());
			GeonamesCity gc = locationDAO.findGeonamesCity(challenge.getCityGeonameId());
			Location loc;
			String fullAddress = "";
			if ((challenge.getAddress() != null) && !challenge.getAddress().trim().isEmpty())
				fullAddress += (challenge.getAddress() + ", ");
			if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
				fullAddress += (gc.getAsciiname() + ", ");
			if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
				fullAddress += (ga.getAsciiname() + ", ");
			if ((challenge.getCountry() != null) && !challenge.getCountry().trim().isEmpty())
				fullAddress += challenge.getCountry();	
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
							loc.setCountry(challenge.getCountry());
							loc.setState((ga != null) ? ga.getAsciiname() : "");
							loc.setCity((gc != null) ? gc.getAsciiname() : "");
							if (ga != null) loc.setGeonamesAdmin(ga);
							if (gc != null) loc.setGeonamesCity(gc);
							loc.setAddress(challenge.getAddress());
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
			if (loc != null) challengeMatch.setLocation(loc);
			return challengeDAO.createChallengeMatch(challengeMatch);
		} else { return null; }		
	}
	
	@POST
	@Path("/open/{id}/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public OpenChallenge acceptOpenChallenge(
			@PathParam("id") int id, 
			@QueryParam("token") String token
		) {
			OpenChallenge ch = challengeDAO.findOpenChallenge(id);
			if (
				userSessionService.getSession().ok() &&
				userSessionService.getSession().getToken().equals(token) && 
				(ch.getFirstPlayerId() != userSessionService.getSession().getId())
			) {
				ch.setAccepted(true);
				
				if (ch != null) {
					ChallengeMatch challengeMatch = new ChallengeMatch();
					//??
					challengeMatch.setSecondPlayer(userDAO.find(userSessionService.getSession().getId()));
					challengeMatch.setFirstPlayer(userDAO.find(ch.getFirstPlayerId()));
					challengeMatch.setAccepted(true);
					
					challengeMatch.setStartDate(ch.getStartDate());
					challengeMatch.setDescription(ch.getDescription());
					challengeMatch.setLocation(ch.getLocation());
					challengeMatch = challengeDAO.createChallengeMatch(challengeMatch);
					
					if ((challengeMatch != null) && (challengeMatch.getId() > 0)) { ch.setMatchId(challengeMatch.getId()); }
				}
				ch = challengeDAO.updateOpenChallenge(ch);
			}
			return ch;
		}
	
	@POST
	@Path("/open")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OpenChallenge createOpenChallenge(OpenChallenge challenge, @QueryParam("token") String token) {
		if (userSessionService.getSession().ok() && userSessionService.getSession().getToken().equals(token)) {
			OpenChallenge openChallenge = new OpenChallenge();
			openChallenge.setFirstPlayer(userDAO.find(userSessionService.getSession().getId()));
			if (
					(challenge.getStartDateDay() > 0) && 
					(challenge.getStartDateMonth() > 0) && 
					(challenge.getStartDateYear() > 0)
				) {
					Calendar start = Calendar.getInstance();
					start.set(Calendar.DATE, challenge.getStartDateDay());
					start.set(Calendar.MONTH, challenge.getStartDateMonth() - 1);
					start.set(Calendar.YEAR, challenge.getStartDateYear());
					openChallenge.setStartDate(start.getTime());
				}
				openChallenge.setDescription(challenge.getDescription());
				openChallenge.setSurfaceType(challenge.getSurfaceType());
				//location
				GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(challenge.getCountry() + "." + challenge.getState());
				Map<String, Object> cityParams = new HashMap<String, Object>();
				cityParams.put("countryCode", challenge.getCountry());
				cityParams.put("adminCode", challenge.getState());
				GeonamesCityAlternate gca = locationDAO.findGeonamesCityAlternateByName(challenge.getCity(), cityParams);
				GeonamesCity gc = gca.getGeonamesCity();
				challenge.setCityGeonameId(gc.getGeonameId());
				Location loc;
				String fullAddress = "";
				if ((challenge.getAddress() != null) && !challenge.getAddress().trim().isEmpty())
					fullAddress += (challenge.getAddress() + ", ");
				if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
					fullAddress += (gc.getAsciiname() + ", ");
				if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
					fullAddress += (ga.getAsciiname() + ", ");
				if ((challenge.getCountry() != null) && !challenge.getCountry().trim().isEmpty())
					fullAddress += challenge.getCountry();	
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("fullAddress", fullAddress);
					loc = locationDAO.find(params).get(0);
				} catch (Exception e) { loc = null; }
				if ((loc == null) && (fullAddress != "") && (challenge.getAddress() != null) && !challenge.getAddress().trim().isEmpty()) { 				
					try {
						GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
						GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await();
						if ((results != null) && (results.length > 0)) {
							try {
								loc = new Location();
								loc.setCountry(challenge.getCountry());
								loc.setState((ga != null) ? ga.getAsciiname() : "");
								loc.setCity((gc != null) ? gc.getAsciiname() : "");
								if (ga != null) loc.setGeonamesAdmin(ga);
								if (gc != null) loc.setGeonamesCity(gc);
								loc.setAddress(challenge.getAddress());
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
				if (loc != null) openChallenge.setLocation(loc);
				return challengeDAO.createOpenChallenge(openChallenge);
		} else { return null; }
	}
	
	@PUT
	@Path("/open/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OpenChallenge updateOpenChallenge(OpenChallenge challenge, @PathParam("id") int id, @QueryParam("token") String token) {
		OpenChallenge openChallenge = challengeDAO.findOpenChallenge(id);
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(openChallenge.getId() == id) && 
			(openChallenge.getFirstPlayer().getId() == userSessionService.getSession().getId())
		) {
			
			if (
					(challenge.getStartDateDay() > 0) && 
					(challenge.getStartDateMonth() > 0) && 
					(challenge.getStartDateYear() > 0)
				) {
					Calendar start = Calendar.getInstance();
					start.set(Calendar.DATE, challenge.getStartDateDay());
					start.set(Calendar.MONTH, challenge.getStartDateMonth() - 1);
					start.set(Calendar.YEAR, challenge.getStartDateYear());
					openChallenge.setStartDate(start.getTime());
				}
				openChallenge.setDescription(challenge.getDescription());
				openChallenge.setSurfaceType(challenge.getSurfaceType());
				//location
				GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(challenge.getCountry() + "." + challenge.getState());
				Map<String, Object> cityParams = new HashMap<String, Object>();
				cityParams.put("countryCode", challenge.getCountry());
				cityParams.put("adminCode", challenge.getState());
				GeonamesCityAlternate gca = locationDAO.findGeonamesCityAlternateByName(challenge.getCity(), cityParams);
				GeonamesCity gc = gca.getGeonamesCity();
				challenge.setCityGeonameId(gc.getGeonameId());
				Location loc;
				String fullAddress = "";
				if ((challenge.getAddress() != null) && !challenge.getAddress().trim().isEmpty())
					fullAddress += (challenge.getAddress() + ", ");
				if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
					fullAddress += (gc.getAsciiname() + ", ");
				if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
					fullAddress += (ga.getAsciiname() + ", ");
				if ((challenge.getCountry() != null) && !challenge.getCountry().trim().isEmpty())
					fullAddress += challenge.getCountry();	
				try {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("fullAddress", fullAddress);
					loc = locationDAO.find(params).get(0);
				} catch (Exception e) { loc = null; }
				if ((loc == null) && (fullAddress != "") && (challenge.getAddress() != null) && !challenge.getAddress().trim().isEmpty()) { 				
					try {
						GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
						GeocodingResult[] results = GeocodingApi.geocode(context, fullAddress).await();
						if ((results != null) && (results.length > 0)) {
							try {
								loc = new Location();
								loc.setCountry(challenge.getCountry());
								loc.setState((ga != null) ? ga.getAsciiname() : "");
								loc.setCity((gc != null) ? gc.getAsciiname() : "");
								if (ga != null) loc.setGeonamesAdmin(ga);
								if (gc != null) loc.setGeonamesCity(gc);
								loc.setAddress(challenge.getAddress());
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
				if (loc != null) openChallenge.setLocation(loc);
				openChallenge = challengeDAO.updateOpenChallenge(openChallenge);
		}
		return openChallenge;
	}
	
	@GET
	@Path("/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpenChallenges(
			@QueryParam("firstPlayer") int firstPlayer,
			@QueryParam("secondPlayer") int secondPlayer,
			@QueryParam("status") int status,
			@QueryParam("accepted") boolean accepted,
			@QueryParam("rejected") boolean rejected,
			@QueryParam("deleted") boolean deleted,
			@QueryParam("country") String country, 
			@QueryParam("page") int page
		) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (firstPlayer > 0) params.put("firstPlayer", firstPlayer);
		if (secondPlayer > 0) params.put("secondPlayer", firstPlayer);
		params.put("deleted", false);
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		int size = challengeDAO.countOpenChallenges(params);
		List<OpenChallenge> results = challengeDAO.findOpenChallenges(params, startPosition, maxResults);
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/open/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OpenChallenge getOpenChallenge(
			@PathParam("id") int id
		) {
		return challengeDAO.findOpenChallenge(id);
	}

}
