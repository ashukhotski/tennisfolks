package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-02T08:26:07.564-0500")
@StaticMetamodel(UserBlogEntry.class)
public class UserBlogEntry_ {
	public static volatile SingularAttribute<UserBlogEntry, Integer> id;
	public static volatile SingularAttribute<UserBlogEntry, String> text;
	public static volatile SingularAttribute<UserBlogEntry, User> author;
	public static volatile SingularAttribute<UserBlogEntry, User> user;
	public static volatile SingularAttribute<UserBlogEntry, Date> creationDate;
	public static volatile SingularAttribute<UserBlogEntry, Boolean> published;
	public static volatile SingularAttribute<UserBlogEntry, Boolean> deleted;
}
