package com.tennisfolks.entities;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

import com.google.appengine.repackaged.org.apache.commons.codec.binary.Base64;

/**
 * Entity implementation class for Entity: Image
 *
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(length = 10485760)
	private byte[] content;
	
	@Transient
	private String contentBase64Encoded;
	
	private String mimetype;
	
	private Date creationDate;
	
	private int userId;

	public Image() {
		super();
	}

	public Image(int id, String name, byte[] content, String mimetype, Date creationDate, int userId) {
		super();
		this.id = id;
		this.setName(name);
		this.content = content;
		this.mimetype = mimetype;
		this.creationDate = creationDate;
		this.setUserId(userId);
	}
	
	@PostLoad
	private void initTransientFields() {
		if (content != null) contentBase64Encoded = Base64.encodeBase64String(content);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentBase64Encoded() {
		return contentBase64Encoded;
	}

	public void setContentBase64Encoded(String contentBase64Encoded) {
		this.contentBase64Encoded = contentBase64Encoded;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
	}
   
}
