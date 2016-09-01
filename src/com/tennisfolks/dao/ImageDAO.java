package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.Image;

@Remote
public interface ImageDAO {
	
	public Image create(Image image);
	
	public Image update(Image image);
	
	public void delete(Image image);
	
	public Image find(int id);
	
	public List<Image> find(Map<String, Object> params, int startPosition, int maxResults);

}
