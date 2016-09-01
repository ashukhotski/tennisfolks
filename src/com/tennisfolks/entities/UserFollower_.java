package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-28T07:05:51.050-0500")
@StaticMetamodel(UserFollower.class)
public class UserFollower_ {
	public static volatile SingularAttribute<UserFollower, Integer> id;
	public static volatile SingularAttribute<UserFollower, User> follower;
	public static volatile SingularAttribute<UserFollower, User> following;
	public static volatile SingularAttribute<UserFollower, Date> subscriptionDate;
}
