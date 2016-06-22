package com.simpleModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class SimpleStore 
{
	private String id;
	private String name, email, password, district, category, description, date;
	private double latitude, longitude;
	private Vector<SimpleOffer> offers;
	private Vector<SimpleReview> reviews;
	
	public SimpleStore(String id, String name,String email,String password,String district,
			String category, String description, String date, String latitude, String longitude)
	{
		this.setId(id);
		this.name=name;
		this.email=email;
		this.password=password;
		this.district=district;
		this.category=category;
		this.description=description;
		this.date=date;
		this.latitude=Integer.parseInt(latitude);
		this.longitude=Integer.parseInt(longitude);
	}
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public Vector<SimpleReview> getReviews() {
		return reviews;
	}


	public void setReviews(Vector<SimpleReview> reviews) {
		this.reviews = reviews;
	}


	public Vector<SimpleOffer> getOffers() {
		return offers;
	}


	public void setOffers(Vector<SimpleOffer> offers) {
		this.offers = offers;
	}

}
