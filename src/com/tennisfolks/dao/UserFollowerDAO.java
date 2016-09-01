package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.User;
import com.tennisfolks.entities.UserFollower;

@Remote
public interface UserFollowerDAO {
	
	public UserFollower create(UserFollower uf);
	
	public void delete(UserFollower uf);
	
	public void delete(int followerId, int followingId);
	
	public UserFollower find(int id);
	
	public UserFollower find(int followerId, int followingId);
	
	public List<User> findFollowers(int id, int startPosition, int maxResults);
	
	public int countFollowers(int id);
	
	public List<User> findFollowing(int id, int startPosition, int maxResults);
	
	public int countFollowing(int id);
	
	public boolean checkIfFollowing(int followerId, int followingId);

}
