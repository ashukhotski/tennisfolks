package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-05T09:16:26.900-0500")
@StaticMetamodel(Tournament.class)
public class Tournament_ {
	public static volatile SingularAttribute<Tournament, Integer> id;
	public static volatile SingularAttribute<Tournament, String> name;
	public static volatile SingularAttribute<Tournament, String> description;
	public static volatile SingularAttribute<Tournament, Integer> targetLevel;
	public static volatile SingularAttribute<Tournament, Integer> levelShift;
	public static volatile SingularAttribute<Tournament, Integer> numberOfPlayers;
	public static volatile SingularAttribute<Tournament, League> league;
	public static volatile SingularAttribute<Tournament, Date> startDate;
	public static volatile SingularAttribute<Tournament, Date> endDate;
	public static volatile SingularAttribute<Tournament, Boolean> finished;
	public static volatile SingularAttribute<Tournament, User> supervisor;
	public static volatile SingularAttribute<Tournament, String> jsonData;
	public static volatile SingularAttribute<Tournament, Boolean> deleted;
}
