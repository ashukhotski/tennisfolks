package com.tennisfolks.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-06-22T13:43:37.141-0500")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Integer> id;
	public static volatile SingularAttribute<Message, String> body;
	public static volatile SingularAttribute<Message, Date> creationDate;
	public static volatile SingularAttribute<Message, User> sender;
	public static volatile SingularAttribute<Message, User> recipient;
	public static volatile SingularAttribute<Message, Boolean> read;
	public static volatile SingularAttribute<Message, Boolean> deleted;
	public static volatile SingularAttribute<Message, String> dialogueId;
}
