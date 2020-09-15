package entity;

import java.io.Serializable;

import enums.idType;

@SuppressWarnings("serial")

public class Guest implements Serializable {
	private String name, guestID, address, gender, country, nationality;
	private long creditCardNumber, mobileNumber;
	private idType idType;
	
	
	public Guest() {
		
	}

	public Guest(String name, String guestID, String address, String gender, String country, String nationality,
			long creditCardNumber, long mobileNumber, enums.idType idType) {
		super();
		this.name = name;
		this.guestID = guestID;
		this.address = address;
		this.gender = gender;
		this.country = country;
		this.nationality = nationality;
		this.creditCardNumber = creditCardNumber;
		this.mobileNumber = mobileNumber;
		this.idType = idType;
	}
	
	
	public idType getIdType() {
		return idType;
	}

	public void setIdType(idType idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getguestID() {
		return guestID;
	}
	public void setguestID(String guestID) {
		this.guestID = guestID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public long getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
