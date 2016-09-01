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

import com.tennisfolks.entities.Message;
import com.tennisfolks.entities.Message_;
import com.tennisfolks.entities.User_;

@Stateless
public class MessageDAOImpl implements MessageDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;

	@Override
	public Message create(Message message) {
		em.persist(message);
		em.flush();
		em.refresh(message);
		return message;
	}

	@Override
	public Message update(Message message) {
		em.merge(message);
		return find(message.getId());
	}
	
	@Override
	public Message find(int id) {
		return em.find(Message.class, id);
	}

	@Override
	public List<Message> find(Map<String, Object> params, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> msg = cq.from(Message.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (key.equalsIgnoreCase("dialogueId")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(msg.get(Message_.dialogueId), value));
		    }
		    if (key.equalsIgnoreCase("sender")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(msg.get(Message_.sender).get(User_.id), Integer.valueOf(value.toString())));
		    }
		    if (key.equalsIgnoreCase("recipient")) {
		    	if ((value != null) && !value.toString().trim().isEmpty()) restrictions.add(cb.equal(msg.get(Message_.recipient).get(User_.id), Integer.valueOf(value.toString())));
		    }
		}
		cq.where(cb.and(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(msg);
		TypedQuery<Message> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public int count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getDialogues(int userId, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> msg = cq.from(Message.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(msg.get(Message_.sender).get(User_.id), userId));
		restrictions.add(cb.equal(msg.get(Message_.recipient).get(User_.id), userId));
		cq.where(cb.or(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(msg);
		cq.groupBy(msg.get(Message_.dialogueId));
		cq.orderBy(cb.desc(msg.get(Message_.creationDate)));
		TypedQuery<Message> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

	@Override
	public int countDialogues(int userId) {
		return Integer.valueOf(em.createNativeQuery("select count(*) from (select * from message where sender = ?1 or recipient = ?1 group by dialogueId) m").setParameter(1, userId).getSingleResult().toString());
		/*
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Message> msg = cq.from(Message.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(msg.get(Message_.sender).get(User_.id), userId));
		restrictions.add(cb.equal(msg.get(Message_.recipient).get(User_.id), userId));
		cq.where(cb.or(restrictions.toArray(new Predicate[restrictions.size()])));
		cq.select(cb.count(msg));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
		*/
	}

	@Override
	public List<Message> getMessages(String dialogueId, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> msg = cq.from(Message.class);
		cq.where(cb.equal(msg.get(Message_.dialogueId), dialogueId));
		cq.select(msg);
		cq.orderBy(cb.desc(msg.get(Message_.creationDate)));
		TypedQuery<Message> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}

	@Override
	public int countMessages(String dialogueId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Message> msg = cq.from(Message.class);
		cq.where(cb.equal(msg.get(Message_.dialogueId), dialogueId));
		cq.select(cb.count(msg));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}
	
	@Override
	public int countNewMessages(int recipientId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Message> msg = cq.from(Message.class);
		cq.where(cb.and(
			cb.equal(msg.get(Message_.read), false),
			cb.equal(msg.get(Message_.recipient).get(User_.id), recipientId)
		));
		cq.select(cb.count(msg));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

}
