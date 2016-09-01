package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String body;
	
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender")
	private User sender;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient")
	private User recipient;
	
	@Transient
	private int senderId;
	
	@Transient
	private int recipientId;
	
	@Column(name = "\"read\"") 
	private boolean read = false;
	
	private boolean deleted = false;
	
	private String dialogueId;

	public Message() {
		super();
	}

	public Message(int id, String body, Date creationDate, User sender, User recipient, boolean read, boolean deleted,
			String dialogueId) {
		super();
		this.id = id;
		this.body = body;
		this.creationDate = creationDate;
		this.sender = sender;
		this.recipient = recipient;
		this.read = read;
		this.deleted = deleted;
		this.dialogueId = dialogueId;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (recipient != null) this.recipientId = recipient.getId();
		if (sender != null) this.senderId = sender.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(String dialogueId) {
		this.dialogueId = dialogueId;
	}
	
	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
   
}
