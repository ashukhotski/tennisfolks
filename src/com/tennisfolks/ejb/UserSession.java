package com.tennisfolks.ejb;

import javax.ejb.Stateful;

@Stateful
public class UserSession {
	
	private int id;
	
	private String token;
	
	private String lang;
	
	/*
	UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	UserSession(int id, String token) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.token = token;
	}
	*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void destroy() {
		this.id = 0;
		this.token = null;
		this.lang = null;
	}
	
	public boolean ok() {
		return ((id != 0) && (token != null) && !token.trim().isEmpty()) ? true : false;
	}

}
