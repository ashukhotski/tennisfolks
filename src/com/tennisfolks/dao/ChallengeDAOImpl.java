package com.tennisfolks.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tennisfolks.entities.ChallengeMatch;
import com.tennisfolks.entities.ChallengeMatch_;
import com.tennisfolks.entities.OpenChallenge;
import com.tennisfolks.entities.OpenChallenge_;
import com.tennisfolks.entities.User_;

@Stateless
public class ChallengeDAOImpl implements ChallengeDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;

	@Override
	public ChallengeMatch createChallengeMatch(ChallengeMatch challengeMatch) {
		em.persist(challengeMatch);
		em.flush();
		em.refresh(challengeMatch);
		return challengeMatch;
	}

	@Override
	public OpenChallenge createOpenChallenge(OpenChallenge openChallenge) {
		em.persist(openChallenge);
		em.flush();
		em.refresh(openChallenge);
		return openChallenge;
	}

	@Override
	public ChallengeMatch updateChallengeMatch(ChallengeMatch challengeMatch) {
		em.merge(challengeMatch);
		return findChallengeMatch(challengeMatch.getId());
	}

	@Override
	public OpenChallenge updateOpenChallenge(OpenChallenge openChallenge) {
		em.merge(openChallenge);
		return findOpenChallenge(openChallenge.getId());
	}

	@Override
	public ChallengeMatch findChallengeMatch(int id) {
		return em.find(ChallengeMatch.class, id);
	}

	@Override
	public OpenChallenge findOpenChallenge(int id) {
		return em.find(OpenChallenge.class, id);
	}

	@Override
	public List<ChallengeMatch> findChallengeMatches(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ChallengeMatch> cq = cb.createQuery(ChallengeMatch.class);
		Root<ChallengeMatch> challenge = cq.from(ChallengeMatch.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
		    			cb.equal(challenge.get(ChallengeMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
		    			cb.equal(challenge.get(ChallengeMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
		    		));
		    }
		    if (key.equalsIgnoreCase("secondPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
			    		cb.equal(challenge.get(ChallengeMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
			    		cb.equal(challenge.get(ChallengeMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
			    	));
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.finished), value));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(challenge);
		cq.orderBy(cb.desc(challenge.get(ChallengeMatch_.startDate)));
		TypedQuery<ChallengeMatch> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

	@Override
	public List<OpenChallenge> findOpenChallenges(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OpenChallenge> cq = cb.createQuery(OpenChallenge.class);
		Root<OpenChallenge> challenge = cq.from(OpenChallenge.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(challenge);
		cq.orderBy(cb.desc(challenge.get(OpenChallenge_.startDate)));
		TypedQuery<OpenChallenge> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

	@Override
	public int countChallengeMatches(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ChallengeMatch> challenge = cq.from(ChallengeMatch.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) 
		    		restrictions.add(cb.or(
			    		cb.equal(challenge.get(ChallengeMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
			    		cb.equal(challenge.get(ChallengeMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
			    	));
		    }
		    if (key.equalsIgnoreCase("secondPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty())
		    		restrictions.add(cb.or(
		    			cb.equal(challenge.get(ChallengeMatch_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())),
		    			cb.equal(challenge.get(ChallengeMatch_.secondPlayer).get(User_.id), Integer.valueOf(value.toString()))
		    		)); 
		    }
		    if (key.equalsIgnoreCase("finished")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.finished), value));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("rejected")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.rejected), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(ChallengeMatch_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(challenge));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public int countOpenChallenges(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<OpenChallenge> challenge = cq.from(OpenChallenge.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("firstPlayer")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.firstPlayer).get(User_.id), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("accepted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.accepted), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(challenge.get(OpenChallenge_.deleted), value));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(challenge));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

}
