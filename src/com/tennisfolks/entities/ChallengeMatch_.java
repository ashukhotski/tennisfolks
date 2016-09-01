package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-14T07:38:20.396-0500")
@StaticMetamodel(ChallengeMatch.class)
public class ChallengeMatch_ {
	public static volatile SingularAttribute<ChallengeMatch, Integer> id;
	public static volatile SingularAttribute<ChallengeMatch, User> firstPlayer;
	public static volatile SingularAttribute<ChallengeMatch, User> secondPlayer;
	public static volatile SingularAttribute<ChallengeMatch, Integer> firstPlayerScore;
	public static volatile SingularAttribute<ChallengeMatch, Integer> secondPlayerScore;
	public static volatile SingularAttribute<ChallengeMatch, String> detailedScore;
	public static volatile SingularAttribute<ChallengeMatch, String> matchReview;
	public static volatile SingularAttribute<ChallengeMatch, String> surfaceType;
	public static volatile SingularAttribute<ChallengeMatch, Boolean> accepted;
	public static volatile SingularAttribute<ChallengeMatch, Boolean> rejected;
	public static volatile SingularAttribute<ChallengeMatch, Boolean> finished;
	public static volatile SingularAttribute<ChallengeMatch, Date> startDate;
	public static volatile SingularAttribute<ChallengeMatch, Date> endDate;
	public static volatile SingularAttribute<ChallengeMatch, Location> location;
	public static volatile SingularAttribute<ChallengeMatch, Boolean> deleted;
	public static volatile SingularAttribute<ChallengeMatch, String> description;
}
