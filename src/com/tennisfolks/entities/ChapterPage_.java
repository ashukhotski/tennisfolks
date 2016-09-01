package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T14:00:49.884-0500")
@StaticMetamodel(ChapterPage.class)
public class ChapterPage_ {
	public static volatile SingularAttribute<ChapterPage, Integer> id;
	public static volatile SingularAttribute<ChapterPage, String> url;
	public static volatile SingularAttribute<ChapterPage, Language> language;
	public static volatile SingularAttribute<ChapterPage, String> header;
	public static volatile SingularAttribute<ChapterPage, String> text;
	public static volatile SingularAttribute<ChapterPage, String> title;
	public static volatile SingularAttribute<ChapterPage, String> keywords;
	public static volatile SingularAttribute<ChapterPage, String> description;
	public static volatile SingularAttribute<ChapterPage, Boolean> published;
	public static volatile SingularAttribute<ChapterPage, Boolean> deleted;
	public static volatile SingularAttribute<ChapterPage, Date> creationDate;
	public static volatile SingularAttribute<ChapterPage, User> creator;
	public static volatile SingularAttribute<ChapterPage, Date> lastModified;
	public static volatile SingularAttribute<ChapterPage, User> editor;
	public static volatile SingularAttribute<ChapterPage, Chapter> chapter;
}
