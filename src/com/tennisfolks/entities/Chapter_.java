package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T13:59:39.869-0500")
@StaticMetamodel(Chapter.class)
public class Chapter_ {
	public static volatile SingularAttribute<Chapter, Integer> id;
	public static volatile SingularAttribute<Chapter, String> name;
	public static volatile SingularAttribute<Chapter, Language> language;
	public static volatile SingularAttribute<Chapter, String> url;
	public static volatile SingularAttribute<Chapter, String> title;
	public static volatile SingularAttribute<Chapter, String> keywords;
	public static volatile SingularAttribute<Chapter, String> description;
	public static volatile SingularAttribute<Chapter, Boolean> published;
	public static volatile SingularAttribute<Chapter, Boolean> deleted;
	public static volatile SingularAttribute<Chapter, Date> creationDate;
	public static volatile SingularAttribute<Chapter, User> creator;
	public static volatile SingularAttribute<Chapter, Date> lastModified;
	public static volatile SingularAttribute<Chapter, User> editor;
}
