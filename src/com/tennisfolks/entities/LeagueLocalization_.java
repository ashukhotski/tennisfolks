package com.tennisfolks.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T07:17:05.293-0500")
@StaticMetamodel(LeagueLocalization.class)
public class LeagueLocalization_ {
	public static volatile SingularAttribute<LeagueLocalization, String> id;
	public static volatile SingularAttribute<LeagueLocalization, League> league;
	public static volatile SingularAttribute<LeagueLocalization, Language> language;
	public static volatile SingularAttribute<LeagueLocalization, String> name;
	public static volatile SingularAttribute<LeagueLocalization, String> description;
}
