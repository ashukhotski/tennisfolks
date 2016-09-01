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

import com.tennisfolks.entities.LeagueBlogEntry;
import com.tennisfolks.entities.LeagueBlogEntryComment;
import com.tennisfolks.entities.LeagueBlogEntryComment_;
import com.tennisfolks.entities.LeagueBlogEntry_;
import com.tennisfolks.entities.League_;
import com.tennisfolks.entities.User_;

@Stateless
public class LeagueBlogEntryDAOImpl implements LeagueBlogEntryDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;
	
	public LeagueBlogEntryDAOImpl() { }
	
	@Override
	public LeagueBlogEntry create(LeagueBlogEntry entry) {
		em.persist(entry);
		em.flush();
		em.refresh(entry);
		return find(entry.getId());
	}
	
	@Override
	public LeagueBlogEntry update(LeagueBlogEntry entry) {
		em.merge(entry);
		return find(entry.getId());
	}
	
	@Override
	public LeagueBlogEntry find(int id) {
		return em.find(LeagueBlogEntry.class, id);
	}
	
	@Override
	public List<LeagueBlogEntry> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LeagueBlogEntry> cq = cb.createQuery(LeagueBlogEntry.class);
		Root<LeagueBlogEntry> entry = cq.from(LeagueBlogEntry.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
		    String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("league")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("published")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.published), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.deleted), value));
		    }
		}
		
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		
		cq.select(entry);
		
		cq.orderBy(cb.desc(entry.get(LeagueBlogEntry_.creationDate)));
		TypedQuery<LeagueBlogEntry> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int count(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueBlogEntry> entry = cq.from(LeagueBlogEntry.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("league")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.league).get(League_.id), value));
		    }
		    if (key.equalsIgnoreCase("published")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.published), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(LeagueBlogEntry_.deleted), value));
		    }
		}
		cq.select(cb.count(entry));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	
	@Override
	public LeagueBlogEntryComment createComment(LeagueBlogEntryComment comment) {
		em.persist(comment);
		em.flush();
		em.refresh(comment);
		return findComment(comment.getId());
	}
	
	@Override
	public LeagueBlogEntryComment updateComment(LeagueBlogEntryComment comment) {
		em.merge(comment);
		return findComment(comment.getId());
	}
	
	@Override
	public LeagueBlogEntryComment findComment(int id) {
		return em.find(LeagueBlogEntryComment.class, id);
	}
	
	@Override
	public List<LeagueBlogEntryComment> findComments(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LeagueBlogEntryComment> cq = cb.createQuery(LeagueBlogEntryComment.class);
		Root<LeagueBlogEntryComment> comment = cq.from(LeagueBlogEntryComment.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
		    String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("entry")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.entry).get(LeagueBlogEntry_.id), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.deleted), value));
		    }
		}
		
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		
		cq.select(comment);
		
		cq.orderBy(cb.desc(comment.get(LeagueBlogEntryComment_.creationDate)));
		TypedQuery<LeagueBlogEntryComment> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countComments(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LeagueBlogEntryComment> comment = cq.from(LeagueBlogEntryComment.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("entry")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.entry).get(LeagueBlogEntry_.id), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(LeagueBlogEntryComment_.deleted), value));
		    }
		}
		cq.select(cb.count(comment));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

}
