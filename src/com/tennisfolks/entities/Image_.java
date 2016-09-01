package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-26T14:46:47.044-0500")
@StaticMetamodel(Image.class)
public class Image_ {
	public static volatile SingularAttribute<Image, Integer> id;
	public static volatile SingularAttribute<Image, String> name;
	public static volatile SingularAttribute<Image, byte[]> content;
	public static volatile SingularAttribute<Image, String> mimetype;
	public static volatile SingularAttribute<Image, Date> creationDate;
	public static volatile SingularAttribute<Image, Integer> userId;
}
