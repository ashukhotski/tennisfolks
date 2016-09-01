package com.tennisfolks.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity
@Table(name = "location")
public class Location implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String country;
	
	private String state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId")
	private GeonamesAdmin geonamesAdmin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cityId")
	private GeonamesCity geonamesCity;
	
	private String city;
	
	private String address;
	
	private String fullAddress;
	
	private String formattedAddress;
	
	@Column(columnDefinition = "Float(10,6)")
	private float latitude;
	
	@Column(columnDefinition = "Float(10,6)")
	private float longitude;

	public Location() {
		super();
	}

	public Location(int id, String country, String state, GeonamesAdmin geonamesAdmin,  
			GeonamesCity geonamesCity, String city, String address,
			String fullAddress, String formattedAddress, float latitude, float longitude) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.geonamesAdmin = geonamesAdmin;
		this.geonamesCity = geonamesCity;
		this.city = city;
		this.address = address;
		this.fullAddress = fullAddress;
		this.formattedAddress = formattedAddress;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the geonamesAdmin
	 */
	public GeonamesAdmin getGeonamesAdmin() {
		return geonamesAdmin;
	}

	/**
	 * @param geonamesAdmin the geonamesAdmin to set
	 */
	public void setGeonamesAdmin(GeonamesAdmin geonamesAdmin) {
		this.geonamesAdmin = geonamesAdmin;
	}

	/**
	 * @return the geonamesCity
	 */
	public GeonamesCity getGeonamesCity() {
		return geonamesCity;
	}

	/**
	 * @param geonamesCity the geonamesCity to set
	 */
	public void setGeonamesCity(GeonamesCity geonamesCity) {
		this.geonamesCity = geonamesCity;
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

	/**
	 * @return the fullAddress
	 */
	public String getFullAddress() {
		return fullAddress;
	}

	/**
	 * @param fullAddress the fullAddress to set
	 */
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
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
   
}
