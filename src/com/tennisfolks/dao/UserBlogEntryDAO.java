package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Remote;

import com.tennisfolks.entities.UserBlogEntry;
import com.tennisfolks.entities.UserBlogEntryComment;

@Remote
public interface UserBlogEntryDAO {

	public UserBlogEntry create(UserBlogEntry entry);
	
	public UserBlogEntry update(UserBlogEntry entry);
	
	public UserBlogEntry find(int id);
	
	public List<UserBlogEntry> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public int count(Map<String, Object> params);
	
	public UserBlogEntryComment createComment(UserBlogEntryComment comment);
	
	public UserBlogEntryComment updateComment(UserBlogEntryComment comment);
	
	public UserBlogEntryComment findComment(int id);
	
	public List<UserBlogEntryComment> findComments(Map<String, Object> params, int startPosition, int maxResults);
	
	public int countComments(Map<String, Object> params);

}
