package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.tennisfolks.entities.User;
import com.tennisfolks.entities.UserFollower;
import com.tennisfolks.entities.UserFollower_;
import com.tennisfolks.entities.User_;

@Stateless
public class UserFollowerDAOImpl implements UserFollowerDAO {

	@PersistenceContext(unitName = "tennisfolks"/*, type = PersistenceContextType.EXTENDED*/)
	EntityManager em;
	
	UserFollowerDAOImpl() { }

	@Override
	public UserFollower create(UserFollower uf) {
		em.persist(uf);
		em.flush();
		em.refresh(uf);
		return find(uf.getId());
	}

	@Override
	public void delete(UserFollower uf) {
		em.remove(uf);
		em.flush();
	}

	@Override
	public void delete(int followerId, int followingId) {
		UserFollower uf = find(followerId, followingId);
		if (uf != null) {
			em.remove(uf);
			em.flush();
		}
	}
	
	@Override
	public UserFollower find(int id) {
		return em.find(UserFollower.class, id);
	}

	@Override
	public UserFollower find(int followerId, int followingId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserFollower> cq = cb.createQuery(UserFollower.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.and(
			cb.equal(uf.get(UserFollower_.follower).get(User_.id), followerId),
			cb.equal(uf.get(UserFollower_.following).get(User_.id), followingId)
		));
		cq.select(uf);
		TypedQuery<UserFollower> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<User> findFollowers(int id, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.equal(uf.get(UserFollower_.following).get(User_.id), id));
		cq.select(uf.get(UserFollower_.follower));
		cq.orderBy(cb.desc(uf.get(UserFollower_.subscriptionDate)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countFollowers(int id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.equal(uf.get(UserFollower_.following).get(User_.id), id));
		cq.select(cb.count(uf));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public List<User> findFollowing(int id, int startPosition, int maxResults) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.equal(uf.get(UserFollower_.follower).get(User_.id), id));
		cq.select(uf.get(UserFollower_.following));
		cq.orderBy(cb.desc(uf.get(UserFollower_.subscriptionDate)));
		TypedQuery<User> q = em.createQuery(cq).setFirstResult(startPosition).setMaxResults(maxResults);
		return q.getResultList();
	}
	
	@Override
	public int countFollowing(int id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.equal(uf.get(UserFollower_.follower).get(User_.id), id));
		cq.select(cb.count(uf));
		return Integer.valueOf(em.createQuery(cq).getSingleResult().toString());
	}

	@Override
	public boolean checkIfFollowing(int followerId, int followingId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserFollower> uf = cq.from(UserFollower.class);
		cq.where(cb.and(
			cb.equal(uf.get(UserFollower_.follower).get(User_.id), followerId),
			cb.equal(uf.get(UserFollower_.following).get(User_.id), followingId)
		));
		cq.select(cb.count(uf));
		return (em.createQuery(cq).getSingleResult() > 0) ? true : false;
	}

}
