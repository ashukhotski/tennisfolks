package com.tennisfolks.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tennisfolks.dao.LanguageDAO;
import com.tennisfolks.entities.Language;

@Path("/languages") @Stateless
public class LanguageService {
	
	@EJB
	LanguageDAO languageDAO;
	
	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Language find(@PathParam("code") String code) {
		return languageDAO.findByCode(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Language> findAll() {
		return languageDAO.findAll();
	}

}
