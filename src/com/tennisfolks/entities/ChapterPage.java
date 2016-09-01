package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ChapterPage
 *
 */
@Entity 
@Table(name = "chapter_page", uniqueConstraints = @UniqueConstraint(columnNames = { "language", "url" }))
public class ChapterPage implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language")
	private Language language;
	
	private String header;
	
	private String text;
	
	private String title;
	
	private String keywords;
	
	private String description;
	
	private boolean published = false;
	
	private boolean deleted = false;
	
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator")
	private User creator;
	
	private Date lastModified;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "editor")
	private User editor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chapter")
	private Chapter chapter;

	public ChapterPage() {
		super();
	}

	public ChapterPage(int id, String url, Language language, String header, String text, String title,
			String keywords, String description, boolean published, boolean deleted, Date creationDate, User creator,
			Date lastModified, User editor, Chapter chapter) {
		super();
		this.id = id;
		this.url = url;
		this.language = language;
		this.header = header;
		this.text = text;
		this.title = title;
		this.keywords = keywords;
		this.description = description;
		this.published = published;
		this.deleted = deleted;
		this.creationDate = creationDate;
		this.creator = creator;
		this.lastModified = lastModified;
		this.editor = editor;
		this.chapter = chapter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}
	
	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	@PrePersist
	public void onCreate() {
		this.creationDate = new Date();
	}
	
	@PostPersist
	public void onUpdate() {
		this.lastModified = new Date();
	}
   
}
