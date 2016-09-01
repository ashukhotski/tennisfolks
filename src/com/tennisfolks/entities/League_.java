package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-07T12:41:43.683-0500")
@StaticMetamodel(League.class)
public class League_ {
	public static volatile SingularAttribute<League, Integer> id;
	public static volatile SingularAttribute<League, String> name;
	public static volatile SingularAttribute<League, String> description;
	public static volatile SingularAttribute<League, Image> logo;
	public static volatile SingularAttribute<League, Image> coverPhoto;
	public static volatile SingularAttribute<League, String> country;
	public static volatile SingularAttribute<League, GeonamesAdmin> geonamesAdmin;
	public static volatile SingularAttribute<League, GeonamesCity> geonamesCity;
	public static volatile SingularAttribute<League, Boolean> open;
	public static volatile SingularAttribute<League, Float> membershipFee;
	public static volatile SingularAttribute<League, Date> creationDate;
	public static volatile SingularAttribute<League, User> creator;
	public static volatile SingularAttribute<League, User> manager;
	public static volatile SingularAttribute<League, Float> balance;
	public static volatile SingularAttribute<League, Boolean> deleted;
}
