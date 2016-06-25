package com.simpleModels;

import java.util.ArrayList;

public class SimpleStore extends Element
{
	private String id, storeName;
	private String email, password, district, category, description, date;
	private String latitude, longitude;
	private ArrayList<SimpleOffer> offers;
	private ArrayList<SimpleReview> reviews;
	
	public SimpleStore(String id, String name,String email,String password,String district,
			String category, String description, String date, String latitude, String longitude)
	{
		super(Type.STORE);
		this.id=id;
		this.storeName=name;
		this.email=email;
		this.password=password;
		this.district=district;
		this.category=category;
		this.description=description;
		this.date=date;
		this.latitude=latitude;
		this.longitude=longitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public ArrayList<SimpleOffer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<SimpleOffer> offers) {
		this.offers = offers;
	}

	public ArrayList<SimpleReview> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<SimpleReview> reviews) {
		this.reviews = reviews;
	}
	

	public static ArrayList<String> getStoreNames(ArrayList<SimpleStore> stores)
	{
		ArrayList<String> names = new ArrayList<String>();
		for(int i=0; i<stores.size(); i++)
		{
			names.add(stores.get(i).getStoreName());
		}
		return names;
	}
	
	public static String getStoreMailByName(ArrayList<SimpleStore> stores, String storeName)
	{
		for(int i=0; i<stores.size(); i++)
		{
			if(stores.get(i).getStoreName().equals(storeName))
			return stores.get(i).getEmail();
			
		}
		return "";
	}
	


}
