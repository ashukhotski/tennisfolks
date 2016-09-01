package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T07:17:05.293-0500")
@StaticMetamodel(LeagueBlogEntryComment.class)
public class LeagueBlogEntryComment_ {
	public static volatile SingularAttribute<LeagueBlogEntryComment, Integer> id;
	public static volatile SingularAttribute<LeagueBlogEntryComment, Date> creationDate;
	public static volatile SingularAttribute<LeagueBlogEntryComment, String> text;
	public static volatile SingularAttribute<LeagueBlogEntryComment, User> author;
	public static volatile SingularAttribute<LeagueBlogEntryComment, LeagueBlogEntry> entry;
	public static volatile SingularAttribute<LeagueBlogEntryComment, Boolean> deleted;
}
