package com.tennisfolks.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.tennisfolks.ejb.UserSession;

@Stateless
public class UserSessionService {
	
	@EJB
	UserSession session;
	
	public UserSession getSession() {
		return session;
	}

}
