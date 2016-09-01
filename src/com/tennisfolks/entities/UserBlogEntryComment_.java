package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-29T12:57:29.681-0500")
@StaticMetamodel(UserBlogEntryComment.class)
public class UserBlogEntryComment_ {
	public static volatile SingularAttribute<UserBlogEntryComment, Integer> id;
	public static volatile SingularAttribute<UserBlogEntryComment, Date> creationDate;
	public static volatile SingularAttribute<UserBlogEntryComment, String> text;
	public static volatile SingularAttribute<UserBlogEntryComment, User> author;
	public static volatile SingularAttribute<UserBlogEntryComment, UserBlogEntry> entry;
	public static volatile SingularAttribute<UserBlogEntryComment, Boolean> deleted;
}
