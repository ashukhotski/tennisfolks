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

import com.tennisfolks.entities.League_;
import com.tennisfolks.entities.Tournament;
import com.tennisfolks.entities.TournamentMatch;
import com.tennisfolks.entities.TournamentMatch_;
import com.tennisfolks.entities.TournamentParticipant;
import com.tennisfolks.entities.TournamentParticipant_;
import com.tennisfolks.entities.Tournament_;
import com.tennisfolks.entities.User;
import com.tennisfolks.entities.User_;

@Stateless
public class TournamentDAOImpl implements TournamentDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;

	@Override
	public Tournament create(Tournament t) {
		em.persist(t);
		em.flush();
		em.refresh(t);
		return find(t.getId());
	}

	@Override
	public Tournament update(Tournament t) {
		em.merge(t);
		return find(t.getId());
	}

	@Override
	public Tournament find(int id) {
		return em.find(Tournament.class, id);
	}

	@Override
	public List<Tournament> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tournament> cq = cb.createQuery(Tournament.class);
		Root<Tournament> t = cq.from(Tournament.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("league")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.finished), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(t);
		cq.orderBy(cb.desc(t.get(Tournament_.startDate)));
		return em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults).getResultList();
	}

	@Override
	public int count(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Tournament> t = cq.from(Tournament.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("league")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.finished), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(t.get(Tournament_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(t));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public TournamentParticipant createParticipant(TournamentParticipant tp) {
		tp.setId(tp.getTournament().getId() + "-" + tp.getUser().getId());
		em.persist(tp);
		em.flush();
		return findParticipant(tp.getId());
	}

	@Override
	public TournamentParticipant updateParticipant(TournamentParticipant tp) {
		em.merge(tp);
		return findParticipant(tp.getId());
	}

	@Override
	public TournamentParticipant findParticipant(String id) {
		return em.find(TournamentParticipant.class, id);
	}

	@Override
	public void deleteParticipant(TournamentParticipant tp) {
		em.remove(tp);
		em.flush();
	}

	@Override
	public boolean isParticipant(int tournamentId, int participantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TournamentParticipant> tp = cq.from(TournamentParticipant.class);
		cq.where(cb.and(
			cb.equal(tp.get(TournamentParticipant_.tournament).get(Tournament_.id), tournamentId),
			cb.equal(tp.get(TournamentParticipant_.user).get(User_.id), participantId)
		));
		cq.select(cb.count(tp));
		return (em.createQuery(cq).getSingleResult() > 0) ? true : false;
	}
	
	@Override
	public boolean isConfirmedParticipant(int tournamentId, int participantId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TournamentParticipant> tp = cq.from(TournamentParticipant.class);
		cq.where(cb.and(
			cb.equal(tp.get(TournamentParticipant_.tournament).get(Tournament_.id), tournamentId),
			cb.equal(tp.get(TournamentParticipant_.user).get(User_.id), participantId), 
			cb.equal(tp.get(TournamentParticipant_.confirmed), true)
		));
		cq.select(cb.count(tp));
		return (em.createQuery(cq).getSingleResult() > 0) ? true : false;
	}

	@Override
	public List<TournamentParticipant> findParticipants(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TournamentParticipant> cq = cb.createQuery(TournamentParticipant.class);
		Root<TournamentParticipant> tp = cq.from(TournamentParticipant.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("tournament")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.tournament).get(Tournament_.id), value));
		    }
		    if (key.equalsIgnoreCase("participant")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("confirmed")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.confirmed), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(tp);
		cq.orderBy(cb.asc(tp.get(TournamentParticipant_.registrationDate)));
		return em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults).getResultList();
	}

	@Override
	public int countParticipants(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TournamentParticipant> tp = cq.from(TournamentParticipant.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("tournament")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.tournament).get(Tournament_.id), value));
		    }
		    if (key.equalsIgnoreCase("participant")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("confirmed")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.confirmed), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(tp));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public TournamentMatch createMatch(TournamentMatch tm) {
		em.persist(tm);
		em.flush();
		em.refresh(tm);
		return findMatch(tm.getId());
	}

	@Override
	public TournamentMatch updateMatch(TournamentMatch tm) {
		em.merge(tm);
		return findMatch(tm.getId());
	}

	@Override
	public TournamentMatch findMatch(int id) {
		return em.find(TournamentMatch.class, id);
	}

	@Override
	public List<TournamentMatch> findMatches(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TournamentMatch> cq = cb.createQuery(TournamentMatch.class);
		Root<TournamentMatch> tm = cq.from(TournamentMatch.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("tournament")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.tournament).get(Tournament_.id), value));
		    }
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
		    			cb.equal(tm.get(TournamentMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
		    			cb.equal(tm.get(TournamentMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
		    		));
		    }
		    if (key.equalsIgnoreCase("secondPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
			    		cb.equal(tm.get(TournamentMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
			    		cb.equal(tm.get(TournamentMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
			    	));
		    }
		    if (key.equalsIgnoreCase("stage")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.stage), value));
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.finished), value));
		    }
		    if (key.equalsIgnoreCase("approved")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.approved), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(tm);
		cq.orderBy(cb.desc(tm.get(TournamentMatch_.startDate)));
		return em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults).getResultList();
	}

	@Override
	public int countMatches(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TournamentMatch> tm = cq.from(TournamentMatch.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("tournament")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.tournament).get(Tournament_.id), value));
		    }
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
		    			cb.equal(tm.get(TournamentMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
		    			cb.equal(tm.get(TournamentMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
		    		));
		    }
		    if (key.equalsIgnoreCase("secondPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
			    		cb.equal(tm.get(TournamentMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
			    		cb.equal(tm.get(TournamentMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
			    	));
		    }
		    if (key.equalsIgnoreCase("stage")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.stage), value));
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.finished), value));
		    }
		    if (key.equalsIgnoreCase("approved")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.approved), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tm.get(TournamentMatch_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(tm));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public List<User> searchParticipantsById(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<TournamentParticipant> tp = cq.from(TournamentParticipant.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("tournament")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.tournament).get(Tournament_.id), value));
		    }
		    if (key.equalsIgnoreCase("userId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.ge(tp.get(TournamentParticipant_.user).get(User_.id), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("confirmed")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(tp.get(TournamentParticipant_.confirmed), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(tp.get(TournamentParticipant_.user));
		cq.orderBy(cb.asc(tp.get(TournamentParticipant_.user).get(User_.id)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

}
