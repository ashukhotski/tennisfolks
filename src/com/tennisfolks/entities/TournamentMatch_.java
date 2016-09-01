package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-15T12:53:20.312-0500")
@StaticMetamodel(TournamentMatch.class)
public class TournamentMatch_ {
	public static volatile SingularAttribute<TournamentMatch, Integer> id;
	public static volatile SingularAttribute<TournamentMatch, Tournament> tournament;
	public static volatile SingularAttribute<TournamentMatch, User> firstPlayer;
	public static volatile SingularAttribute<TournamentMatch, User> secondPlayer;
	public static volatile SingularAttribute<TournamentMatch, Integer> firstPlayerScore;
	public static volatile SingularAttribute<TournamentMatch, Integer> secondPlayerScore;
	public static volatile SingularAttribute<TournamentMatch, String> detailedScore;
	public static volatile SingularAttribute<TournamentMatch, String> matchReview;
	public static volatile SingularAttribute<TournamentMatch, Integer> stage;
	public static volatile SingularAttribute<TournamentMatch, Date> startDate;
	public static volatile SingularAttribute<TournamentMatch, Date> endDate;
	public static volatile SingularAttribute<TournamentMatch, Boolean> finished;
	public static volatile SingularAttribute<TournamentMatch, Boolean> approved;
	public static volatile SingularAttribute<TournamentMatch, Boolean> deleted;
	public static volatile SingularAttribute<TournamentMatch, String> surfaceType;
	public static volatile SingularAttribute<TournamentMatch, String> description;
	public static volatile SingularAttribute<TournamentMatch, Location> location;
}
