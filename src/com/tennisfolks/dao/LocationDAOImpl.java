package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tennisfolks.entities.GeonamesAdmin;
import com.tennisfolks.entities.GeonamesAdmin_;
import com.tennisfolks.entities.GeonamesCity;
import com.tennisfolks.entities.GeonamesCityAlternate;
import com.tennisfolks.entities.GeonamesCityAlternate_;
import com.tennisfolks.entities.GeonamesCity_;
import com.tennisfolks.entities.Location;
import com.tennisfolks.entities.Location_;

@Stateless
public class LocationDAOImpl implements LocationDAO { 
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;
	
	public LocationDAOImpl() { }

	@Override
	public Location create(Location loc) {
		em.persist(loc);
		em.flush();
		em.refresh(loc);
		return loc;
	}
	
	@Override
	public Location create(String country, String state, String city, String address,
			String fullAddress, String formattedAddress, float latitude, float longitude) {
		Location loc = new Location();
		loc.setCountry(country);
		loc.setState(state);
		loc.setCity(city);
		loc.setAddress(address);
		loc.setFullAddress(fullAddress);
		loc.setFormattedAddress(formattedAddress);
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		em.persist(loc);
		em.flush();
		em.refresh(loc);
		return loc;
	
		//Map<String, Object> params = new HashMap<String, Object>();
		//params.put("country", country);
		//params.put("state", state);
		//params.put("city", city);
		//params.put("address", address);
		//params.put("fullAddress", fullAddress);
		//params.put("formattedAddress", formattedAddress);
		//params.put("latitude", latitude);
		//params.put("longitude", longitude);
		//try { return find(params).get(0); } catch (Exception e) { return null; }
	}

	@Override
	public Location find(int id) {
		return em.find(Location.class, id);
	}

	@Override
	public List<Location> find(Map<String, Object> params) {
		if (!params.isEmpty()) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Location> cq = cb.createQuery(Location.class);
			Root<Location> location = cq.from(Location.class);
			List<Predicate> restrictions = new ArrayList<Predicate>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
			    String key = entry.getKey();
			    Object value = entry.getValue();
			    if (key.equalsIgnoreCase("country")) { 
			    	restrictions.add(cb.equal(location.get(Location_.country), value.toString()));
			    }
			    if (key.equalsIgnoreCase("state")) { 
			    	restrictions.add(cb.equal(location.get(Location_.state), value.toString()));
			    }
			    if (key.equalsIgnoreCase("city")) { 
			    	restrictions.add(cb.equal(location.get(Location_.city), value.toString()));
			    }
			    if (key.equalsIgnoreCase("address")) { 
			    	restrictions.add(cb.equal(location.get(Location_.address), value.toString()));
			    }
			    if (key.equalsIgnoreCase("fullAddress")) { 
			    	restrictions.add(cb.equal(location.get(Location_.fullAddress), value.toString()));
			    }
			    if (key.equalsIgnoreCase("formattedAddress")) { 
			    	restrictions.add(cb.equal(location.get(Location_.formattedAddress), value.toString()));
			    }
			    if (key.equalsIgnoreCase("latitude")) { 
			    	restrictions.add(cb.equal(location.get(Location_.latitude), Float.valueOf(value.toString())));
			    }
			    if (key.equalsIgnoreCase("longitude")) { 
			    	restrictions.add(cb.equal(location.get(Location_.longitude), Float.valueOf(value.toString())));
			    }
			}
			cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
			cq.select(location);
			TypedQuery<Location> q = em.createQuery(cq);
			return q.getResultList();
		} else { return null; }
	}
	
	@Override
	public List<Location> find(float latitude, float longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> find(float latitude, float longitude, float radius) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> fulltextSearch(String fullAddress) {
		String sql = "select * from location where match(fullAddress) against(?)";
		javax.persistence.Query q = em.createNativeQuery(sql, Location.class);
		q.setParameter(1, fullAddress);
		return (List<Location>) q.getResultList();
	}
	
	//GeoNames
	@Override
	public GeonamesCity findGeonamesCity(int id) {
		return em.find(GeonamesCity.class, id);
	}
		
	@Override
	public GeonamesCityAlternate findGeonamesCityAlternate(int id) {
		return em.find(GeonamesCityAlternate.class, id);
	}
	
	@Override
	public List<GeonamesCityAlternate> findGeonamesCityAlternates(String name, Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GeonamesCityAlternate> cq = cb.createQuery(GeonamesCityAlternate.class);
		Root<GeonamesCityAlternate> city = cq.from(GeonamesCityAlternate.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.like(city.get(GeonamesCityAlternate_.name), name + "%"));
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("countryCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.countryCode), value));
		    }
		    if (key.equalsIgnoreCase("adminCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.adminCode), value));
		    }
		    if (key.equalsIgnoreCase("languageCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.language), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
		cq.groupBy(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.geonameId));
		cq.select(city);
		TypedQuery<GeonamesCityAlternate> q = em.createQuery(cq);
		return q.getResultList();
	}
	
	@Override
	public List<GeonamesCityAlternate> findGeonamesCityAlternates(String name, Map<String, Object> params, int offset, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GeonamesCityAlternate> cq = cb.createQuery(GeonamesCityAlternate.class);
		Root<GeonamesCityAlternate> city = cq.from(GeonamesCityAlternate.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.like(city.get(GeonamesCityAlternate_.name), name + "%"));
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("countryCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.countryCode), value));
		    }
		    if (key.equalsIgnoreCase("adminCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.adminCode), value));
		    }
		    if (key.equalsIgnoreCase("languageCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.language), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
		cq.groupBy(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.geonameId));
		cq.select(city);
		TypedQuery<GeonamesCityAlternate> q = em.createQuery(cq);
		return q.setFirstResult(offset).setMaxResults(maxResults).getResultList();
	}
	
	@Override
	public GeonamesCityAlternate findGeonamesCityAlternateByName(String name, Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GeonamesCityAlternate> cq = cb.createQuery(GeonamesCityAlternate.class);
		Root<GeonamesCityAlternate> city = cq.from(GeonamesCityAlternate.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.name), name));
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("countryCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.countryCode), value));
		    }
		    if (key.equalsIgnoreCase("adminCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.adminCode), value));
		    }
		    if (key.equalsIgnoreCase("languageCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(city.get(GeonamesCityAlternate_.language), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
		cq.groupBy(city.get(GeonamesCityAlternate_.geonamesCity).get(GeonamesCity_.geonameId));
		cq.select(city);
		TypedQuery<GeonamesCityAlternate> q = em.createQuery(cq).setFirstResult(0).setMaxResults(1);
		return q.getSingleResult();
	}
	
	@Override
	public List<GeonamesAdmin> findGeonamesAdminDivisions(String countryCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GeonamesAdmin> cq = cb.createQuery(GeonamesAdmin.class);
		Root<GeonamesAdmin> division = cq.from(GeonamesAdmin.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(division.get(GeonamesAdmin_.country), countryCode));
		cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
		cq.select(division);
		TypedQuery<GeonamesAdmin> q = em.createQuery(cq);
		return q.getResultList();
	}
	
	@Override
	public GeonamesAdmin findGeonamesAdminDivision(String id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GeonamesAdmin> cq = cb.createQuery(GeonamesAdmin.class);
		Root<GeonamesAdmin> division = cq.from(GeonamesAdmin.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(division.get(GeonamesAdmin_.id), id));
		cq.where(cb.and(restrictions.toArray(new Predicate[] {})));
		cq.select(division);
		TypedQuery<GeonamesAdmin> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	@Override
	public GeonamesAdmin findGeonamesAdminDivision(int id) {
		return em.find(GeonamesAdmin.class, id);
	}

}
