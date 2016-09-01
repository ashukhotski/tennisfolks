package com.tennisfolks.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: League
 *
 */
@Entity
@Table(name = "league")
public class League implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	//@Column(unique = true)
	//private String subdomain;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "logoId")
	private Image logo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coverPhotoId")
	private Image coverPhoto;
	
	private String country;
	
	@Transient
	private String state;
	
	@Transient
	private String city;
	
	@Transient
	private int cityGeonameId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId")
	private GeonamesAdmin geonamesAdmin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId")
	private GeonamesCity geonamesCity;
	
	private boolean open = true;
	
	private float membershipFee = 0;
	
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator")
	private User creator;
	
	@Transient
	private int creatorId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager")
	private User manager;
	
	@Transient
	private int managerId;
	
	private float balance = 0;
	
	private boolean deleted = false;

	public League() {
		super();
	}

	public League(int id, String name, String description, Image logo, Image coverPhoto,
			String country, GeonamesAdmin geonamesAdmin, GeonamesCity geonamesCity, boolean open, float membershipFee,
			Date creationDate, User creator, User manager, float balance, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.logo = logo;
		this.coverPhoto = coverPhoto;
		this.country = country;
		this.geonamesAdmin = geonamesAdmin;
		this.geonamesCity = geonamesCity;
		this.open = open;
		this.membershipFee = membershipFee;
		this.creationDate = creationDate;
		this.creator = creator;
		this.manager = manager;
		this.balance = balance;
		this.deleted = deleted;
	}
	
	@PostLoad
	private void initTransientFields() {
		if (creator != null) creatorId = creator.getId();
		if (manager != null) managerId = manager.getId();
		if (geonamesAdmin != null) state = geonamesAdmin.getAdminCode();
		if (geonamesCity != null) {
			city = geonamesCity.getAsciiname();
			cityGeonameId = geonamesCity.getGeonameId();
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public Image getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(Image coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityGeonameId() {
		return cityGeonameId;
	}

	public void setCityGeonameId(int cityGeonameId) {
		this.cityGeonameId = cityGeonameId;
	}

	public GeonamesAdmin getGeonamesAdmin() {
		return geonamesAdmin;
	}

	public void setGeonamesAdmin(GeonamesAdmin geonamesAdmin) {
		this.geonamesAdmin = geonamesAdmin;
	}

	public GeonamesCity getGeonamesCity() {
		return geonamesCity;
	}

	public void setGeonamesCity(GeonamesCity geonamesCity) {
		this.geonamesCity = geonamesCity;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public float getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(float membershipFee) {
		this.membershipFee = membershipFee;
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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
	}
   
}
