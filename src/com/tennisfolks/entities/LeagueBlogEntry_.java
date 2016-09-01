package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T07:17:05.293-0500")
@StaticMetamodel(LeagueBlogEntry.class)
public class LeagueBlogEntry_ {
	public static volatile SingularAttribute<LeagueBlogEntry, Integer> id;
	public static volatile SingularAttribute<LeagueBlogEntry, Date> creationDate;
	public static volatile SingularAttribute<LeagueBlogEntry, String> header;
	public static volatile SingularAttribute<LeagueBlogEntry, String> text;
	public static volatile SingularAttribute<LeagueBlogEntry, League> league;
	public static volatile SingularAttribute<LeagueBlogEntry, User> author;
	public static volatile SingularAttribute<LeagueBlogEntry, Boolean> published;
	public static volatile SingularAttribute<LeagueBlogEntry, Boolean> deleted;
}
