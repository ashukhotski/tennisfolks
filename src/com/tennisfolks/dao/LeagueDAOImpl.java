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

import com.tennisfolks.entities.GeonamesAdmin_;
import com.tennisfolks.entities.GeonamesCity_;
import com.tennisfolks.entities.League;
import com.tennisfolks.entities.LeagueMember;
import com.tennisfolks.entities.LeagueMember_;
import com.tennisfolks.entities.League_;
import com.tennisfolks.entities.User;
import com.tennisfolks.entities.User_;

@Stateless
public class LeagueDAOImpl implements LeagueDAO { 
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;
	
	public LeagueDAOImpl() { }

	@Override
	public League create(League league) {
		em.persist(league);
		em.flush();
		em.refresh(league);
		return league;
	}

	@Override
	public League update(League league) {
		em.merge(league);
		return find(league.getId());
	}

	@Override
	public League find(int id) {
		return em.find(League.class, id);
	}

	@Override
	public List<League> find() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<League> cq = cb.createQuery(League.class);
		Root<League> league = cq.from(League.class);
		cq.select(league);
		TypedQuery<League> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public List<League> find(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<League> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<League> cq = cb.createQuery(League.class);
		Root<League> league = cq.from(League.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("country")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.country), value.toString()));
		    }
		    if (key.equalsIgnoreCase("stateId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.geonamesAdmin).get(GeonamesAdmin_.geonameId), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("cityId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.geonamesCity).get(GeonamesCity_.geonameId), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("open")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.open), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.deleted), value));
		    }
		    
		    if (key.equalsIgnoreCase("creator")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.creator).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("manager")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.manager).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("creatorOrManager")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) {
		    		restrictions.add(cb.or(
		    			cb.equal(league.get(League_.creator).get(User_.id), value),
		    			cb.equal(league.get(League_.manager).get(User_.id), value)
		    		));
		    	}
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
		    				cb.lessThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.latitude), maxlat),
		    				cb.greaterThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.latitude), minlat),
		    				cb.lessThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.longitude), maxlon),
		    				cb.greaterThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.longitude), minlon)
		    			));
		    		} catch (Exception e) { }
		    	}
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(league);
		cq.orderBy(cb.desc(league.get(League_.creationDate)));
		TypedQuery<League> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countLeagues(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<League> league = cq.from(League.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("country")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.country), value.toString()));
		    }
		    if (key.equalsIgnoreCase("stateId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.geonamesAdmin).get(GeonamesAdmin_.geonameId), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("cityId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.geonamesCity).get(GeonamesCity_.geonameId), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("open")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.open), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.deleted), value));
		    }
		    
		    if (key.equalsIgnoreCase("creator")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.creator).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("manager")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(League_.manager).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("creatorOrManager")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) {
		    		restrictions.add(cb.or(
		    			cb.equal(league.get(League_.creator).get(User_.id), value),
		    			cb.equal(league.get(League_.manager).get(User_.id), value)
		    		));
		    	}
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
		    				cb.lessThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.latitude), maxlat),
		    				cb.greaterThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.latitude), minlat),
		    				cb.lessThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.longitude), maxlon),
		    				cb.greaterThanOrEqualTo(league.get(League_.geonamesCity).get(GeonamesCity_.longitude), minlon)
		    			));
		    		} catch (Exception e) { }
		    	}
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	@Override
	public LeagueMember createLeagueMember(LeagueMember leagueMember) {
		if ((leagueMember.getId() == null) || leagueMember.getId().trim().isEmpty()) {
			leagueMember.setId(leagueMember.getLeague().getId() + "-" + leagueMember.getUser().getId());
		}
		em.persist(leagueMember);
		em.flush();
		return findLeagueMember(leagueMember.getId());
	}
	
	@Override
	public LeagueMember updateLeagueMember(LeagueMember leagueMember) {
		em.merge(leagueMember);
		return findLeagueMember(leagueMember.getId());
	}
	
	@Override
	public LeagueMember findLeagueMember(String id) {
		return em.find(LeagueMember.class, id);
	}

	@Override
	public List<LeagueMember> getMembers(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LeagueMember> cq = cb.createQuery(LeagueMember.class);
		Root<LeagueMember> member = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("leagueId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("leagueName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(member.get(LeagueMember_.league).get(League_.name), value.toString() + "%"));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(member);
		cq.orderBy(cb.desc(member.get(LeagueMember_.tournamentPoints)));
		TypedQuery<LeagueMember> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

	@Override
	public int countMembers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> member = cq.from(LeagueMember.class);
		cq.select(cb.count(member));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public int countMembers(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> member = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("leagueId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("leagueName")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(member.get(LeagueMember_.league).get(League_.name), value.toString() + "%"));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(member));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public List<LeagueMember> getMemberLeagues(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LeagueMember> cq = cb.createQuery(LeagueMember.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("userId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(league);
		cq.groupBy(league.get(LeagueMember_.league));
		cq.orderBy(cb.asc(league.get(LeagueMember_.league).get(League_.name)));
		TypedQuery<LeagueMember> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countMemberLeagues(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("userId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(league.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	@Override
	public boolean isMember(int leagueId, int userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(league.get(LeagueMember_.league).get(League_.id), leagueId));
		restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), userId));
		restrictions.add(cb.equal(league.get(LeagueMember_.accepted), true));
		restrictions.add(cb.equal(league.get(LeagueMember_.rejected), false));
		restrictions.add(cb.equal(league.get(LeagueMember_.deleted), false));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return (Integer.valueOf(em.createQuery(cq).getSingleResult().toString()) > 0) ? true : false;
	}
	
	@Override
	public boolean isRequestor(int leagueId, int userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(league.get(LeagueMember_.league).get(League_.id), leagueId));
		restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), userId));
		restrictions.add(cb.and(
			cb.equal(league.get(LeagueMember_.accepted), false),
			cb.equal(league.get(LeagueMember_.rejected), false), 
			cb.equal(league.get(LeagueMember_.deleted), false)
		));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return (Integer.valueOf(em.createQuery(cq).getSingleResult().toString()) > 0) ? true : false;
	}
	
	@Override
	public boolean isRejected(int leagueId, int userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(league.get(LeagueMember_.league).get(League_.id), leagueId));
		restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), userId));
		restrictions.add(cb.and(
			cb.equal(league.get(LeagueMember_.accepted), false),
			cb.equal(league.get(LeagueMember_.rejected), true), 
			cb.equal(league.get(LeagueMember_.deleted), false)
		));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return (Integer.valueOf(em.createQuery(cq).getSingleResult().toString()) > 0) ? true : false;
	}
	
	@Override
	public boolean isDeleted(int leagueId, int userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueMember> league = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(league.get(LeagueMember_.league).get(League_.id), leagueId));
		restrictions.add(cb.equal(league.get(LeagueMember_.user).get(User_.id), userId));
		restrictions.add(cb.equal(league.get(LeagueMember_.deleted), true));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(league));
		return (Integer.valueOf(em.createQuery(cq).getSingleResult().toString()) > 0) ? true : false;
	}
	
	@Override
	public List<User> searchAcceptedMembersByName(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<LeagueMember> member = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("leagueId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("name")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.like(member.get(LeagueMember_.user).get(User_.fullName), "%" + value.toString() + "%"));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(member.get(LeagueMember_.user));
		cq.orderBy(cb.desc(member.get(LeagueMember_.user).get(User_.fullName)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public List<User> searchAcceptedMembersById(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<LeagueMember> member = cq.from(LeagueMember.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("leagueId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("userId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.ge(member.get(LeagueMember_.user).get(User_.id), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(member.get(LeagueMember_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(member.get(LeagueMember_.user));
		cq.orderBy(cb.asc(member.get(LeagueMember_.user).get(User_.id)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

}
