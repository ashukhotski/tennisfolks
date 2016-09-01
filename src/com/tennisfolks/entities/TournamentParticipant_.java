package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-10-05T09:16:26.971-0500")
@StaticMetamodel(TournamentParticipant.class)
public class TournamentParticipant_ {
	public static volatile SingularAttribute<TournamentParticipant, String> id;
	public static volatile SingularAttribute<TournamentParticipant, Tournament> tournament;
	public static volatile SingularAttribute<TournamentParticipant, User> user;
	public static volatile SingularAttribute<TournamentParticipant, Boolean> confirmed;
	public static volatile SingularAttribute<TournamentParticipant, Date> registrationDate;
	public static volatile SingularAttribute<TournamentParticipant, Integer> top;
}
