package com.tennisfolks.service;

import java.util.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.tennisfolks.dao.ImageDAO;
import com.tennisfolks.dao.LanguageDAO;
import com.tennisfolks.dao.LocationDAO;
import com.tennisfolks.dao.UserBlogEntryDAO;
import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.dao.UserFollowerDAO;
import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.entities.Location;
import com.tennisfolks.entities.User;
import com.tennisfolks.entities.UserBlogEntry;
import com.tennisfolks.entities.UserBlogEntryComment;
import com.tennisfolks.entities.UserFollower;
import com.tennisfolks.util.UserSessionService;

@Path("/users") @Stateless
public class UserService {
	
	@EJB 
	UserDAO userDAO;
	
	@EJB
	LanguageDAO languageDAO;
	
	@EJB
	LocationDAO locationDAO;
	
	@EJB
	ImageDAO imageDAO;
	
	@EJB
	UserBlogEntryDAO blogDAO;
	
	@EJB
	UserFollowerDAO followerDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@Resource
	EJBContext ejbContext;
	
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User create(User user) {
		return userDAO.create(user);
	}
	
	@POST
	@Path("/{id}/photo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUserPhoto(int imageId, @PathParam("id") int id, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() == id)
		) {
			User user = userDAO.find(id);
			if ((user != null) && (imageDAO.find(imageId) != null)) {
				user.setPhoto(imageDAO.find(imageId));
				return userDAO.update(user);
			} else { return user; }
		} else { return userDAO.find(id); }
	}
	
	@javax.ws.rs.PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(
			User u,
			@PathParam("id") int id, 
			@QueryParam("token") String token
			) {
		if (userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() == id)) {
			
			User user = userDAO.find(id);
			
			if (!BCrypt.checkpw(u.getCurrentPassword(), user.getPassword())) {
				return user;
			}

			//personal details
			user.setEmail(u.getEmail());
			if ((u.getNewPassword() != null) && !u.getNewPassword().trim().isEmpty()) user.setPassword(BCrypt.hashpw(u.getNewPassword(), BCrypt.gensalt()));
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setFullName(u.getFirstName() + " " + u.getLastName());
			
			//contact info and address
			GeonamesAdmin ga = locationDAO.findGeonamesAdminDivision(u.getCountry() + "." + u.getState());
			if (ga != null) user.setGeonamesAdmin(ga);
			
			Map<String, Object> cityParams = new HashMap<String, Object>();
			cityParams.put("countryCode", u.getCountry());
			cityParams.put("adminCode", u.getState());
			//cityParams.put("languageCode", lang);
			GeonamesCityAlternate gca = locationDAO.findGeonamesCityAlternateByName(u.getCity(), cityParams);
			GeonamesCity gc = gca.getGeonamesCity();
			u.setCityGeonameId(gc.getGeonameId());
			//GeonamesCity gc = locationDAO.findGeonamesCity(u.getCityGeonameId());
			if (gc != null) user.setGeonamesCity(gc);
			Location loc;
			String fullAddress = "";
			if ((u.getAddress() != null) && !u.getAddress().trim().isEmpty())
				fullAddress += (u.getAddress() + ", ");
			//if ((u.getCity() != null) && !u.getCity().trim().isEmpty())
			//	fullAddress += (u.getCity() + ", ");
			if ((gc != null) && !gc.getAsciiname().trim().isEmpty()) 
				fullAddress += (gc.getAsciiname() + ", ");
			//if ((u.getState() != null) && !u.getState().trim().isEmpty())
			//	fullAddress += (u.getState() + ", ");
			if ((ga != null) && !ga.getAsciiname().trim().isEmpty())
				fullAddress += (ga.getAsciiname() + ", ");
			if ((u.getCountry() != null) && !u.getCountry().trim().isEmpty())
				fullAddress += u.getCountry();	
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("fullAddress", fullAddress);
				loc = locationDAO.find(params).get(0);
			} catch (Exception e) { loc = null; }
			if ((loc == null) && (fullAddress != "")) { 				
				try {
					GeocodingResult[] results = null;
					if ((u.getAddress() != null) && !u.getAddress().trim().isEmpty()) {
						GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
						results = GeocodingApi.geocode(context, fullAddress).await();
					}
					if ((results != null) && (results.length > 0)) {
						try {
							loc = new Location();
							loc.setCountry(u.getCountry());
							loc.setState((ga != null) ? ga.getAsciiname() : "");
							loc.setCity((gc != null) ? gc.getAsciiname() : "");
							if (ga != null) loc.setGeonamesAdmin(ga);
							if (gc != null) loc.setGeonamesCity(gc);
							loc.setAddress(u.getAddress());
							loc.setFullAddress(fullAddress);
							loc.setFormattedAddress(results[0].formattedAddress);
							loc.setLatitude((float) results[0].geometry.location.lat);
							loc.setLongitude((float) results[0].geometry.location.lng);
							loc = locationDAO.create(loc);
						} catch (Throwable t) { ejbContext.setRollbackOnly(); loc = null; }
					} else {
						loc = new Location();
						loc.setCountry(u.getCountry());
						loc.setState((ga != null) ? ga.getAsciiname() : "");
						loc.setCity((gc != null) ? gc.getAsciiname() : "");
						if (ga != null) loc.setGeonamesAdmin(ga);
						if (gc != null) loc.setGeonamesCity(gc);
						loc.setAddress("");
						loc.setFullAddress(fullAddress);
						loc.setFormattedAddress(fullAddress);
						loc.setLatitude(gc.getLatitude());
						loc.setLongitude(gc.getLongitude());
						loc = locationDAO.create(loc);
					}
				} catch (Exception e) { System.out.println("Geocoding error: " + e.getMessage()); }
			}
			user.setPhone(u.getPhone());
			user.setCountry(u.getCountry());
			user.setState(u.getState());
			user.setCity(u.getCity());
			user.setAddress(u.getAddress());
			if (loc != null) {
				user.setLatitude(loc.getLatitude());
				user.setLongitude(loc.getLongitude());
				user.setLocation(loc);
			} else {
				if (gc != null) {
					user.setLatitude(gc.getLatitude());
					user.setLongitude(gc.getLongitude());
				}
			}
			
			
			//meta data
			if ((u.getbDay() > 0) && (u.getbMonth() > 0) && (u.getbYear() > 0)) {
				Calendar birthday = Calendar.getInstance();
				birthday.set(Calendar.DATE, u.getbDay());
				birthday.set(Calendar.MONTH, u.getbMonth() - 1);
				birthday.set(Calendar.YEAR, u.getbYear());
				user.setBirthday(birthday.getTime());
			}
			user.setGender(u.getGender());
			user.setDescription(u.getDescription());
			
			//settings
			user.setLevel(u.getLevel());
			user.setLevelShift(u.getLevelShift());
			if (languageDAO.findByCode(u.getLanguage().getCode()) != null) {
				user.setLanguage(languageDAO.findByCode(u.getLanguage().getCode()));
			}
			
			
			return userDAO.update(user);
		} else { return null; }
	}
	
	@POST
	@Path("/{id}/update/lastactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateOnline(@PathParam("id") int id, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			(userSessionService.getSession().getId() == id) && 
			userSessionService.getSession().getToken().equals(token)
		) {
			User user = userDAO.find(id);
			if (user != null) { 
				user.setLastActivityDate(Calendar.getInstance().getTime());
				return userDAO.update(user);
			} else { return null; }
		} else { return null; }
	}
	
	@POST
	@Path("{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public User delete(
			String password, 
			@PathParam("id") int id, 
			@QueryParam("token") String token
			) {
		User user = userDAO.find(id);
		if (
			(user != null) && BCrypt.checkpw(password, user.getPassword()) &&
			userSessionService.getSession().ok() && 
			(user.getId() == userSessionService.getSession().getId()) && 
			userSessionService.getSession().getToken().equals(token)
		) {
			user.setDeleted(true);
			user = userDAO.update(user);
		}
		return user;
	}
	
	@POST
	@Path("{id}/restore")
	@Produces(MediaType.APPLICATION_JSON)
	public User restore(
			String password, 
			@PathParam("id") int id, 
			@QueryParam("token") String token
			) {
		User user = userDAO.find(id);
		if (
			(user != null) &&  BCrypt.checkpw(password, user.getPassword()) &&
			userSessionService.getSession().ok() && 
			(user.getId() == userSessionService.getSession().getId()) && 
			userSessionService.getSession().getToken().equals(token)
		) {
			user.setDeleted(false);
			user = userDAO.update(user);
		}
		return user;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User findUser(@PathParam("id") int id) {
		return userDAO.find(id);
	}
	
	@GET
	@Path("/{id}/check/following")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkIfFollowing(@PathParam("id") int id, @QueryParam("token") String token) {
		if (userSessionService.getSession().ok() && userSessionService.getSession().getToken().equals(token)) {
			return followerDAO.checkIfFollowing(userSessionService.getSession().getId(), id);
		}
		return false;
	}
	
	@POST
	@Path("/follow")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserFollower follow(
		int id, 
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() != id) && 
			!followerDAO.checkIfFollowing(userSessionService.getSession().getId(), id)
		) {
			User u = userDAO.find(id);
			if (u != null) {
				UserFollower uf = new UserFollower();
				uf.setFollower(userDAO.find(userSessionService.getSession().getId()));
				uf.setFollowing(u);
				return followerDAO.create(uf);
			}
		}
		return null;
	}
	
	@POST
	@Path("/unfollow")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int unfollow(
		int id, 
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() &&
			userSessionService.getSession().getToken().equals(token) && 
			(userSessionService.getSession().getId() != id) && 
			followerDAO.checkIfFollowing(userSessionService.getSession().getId(), id)
		) {
			User u = userDAO.find(id);
			if (u != null) {
				UserFollower uf = followerDAO.find(userSessionService.getSession().getId(), id);
				followerDAO.delete(uf);
				return uf.getId();
			}
		}
		return 0;
	}
	
	@GET
	@Path("/{id}/followers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findFollowers(
		@PathParam("id") int id, 
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		
		int size = followerDAO.countFollowers(id);
		List<User> results = followerDAO.findFollowers(id, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/following")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findFollowing(
		@PathParam("id") int id, 
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		
		int size = followerDAO.countFollowing(id);
		List<User> results = followerDAO.findFollowing(id, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/featured")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<User> getFeatured(@QueryParam("first") int first, @QueryParam("max") int max) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleted", false);
		first = (first > 0) ? first : 0;
		max = (max > 0) ? max : 4;
		return userDAO.find(params, first, max);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(
		@QueryParam("name") String name, 
		@QueryParam("country") String country, 
		@QueryParam("nearby") boolean nearby,
		@QueryParam("page") int page
	) {
		int maxResults = 8;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		if ((name != null) && !name.trim().isEmpty()) params.put("name", name);
		if ((country != null) && !country.trim().isEmpty()) params.put("countryCode", country);

		if (nearby && userSessionService.getSession().ok()) {
			User u = userDAO.find(userSessionService.getSession().getId());
			if (u != null) {
				//6371;
				float radius = new Float(0.001);
				float minlat = u.getLatitude() - radius;
				float minlon = u.getLongitude() - radius;
				float maxlat = u.getLatitude() + radius;
				float maxlon = u.getLongitude() + radius;

				String coords = minlat + "," + minlon + "," + maxlat + "," + maxlon;
				params.put("nearby", coords);
			}
		}
		
		params.put("deleted", false);
		int size = userDAO.count(params);
		List<User> results = userDAO.find(params, startPosition, maxResults);
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"nearby\": " + nearby + ", \"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}/blog")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBlogEntries(
		@PathParam("id") int id,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", id);
		params.put("deleted", false);
		if (!userSessionService.getSession().ok() || (userSessionService.getSession().getId() != id)) {
			params.put("published", true);
		}
		int size = blogDAO.count(params);
		List<UserBlogEntry> entries = blogDAO.find(params, startPosition, maxResults);
		
		Map<String, Object> paramsForComments = new HashMap<String, Object>();
		List<UserBlogEntry> results = new ArrayList<UserBlogEntry>();
		int sizeOfComments = 0;
		int currentPageForComments = 1;
		int nextPageForComments = 1;
		for (UserBlogEntry entry : entries) {
			paramsForComments.clear();
			paramsForComments.put("entry", entry.getId());
			paramsForComments.put("deleted", false);
			sizeOfComments = blogDAO.countComments(paramsForComments);
			currentPageForComments = 1;
			nextPageForComments = 1;
			if (currentPageForComments * 5 < sizeOfComments) {
				nextPageForComments = nextPageForComments + 1;	
			}
			entry.setCurrentPageForComments(currentPageForComments);
			entry.setNextPageForComments(nextPageForComments);
			entry.setComments(blogDAO.findComments(paramsForComments, 0, 5));
			results.add(entry);
		}
		entries.clear();
		
		int currentPage = page;
		int nextPage = currentPage;	
		if (currentPage * maxResults < size) {
			nextPage = nextPage + 1;	
		}
		
		String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/blog/{entryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserBlogEntry getUserBlogEntry(
		@PathParam("entryId") int id, 
		@QueryParam("token") String token
	) {
		UserBlogEntry entry = blogDAO.find(id);
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
				(entry.getAuthor().getId() == userSessionService.getSession().getId()) || 
				(entry.getUser().getId() == userSessionService.getSession().getId())
			)
		) {
			return entry;
		} else { return null; }
	}
	
	@POST
	@Path("/{id}/blog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserBlogEntry createUserBlogEntry(
		String text,
		@PathParam("id") int id,
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(text != null) && !text.trim().isEmpty()
		) {
			UserBlogEntry entry = new UserBlogEntry();
			entry.setAuthor(userDAO.find(userSessionService.getSession().getId()));
			entry.setUser(userDAO.find(id));
			entry.setDeleted(false);
			entry.setPublished(true);
			entry.setText(text);
			return blogDAO.create(entry);
		} else { return null; }
	}
	
	@GET
	@Path("/blog/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBlogEntryComments(
		@PathParam("id") int id,
		@QueryParam("page") int page
	) {
		int maxResults = 5;
		int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entry", id);
		params.put("deleted", false);
		int size = blogDAO.countComments(params);
		List<UserBlogEntryComment> results = blogDAO.findComments(params, startPosition, maxResults);
		
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
	public UserBlogEntryComment createUserBlogEntryComment(
		String text,
		@PathParam("id") int id,
		@QueryParam("token") String token
	) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token) && 
			(text != null) && !text.trim().isEmpty()
		) {
			UserBlogEntryComment comment = new UserBlogEntryComment();
			comment.setAuthor(userDAO.find(userSessionService.getSession().getId()));
			comment.setEntry(blogDAO.find(id));
			comment.setDeleted(false);
			comment.setText(text);
			return blogDAO.createComment(comment);
		} else { return null; }
	}

}
