package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tennisfolks.entities.Image;

@Stateless
public class ImageDAOImpl implements ImageDAO {
	
	@PersistenceContext(unitName = "tennisfolks")
	EntityManager em;

	@Override
	public Image create(Image image) {
		em.persist(image);
		em.flush();
		em.refresh(image);
		return image;
	}

	@Override
	public Image update(Image image) {
		em.merge(image);
		return find(image.getId());
	}

	@Override
	public void delete(Image image) {
		em.remove(em.contains(image) ? image : em.merge(image));
	}

	@Override
	public Image find(int id) {
		return em.find(Image.class, id);
	}

	@Override
	public List<Image> find(Map<String, Object> params, int startPosition, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}
