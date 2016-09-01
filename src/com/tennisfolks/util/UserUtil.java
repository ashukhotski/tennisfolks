package com.tennisfolks.util;

import java.util.Date;

public class UserUtil {
	
	private int id;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	private String gender;
	
	private String phone;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String address;
	
	private float latitude;
	
	private float longitude;
	
	private int level;
	
	private int levelShift;
	
	private String description;
	
	private String photoId;
	
	private String facebookId;
	
	private String googleId;
	
	private String languageCode;
	
	private float balance = 0;

	public UserUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserUtil(int id, String email, String password, String firstName, String lastName, Date birthday,
			String gender, String phone, String country, String state, String city, String address, float latitude,
			float longitude, int level, int levelShift, String description, String photoId, String facebookId,
			String googleId, String languageCode, float balance) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.level = level;
		this.levelShift = levelShift;
		this.description = description;
		this.photoId = photoId;
		this.facebookId = facebookId;
		this.googleId = googleId;
		this.languageCode = languageCode;
		this.balance = balance;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
