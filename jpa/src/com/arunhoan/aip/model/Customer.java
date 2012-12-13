package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customerID;

	private String address;

	private String country;

	private String creditNo;

	private String email;

	private String givenName;

	private String postcode;

	private String state;

	private String surname;

	private String title;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="customer")
	private Set<Purchase> purchases;

    public Customer() {
    }
    
    public Customer(String title, String givenName, String surname, String address, String state, String postcode, String country, String email, String creditNo)
    {
    	this.setTitle(title);
    	this.setGivenName(givenName);
    	this.setSurname(surname);
    	this.setAddress(address);
    	this.setState(state);
    	this.setPostcode(postcode);
    	this.setCountry(country);
    	this.setEmail(email);
    	this.setCreditNo(creditNo);
    }
	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreditNo() {
		return this.creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	/** Validation section **/
	public boolean isValidGivenName() {
		return Pattern.matches("[A-Za-z]+", givenName);
	}
	public boolean isValidSurname() {
		return Pattern.matches("[A-Za-z]+", surname);
	}
	public boolean isValidEmail() {
		return Pattern.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", email);
	}
	public boolean isValidAddress() {
		return Pattern.matches("[A-Za-z0-9 ]+", address);
	}
	public boolean isValidCountry() {
		return Pattern.matches("[A-Za-z]+", country);
	}
	public boolean isValidState() {
		return Pattern.matches("[A-Za-z]+", state);
	}
	public boolean isValidPoscode() {
		return Pattern.matches("[0-9]+", postcode);
	}
	public boolean isValidCreditNo() {
		return Pattern.matches("[0-9]+", creditNo);
	}
	
	public boolean isValid() {
		return isValidGivenName() && isValidSurname() && isValidAddress() && isValidEmail()
				&& isValidAddress() && isValidCountry() && isValidState() && isValidPoscode() && isValidCreditNo();
	}
}