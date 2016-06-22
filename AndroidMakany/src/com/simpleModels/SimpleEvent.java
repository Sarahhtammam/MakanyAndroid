package com.simpleModels;

import java.util.Vector;

public class SimpleEvent extends Element
{

	private String id, name, category, description,date, from, to;
	
	private double latitude, longitude;
	private String ownerMail, district, ownerName;
	private Vector<String> goingMails, postIDs;
	private Vector<SimpleReview> reviews;
	
	
	public SimpleEvent(String id, String name, String category, String description,
			double latitude, double longitude, String ownerMail, String district,
			String goingMails, String postIDs,String date, String from, String to)
	{
		super(Type.EVENT);
		this.id=id;
		this.name=name;
		this.category=category;
		this.description=description;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.ownerMail=ownerMail;
		this.setDistrict(district);
		this.date = date;
		this.from = from;
		this.to = to;
		
		//this.goingMails=goingMails;
		//this.postIDs=postIDs;
	}
	
	public String getName ()
	{
		return name;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	public String getOwner() {
		// TODO Auto-generated method stub
		return this.ownerMail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Vector<SimpleReview> getReviews() {
		return reviews;
	}

	public void setReviews(Vector<SimpleReview> reviews) {
		this.reviews = reviews;
	}

	public Vector<String> getGoingMails() {
		return goingMails;
	}

	public void setGoingMails(Vector<String> goingMails) {
		this.goingMails = goingMails;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Vector<String> getPostIDs() {
		return postIDs;
	}

	public void setPostIDs(Vector<String> postIDs) {
		this.postIDs = postIDs;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	

}
