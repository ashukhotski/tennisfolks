package com.tennisfolks.dao;

import java.util.*;

import javax.ejb.Remote;

import com.tennisfolks.entities.Language;
import com.tennisfolks.entities.User;

@Remote
public interface UserDAO {

	public User create(User user);
	
	public User create(String email, String password, String firstName, String lastName, Language language);
	
	/*
	public User updateAllData(int id, String email, String password, 
			String firstName, String lastName, String birthday, 
			String gender, String phone, String country, String state, 
			String city, String address, float latitude, float longitude, 
			int level, int levelShift, String description, String photoId, 
			String facebookId, String googleId, Date lastActivityDate,
			Date premiumExpirationDate, String language, float balance, 
			boolean deleted);
	
	public User updatePersonalData(int id, String email, String password, 
			String firstName, String lastName);
	
	public User updateContactDetails(int id, String phone, String country, 
			String state, String city, String address);
	
	public User updateGeoLocation(int id, float latitude, float longitude);
	
	public User updateMetaData(int id, Date birthday, String gender, 
			String description);
	
	public User updateSettings(int id, int level, int levelShift, 
			String language);
	
	public void updatePhoto(int id, String photoId);
	
	public void updateBalance(int id, float balance);
	
	public void updatePremium(int id, Date premiumExpirationDate);	
	
	public void delete(int id);
	
	public void restore(int id);
	*/
	
	public User update(User user);
	
	public User find(int id);
	
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
	public List<User> findAll();
	
	public List<User> findByDeleted(boolean deleted);
	
	public List<User> find(Map<String, Object> params, int startPosition, int maxResults);
	
	public int count();
	
	public int count(Map<String, Object> params);

}
