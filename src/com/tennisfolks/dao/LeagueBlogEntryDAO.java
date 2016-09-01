package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Remote;

import com.tennisfolks.entities.LeagueBlogEntry;
import com.tennisfolks.entities.LeagueBlogEntryComment;

@Remote
public interface LeagueBlogEntryDAO {

	public LeagueBlogEntry create(LeagueBlogEntry entry);
	
	public LeagueBlogEntry update(LeagueBlogEntry entry);
	
	public LeagueBlogEntry find(int id);
	
	public List<LeagueBlogEntry> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public int count(Map<String, Object> params);
	
	public LeagueBlogEntryComment createComment(LeagueBlogEntryComment comment);
	
	public LeagueBlogEntryComment updateComment(LeagueBlogEntryComment comment);
	
	public LeagueBlogEntryComment findComment(int id);
	
	public List<LeagueBlogEntryComment> findComments(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countComments(Map<String, Object> params);

}
