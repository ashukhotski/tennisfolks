package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserFollower
 *
 */
@Entity
@Table(name = "user_follower")
public class UserFollower implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "follower")
	private User follower;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "following")
	private User following;
	
	private Date subscriptionDate;

	public UserFollower() {
		super();
	}

	public UserFollower(int id, User follower, User following, Date subscriptionDate) {
		super();
		this.id = id;
		this.follower = follower;
		this.following = following;
		this.subscriptionDate = subscriptionDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	
	@PrePersist
	public void onCreate() {
		this.subscriptionDate = new Date();
	}
   
}
