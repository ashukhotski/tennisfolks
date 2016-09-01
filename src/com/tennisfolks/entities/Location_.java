package com.tennisfolks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-04T08:02:43.861-0500")
@StaticMetamodel(Location.class)
public class Location_ {
	public static volatile SingularAttribute<Location, Integer> id;
	public static volatile SingularAttribute<Location, String> country;
	public static volatile SingularAttribute<Location, String> state;
	public static volatile SingularAttribute<Location, GeonamesAdmin> geonamesAdmin;
	public static volatile SingularAttribute<Location, GeonamesCity> geonamesCity;
	public static volatile SingularAttribute<Location, String> city;
	public static volatile SingularAttribute<Location, String> address;
	public static volatile SingularAttribute<Location, String> fullAddress;
	public static volatile SingularAttribute<Location, String> formattedAddress;
	public static volatile SingularAttribute<Location, Float> latitude;
	public static volatile SingularAttribute<Location, Float> longitude;
}
