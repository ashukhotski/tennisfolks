package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-23T09:29:36.856-0500")
@StaticMetamodel(OpenChallenge.class)
public class OpenChallenge_ {
	public static volatile SingularAttribute<OpenChallenge, Integer> id;
	public static volatile SingularAttribute<OpenChallenge, Integer> matchId;
	public static volatile SingularAttribute<OpenChallenge, User> firstPlayer;
	public static volatile SingularAttribute<OpenChallenge, Boolean> accepted;
	public static volatile SingularAttribute<OpenChallenge, Boolean> deleted;
	public static volatile SingularAttribute<OpenChallenge, Date> startDate;
	public static volatile SingularAttribute<OpenChallenge, Location> location;
	public static volatile SingularAttribute<OpenChallenge, Date> creationDate;
	public static volatile SingularAttribute<OpenChallenge, String> description;
	public static volatile SingularAttribute<OpenChallenge, String> surfaceType;
}
