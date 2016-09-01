package com.tennisfolks.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserBlogEntryComment
 *
 */
@Entity
@Table(name = "user_blog_entry_comment")
public class UserBlogEntryComment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date creationDate;
	
	@Transient
	private String formattedDate;
	
	@Transient
	private int dateDay;
	
	@Transient
	private int dateMonth;
	
	@Transient
	private int dateYear;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private User author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entry")
	private UserBlogEntry entry;
	
	private boolean deleted = false;

	public UserBlogEntryComment() {
		super();
	}

	public UserBlogEntryComment(int id, Date creationDate, String text, User author, UserBlogEntry entry,
			boolean deleted) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.text = text;
		this.author = author;
		this.entry = entry;
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

	public UserBlogEntry getEntry() {
		return entry;
	}

	public void setEntry(UserBlogEntry entry) {
		this.entry = entry;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
   
}
