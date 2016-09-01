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

import com.tennisfolks.entities.UserBlogEntry;
import com.tennisfolks.entities.UserBlogEntryComment;
import com.tennisfolks.entities.UserBlogEntryComment_;
import com.tennisfolks.entities.UserBlogEntry_;
import com.tennisfolks.entities.User_;

@Stateless
public class UserBlogEntryDAOImpl implements UserBlogEntryDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;
	
	public UserBlogEntryDAOImpl() { }
	
	@Override
	public UserBlogEntry create(UserBlogEntry entry) {
		em.persist(entry);
		em.flush();
		em.refresh(entry);
		return find(entry.getId());
	}
	
	@Override
	public UserBlogEntry update(UserBlogEntry entry) {
		em.merge(entry);
		return find(entry.getId());
	}
	
	@Override
	public UserBlogEntry find(int id) {
		return em.find(UserBlogEntry.class, id);
	}
	
	@Override
	public List<UserBlogEntry> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserBlogEntry> cq = cb.createQuery(UserBlogEntry.class);
		Root<UserBlogEntry> entry = cq.from(UserBlogEntry.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
		    String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("user")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("published")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.published), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.deleted), value));
		    }
		}
		
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		
		cq.select(entry);
		
		cq.orderBy(cb.desc(entry.get(UserBlogEntry_.creationDate)));
		TypedQuery<UserBlogEntry> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int count(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserBlogEntry> entry = cq.from(UserBlogEntry.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("user")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.user).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("published")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.published), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(entry.get(UserBlogEntry_.deleted), value));
		    }
		}
		cq.select(cb.count(entry));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	
	@Override
	public UserBlogEntryComment createComment(UserBlogEntryComment comment) {
		em.persist(comment);
		em.flush();
		em.refresh(comment);
		return findComment(comment.getId());
	}
	
	@Override
	public UserBlogEntryComment updateComment(UserBlogEntryComment comment) {
		em.merge(comment);
		return findComment(comment.getId());
	}
	
	@Override
	public UserBlogEntryComment findComment(int id) {
		return em.find(UserBlogEntryComment.class, id);
	}
	
	@Override
	public List<UserBlogEntryComment> findComments(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserBlogEntryComment> cq = cb.createQuery(UserBlogEntryComment.class);
		Root<UserBlogEntryComment> comment = cq.from(UserBlogEntryComment.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
		    String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("entry")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.entry).get(UserBlogEntry_.id), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.deleted), value));
		    }
		}
		
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		
		cq.select(comment);
		
		cq.orderBy(cb.desc(comment.get(UserBlogEntryComment_.creationDate)));
		TypedQuery<UserBlogEntryComment> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countComments(Map<String, Object> params) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserBlogEntryComment> comment = cq.from(UserBlogEntryComment.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
		    Object value = item.getValue();
		    if (key.equalsIgnoreCase("author")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.author).get(User_.id), value));
		    }
		    if (key.equalsIgnoreCase("entry")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.entry).get(UserBlogEntry_.id), value));
		    }
		    if (key.equalsIgnoreCase("deleted")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(comment.get(UserBlogEntryComment_.deleted), value));
		    }
		}
		cq.select(cb.count(comment));
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

}
