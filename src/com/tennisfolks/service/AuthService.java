package com.tennisfolks.service;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.resteasy.util.Base64;
import org.mindrot.jbcrypt.BCrypt;

import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.ejb.UserSession;
import com.tennisfolks.entities.User;
import com.tennisfolks.util.Credentials;
import com.tennisfolks.util.UserSessionService;

@Path("/auth") @Stateless
public class AuthService {
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@POST
	@Path("/restore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response restorePassword(String email) {
		if (!userSessionService.getSession().ok()) {
			User user = userDAO.findByEmail(email);
			if (user != null) {
				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
				String digits = "0123456789";
				
				String newPwd = RandomStringUtils.random( 7, 0, 0, false, false, characters.toCharArray(), new java.security.SecureRandom() ) + RandomStringUtils.random( 3, 0, 0, false, false, digits.toCharArray(), new java.security.SecureRandom() );

				user.setPassword(BCrypt.hashpw(newPwd, BCrypt.gensalt()));
				user = userDAO.update(user);
				System.out.println("new password: " + newPwd + " (hashed: " + user.getPassword() + ")");
				return Response.ok(newPwd).build();
			}
		}
		return null;
	}
	
	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserSession signin(Credentials credentials) {
		String email = credentials.getEmail();
		String password = credentials.getPassword();
		String token = Base64.encodeBytes(BCrypt.hashpw(email + password + Calendar.getInstance().getTime(), BCrypt.gensalt()).getBytes());
		
		User user = userDAO.findByEmailAndPassword(email, password);
		if (user != null) {
			userSessionService.getSession().setId(user.getId());
			userSessionService.getSession().setToken(token);
			userSessionService.getSession().setLang(user.getLanguage().getCode());
		}  else { userSessionService.getSession().destroy(); return null; }
		return userSessionService.getSession();
	}
	
	@POST
	@Path("/session")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserSession getSession() {
		return (userSessionService.getSession().ok()) ? userSessionService.getSession() : null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean authenticate(UserSession session) {
		return (userSessionService.getSession().ok() && 
			(session != null) && 
			(session.getId() == userSessionService.getSession().getId()) && 
			session.getToken().equals(userSessionService.getSession().getToken())) ? true : false;
	}
	
	@POST
	@Path("/signout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signout() {
		userSessionService.getSession().destroy();
		return Response.ok().build();
	}

}
