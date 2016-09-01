package com.tennisfolks.dao;

import java.util.*;
import javax.ejb.Remote;

import com.tennisfolks.entities.Message;

@Remote
public interface MessageDAO {
	
	public Message create(Message message);
	
	public Message update(Message message);
	
	public Message find(int id);
	
	public List<Message> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public int count(Map<String, Object> params);
	
	public List<Message> getDialogues(int userId, int startPosition, int maxResults);
	
	public int countDialogues(int userId);
	
	public List<Message> getMessages(String dialogueId, int startPosition, int maxResults);
	
	public int countMessages(String dialogueId);

	public int countNewMessages(int recipientId);

}
