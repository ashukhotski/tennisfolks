package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.mindrot.jbcrypt.BCrypt;

import com.tennisfolks.entities.Language;
import com.tennisfolks.entities.Location_;
import com.tennisfolks.entities.User;
import com.tennisfolks.entities.User_;

@Stateless
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext(unitName = "tennisfolks"/*, type = PersistenceContextType.EXTENDED*/)
	EntityManager em;
	
	public UserDAOImpl() { }
	
	@Override
	public User create(User user) {
		user.setFullName(user.getFirstName() + " " + user.getLastName());
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		em.persist(user);
		em.flush();
		em.refresh(user);
		return find(user.getId());
	}
	
	@Override
	public User create(String email, String password, String firstName, String lastName, Language language) {
		User u = new User();
		u.setEmail(email);
		u.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setFullName(firstName + " " + lastName);
		u.setLanguage(language);

		u.setDeleted(false);
		
		em.persist(u);
		em.flush();
		
		return findByEmailAndPassword(email, password);
	}
	
	@Override
	public User update(User user) {
		em.merge(user);
		return find(user.getId());
	}
	
	@Override
	public User find(int id) {
		return em.find(User.class, id);
	}
	
	@Override
	public User findByEmailAndPassword(String email, String password) {
		User result;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);		
		cq.where(cb.equal(user.get(User_.email), email));
		cq.select(user);
		TypedQuery<User> q = em.createQuery(cq);
		try {
			result = q.getSingleResult();
		} catch (Exception e) { result = null; }
		if ((result != null) && BCrypt.checkpw(password, result.getPassword())) {
			return result;
		} else { return null; }
	}
	
	@Override
	public User findByEmail(String email) { 
		User result;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);		
		cq.where(cb.equal(user.get(User_.email), email));
		cq.select(user);
		TypedQuery<User> q = em.createQuery(cq);
		try {
			result = q.getSingleResult();
		} catch (Exception e) { result = null; }
		return result;
	}
	
	@Override
	public List<User> findByDeleted(boolean deleted) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.where(cb.equal(user.get(User_.deleted), deleted));
		cq.select(user);
		TypedQuery<User> q = em.createQuery(cq);
		return q.getResultList();
	}
	
	@Override
	public List<User> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);
		TypedQuery<User> q = em.createQuery(cq);
		return q.getResultList();
	}
	
	@Override
	public List<User> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.firstName), value + "%"));
		    }
		    if (key.equalsIgnoreCase("lastName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.lastName), value + "%"));
		    }
		    if (key.equalsIgnoreCase("name")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.fullName), "%" + value + "%"));
		    }
		    if (key.equalsIgnoreCase("countryCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.country), value));
		    }
		    if (key.equalsIgnoreCase("stateId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.geonamesAdmin), value));
		    }
		    if (key.equalsIgnoreCase("cityId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.geonamesCity), value));
		    }
		    if (key.equalsIgnoreCase("level")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.level), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.deleted), value));
		    }
		    if (key.equalsIgnoreCase("nearby")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) {
		    		try {
		    			String [] nearby = value.toString().split(",");
		    			float minlat = Float.valueOf(nearby[0]);
		    			float minlon = Float.valueOf(nearby[1]);
		    			float maxlat = Float.valueOf(nearby[2]);
		    			float maxlon = Float.valueOf(nearby[3]);
		    			
		    			restrictions.add(cb.and(
		    				cb.lessThanOrEqualTo(user.get(User_.location).get(Location_.latitude), maxlat),
		    				cb.greaterThanOrEqualTo(user.get(User_.location).get(Location_.latitude), minlat),
		    				cb.lessThanOrEqualTo(user.get(User_.location).get(Location_.longitude), maxlon),
		    				cb.greaterThanOrEqualTo(user.get(User_.location).get(Location_.longitude), minlon)
		    			));
		    		} catch (Exception e) { }
		    	}
		    }
		}
		
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		
		cq.select(user);
		
		cq.orderBy(cb.desc(user.get(User_.lastActivityDate)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int count() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(User.class)));
		cq.where(/*your stuff*/);
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	@Override
	public int count(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> user = cq.from(User.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.firstName), value + "%"));
		    }
		    if (key.equalsIgnoreCase("lastName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.lastName), value + "%"));
		    }
		    if (key.equalsIgnoreCase("name")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(user.get(User_.fullName), "%" + value + "%"));
		    }
		    if (key.equalsIgnoreCase("countryCode")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.country), value));
		    }
		    if (key.equalsIgnoreCase("stateId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.geonamesAdmin), value));
		    }
		    if (key.equalsIgnoreCase("cityId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.geonamesCity), value));
		    }
		    if (key.equalsIgnoreCase("level")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.level), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(user.get(User_.deleted), value));
		    }
		    if (key.equalsIgnoreCase("nearby")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) {
		    		try {
		    			String [] nearby = value.toString().split(",");
		    			float minlat = Float.valueOf(nearby[0]);
		    			float minlon = Float.valueOf(nearby[1]);
		    			float maxlat = Float.valueOf(nearby[2]);
		    			float maxlon = Float.valueOf(nearby[3]);
		    			
		    			restrictions.add(cb.and(
		    				cb.lessThanOrEqualTo(user.get(User_.location).get(Location_.latitude), maxlat),
		    				cb.greaterThanOrEqualTo(user.get(User_.location).get(Location_.latitude), minlat),
		    				cb.lessThanOrEqualTo(user.get(User_.location).get(Location_.longitude), maxlon),
		    				cb.greaterThanOrEqualTo(user.get(User_.location).get(Location_.longitude), minlon)
		    			));
		    		} catch (Exception e) { }
		    	}
		    }
		}
		cq.select(cb.count(user));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

}
