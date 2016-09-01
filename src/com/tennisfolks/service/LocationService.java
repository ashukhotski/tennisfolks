package com.tennisfolks.service;

import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.tennisfolks.dao.LocationDAO;
import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.util.Country;

@Path("/locations") @Stateless
public class LocationService {
	
	@EJB
	LocationDAO locationDAO;
	
	@GET
	@Path("/countries")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountries(@QueryParam("lang") String lang) {
		Locale locale = new Locale.Builder().setLanguage(lang).build();
		
		String[] locales = Locale.getISOCountries();
		List<Country> countries = new ArrayList<Country>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(new Country(obj.getCountry(), obj.getDisplayCountry(locale)));
		}
		return countries;
	}
	
	@GET
	@Path("/countries/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country getCountry(
			@PathParam("code") String code, 
			@QueryParam("lang") String lang
		) {
		Locale locale = new Locale.Builder().setLanguage(lang).build();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			if (countryCode.equals(code)) {
				Locale obj = new Locale("", countryCode);
				return new Country(obj.getCountry(), obj.getDisplayCountry(locale));	
			}
		}
		return null;
	}
	
	@GET
	@Path("/countries/{code}/divisions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GeonamesAdmin> findDivisions(
			@PathParam("code") String code
		) {
		return locationDAO.findGeonamesAdminDivisions(code);
	}
	
	@GET
	@Path("/countries/{code}/cities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GeonamesCityAlternate> findCityAlernates(
				@PathParam("code") String code,
				@QueryParam("division") String division, 
				@QueryParam("lang") String lang,
				@QueryParam("query") String query
			) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("countryCode", code);
		params.put("adminCode", division);
		params.put("languageCode", lang);
		return locationDAO.findGeonamesCityAlternates(query, params, 0, 5);
	}
	
	@GET
	@Path("/geocoding")
	@Produces(MediaType.APPLICATION_JSON)
	public GeocodingResult findAddress(@QueryParam("address") String address, @QueryParam("language") String language) {
		if ((language == null) || language.trim().isEmpty()) language = "en";
		if ((address != null) && !address.trim().isEmpty()) {
			try {
				GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA46ycjZJhbxFQds5D2qPQrA0x1Q_yMyLQ");
				GeocodingResult[] results = GeocodingApi.geocode(context, address).language(language).await();
				if ((results != null) && (results.length > 0)) {
					return results[0];
				}
			} catch (Exception e) { }
		} 
		return null;
	}

}
