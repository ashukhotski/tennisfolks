package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-02T07:37:26.617-0500")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, Date> birthday;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> country;
	public static volatile SingularAttribute<User, String> city;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, Float> latitude;
	public static volatile SingularAttribute<User, Float> longitude;
	public static volatile SingularAttribute<User, GeonamesAdmin> geonamesAdmin;
	public static volatile SingularAttribute<User, GeonamesCity> geonamesCity;
	public static volatile SingularAttribute<User, Location> location;
	public static volatile SingularAttribute<User, Integer> level;
	public static volatile SingularAttribute<User, Integer> levelShift;
	public static volatile SingularAttribute<User, String> description;
	public static volatile SingularAttribute<User, Image> photo;
	public static volatile SingularAttribute<User, Date> lastActivityDate;
	public static volatile SingularAttribute<User, Date> registrationDate;
	public static volatile SingularAttribute<User, Language> language;
	public static volatile SingularAttribute<User, Boolean> deleted;
}
