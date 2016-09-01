package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.entities.Location;

@Remote
public interface LocationDAO {
	
	public Location create(Location loc);
	
	public Location create(String country, 
			String state, String city, String address, 
			String fullAddress, String formattedAddress, 
			float latitude, float longitude);
	
	public Location find(int id);
	
	public List<Location> find(Map<String, Object> params);
	
	public List<Location> find(float latitude, float longitude);

	public List<Location> find(float latitude, float longitude, float radius);
	
	public List<Location> fulltextSearch(String fullAddress);
	
	public GeonamesCity findGeonamesCity(int id);
	
	public GeonamesCityAlternate findGeonamesCityAlternate(int id);
	
	public List<GeonamesCityAlternate> findGeonamesCityAlternates(String name, Map<String, Object> params);
	
	public List<GeonamesCityAlternate> findGeonamesCityAlternates(String name, Map<String, Object> params, int offset, int maxResults);
	
	public GeonamesCityAlternate findGeonamesCityAlternateByName(String name, Map<String, Object> params);
	
	public List<GeonamesAdmin> findGeonamesAdminDivisions(String countryCode);
	
	public GeonamesAdmin findGeonamesAdminDivision(String id);
	
	public GeonamesAdmin findGeonamesAdminDivision(int id);

}
