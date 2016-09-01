package com.tennisfolks.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserBlog
 *
 */
@Entity
@Table(name = "user_blog_entry")
public class UserBlogEntry implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private User author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	private Date creationDate;
	
	@Transient
	private String formattedDate;
	
	@Transient
	private int dateDay;
	
	@Transient
	private int dateMonth;
	
	@Transient
	private int dateYear;
	
	private boolean published = true;
	
	private boolean deleted = false;
	
	@Transient
	private List<UserBlogEntryComment> comments = new ArrayList<UserBlogEntryComment>();

	@Transient
	private int currentPageForComments = 1;
	
	@Transient
	private int nextPageForComments = 1;
	
	public UserBlogEntry() {
		super();
	}

	public UserBlogEntry(int id, String text, User author, User user, Date creationDate, boolean published,
			boolean deleted) {
		super();
		this.id = id;
		this.text = text;
		this.author = author;
		this.user = user;
		this.creationDate = creationDate;
		this.published = published;
		this.deleted = deleted;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (creationDate != null) {
			formattedDate = (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(creationDate);
			
			Calendar creation = Calendar.getInstance();
			creation.setTime(creationDate);
			dateDay = creation.get(Calendar.DATE);
			dateMonth = creation.get(Calendar.MONTH) + 1;
			dateYear = creation.get(Calendar.YEAR);
		} else { formattedDate = ""; }
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public int getDateDay() {
		return dateDay;
	}

	public void setDateDay(int dateDay) {
		this.dateDay = dateDay;
	}

	public int getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(int dateMonth) {
		this.dateMonth = dateMonth;
	}

	public int getDateYear() {
		return dateYear;
	}

	public void setDateYear(int dateYear) {
		this.dateYear = dateYear;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public List<UserBlogEntryComment> getComments() {
		return comments;
	}

	public void setComments(List<UserBlogEntryComment> comments) {
		this.comments = comments;
	}

	public int getCurrentPageForComments() {
		return currentPageForComments;
	}

	public void setCurrentPageForComments(int currentPageForComments) {
		this.currentPageForComments = currentPageForComments;
	}

	public int getNextPageForComments() {
		return nextPageForComments;
	}

	public void setNextPageForComments(int nextPageForComments) {
		this.nextPageForComments = nextPageForComments;
	}

	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
   
}
