package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T07:17:05.309-0500")
@StaticMetamodel(TournamentMatchPhoto.class)
public class TournamentMatchPhoto_ {
	public static volatile SingularAttribute<TournamentMatchPhoto, Integer> id;
	public static volatile SingularAttribute<TournamentMatchPhoto, String> url;
	public static volatile SingularAttribute<TournamentMatchPhoto, TournamentMatch> match;
	public static volatile SingularAttribute<TournamentMatchPhoto, Tournament> tournament;
	public static volatile SingularAttribute<TournamentMatchPhoto, League> league;
	public static volatile SingularAttribute<TournamentMatchPhoto, Date> uploadDate;
	public static volatile SingularAttribute<TournamentMatchPhoto, String> description;
	public static volatile SingularAttribute<TournamentMatchPhoto, Boolean> deleted;
	public static volatile SingularAttribute<TournamentMatchPhoto, User> author;
}
