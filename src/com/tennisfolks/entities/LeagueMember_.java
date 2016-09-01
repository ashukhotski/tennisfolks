package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-02T07:36:20.224-0500")
@StaticMetamodel(LeagueMember.class)
public class LeagueMember_ {
	public static volatile SingularAttribute<LeagueMember, String> id;
	public static volatile SingularAttribute<LeagueMember, League> league;
	public static volatile SingularAttribute<LeagueMember, User> user;
	public static volatile SingularAttribute<LeagueMember, Boolean> accepted;
	public static volatile SingularAttribute<LeagueMember, Boolean> rejected;
	public static volatile SingularAttribute<LeagueMember, Boolean> deleted;
	public static volatile SingularAttribute<LeagueMember, Date> paymentDate;
	public static volatile SingularAttribute<LeagueMember, Date> expirationDate;
	public static volatile SingularAttribute<LeagueMember, Integer> tournamentPoints;
	public static volatile SingularAttribute<LeagueMember, Integer> challengePoints;
}
