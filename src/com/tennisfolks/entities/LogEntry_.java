package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-05-22T07:17:05.309-0500")
@StaticMetamodel(LogEntry.class)
public class LogEntry_ {
	public static volatile SingularAttribute<LogEntry, Integer> id;
	public static volatile SingularAttribute<LogEntry, Date> activityDate;
	public static volatile SingularAttribute<LogEntry, User> user;
	public static volatile SingularAttribute<LogEntry, String> notes;
}
