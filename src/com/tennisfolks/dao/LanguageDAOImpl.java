package com.tennisfolks.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.tennisfolks.entities.Language;
import com.tennisfolks.entities.Language_;

@Stateless
public class LanguageDAOImpl implements LanguageDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;
	
	public LanguageDAOImpl() { }
	
	@Override
	public Language findByCode(String code) {
		return em.find(Language.class, code);
	}
	
	@Override
	public List<Language> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Language> cq = cb.createQuery(Language.class);
		Root<Language> language = cq.from(Language.class);
		cq.select(language);
		cq.orderBy(cb.asc(language.get(Language_.name)));
		TypedQuery<Language> q = em.createQuery(cq);
		return q.getResultList();
	}

}
