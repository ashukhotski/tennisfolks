package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 * Entity implementation class for Entity: User
 *
 */ 
@Entity
@Table(name = "user")
public class User implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String email;
	
	@Basic(fetch = FetchType.LAZY)
	private String password;
	
	@Transient
	private String currentPassword;
	
	@Transient
	private String newPassword;
	
	private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	private Date birthday;
	
	@Transient
	private int bDay;
	
	@Transient
	private int bMonth;
	
	@Transient
	private int bYear;
	
	@Transient
	private int age;
	
	private String gender;
	
	private String phone;
	
	private String country;
	
	@Transient
	private String state;
	
	private String city;
	
	@Transient
	private int cityGeonameId;
	
	private String address;
	
	@Column(columnDefinition = "Float(10,6)")
	private float latitude;
	
	@Column(columnDefinition = "Float(10,6)")
	private float longitude;
	
	@Transient
	private String geolocation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId")
	private GeonamesAdmin geonamesAdmin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId")
	private GeonamesCity geonamesCity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location") 
	private Location location = null;
	
	private int level;
	
	private int levelShift;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "photoId")
	private Image photo;
	
	private Date lastActivityDate;
	
	@Transient
	private boolean online;
	
	private Date registrationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "language")
	private Language language;
	
	private boolean deleted = false;

	public User() {
		super();
	}
	
	public User(int id, String email, String password, String firstName, String lastName, String fullName, Date birthday, String gender,
			String phone, String country, String address, float latitude, float longitude, 
			GeonamesAdmin geonamesAdmin, GeonamesCity geonamesCity, Location location, int level, int levelShift, String description, Image photo, 
			Date lastActivityDate, Date registrationDate, Language language, boolean deleted) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.country = country;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.geonamesAdmin = geonamesAdmin;
		this.geonamesCity = geonamesCity;
		this.location = location;
		this.level = level;
		this.levelShift = levelShift;
		this.description = description;
		this.photo = photo;
		this.lastActivityDate = lastActivityDate;
		this.registrationDate = registrationDate;
		this.language = language;
		this.deleted = deleted;
	}
	
	@PostLoad
	private void initTransientFields() {
		//for age
		if (birthday == null) { 
			age = 0; 
		} else {
			Calendar now = Calendar.getInstance();
			Calendar dateOfBirth = Calendar.getInstance();
			dateOfBirth.setTime(birthday);
			bDay = dateOfBirth.get(Calendar.DATE);
			bMonth = dateOfBirth.get(Calendar.MONTH) + 1;
			bYear = dateOfBirth.get(Calendar.YEAR);
			if (now.get(Calendar.YEAR) <= dateOfBirth.get(Calendar.YEAR)) { 
				age = 0; 
			} else {
				age = now.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
				if (now.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)) {
					age--;
				} else if ((now.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH)) && (now.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH))) {
					age--;
				} else { }
			}
		}
		//for online
		if (lastActivityDate == null) { 
			online = false; 
		} else {
			long currentTime = new Date().getTime();
			long lastActivityTime = this.lastActivityDate.getTime();
			online = (currentTime - lastActivityTime <= 300000) ? true : false;
		}
		//for geolocation 
		geolocation = latitude + "," + longitude;
		//for cityGeonameId
		if (geonamesCity != null) cityGeonameId = geonamesCity.getGeonameId();
		//for state
		if (geonamesAdmin != null) state = geonamesAdmin.getAdminCode();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getbDay() {
		return bDay;
	}

	public void setbDay(int bDay) {
		this.bDay = bDay;
	}

	public int getbMonth() {
		return bMonth;
	}

	public void setbMonth(int bMonth) {
		this.bMonth = bMonth;
	}

	public int getbYear() {
		return bYear;
	}

	public void setbYear(int bYear) {
		this.bYear = bYear;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevelShift() {
		return levelShift;
	}

	public void setLevelShift(int levelShift) {
		this.levelShift = levelShift;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@PrePersist
	protected void onCreate() {
		this.registrationDate = new Date();
	}
	
	@PostPersist @PreUpdate
	protected void onUpdate() {
		this.lastActivityDate = new Date();
	}
	 
}
