package com.simpleModels;

import java.util.Vector;

public class SimpleEvent extends Element
{

	private String id, name, category, description,date;
	private double latitude, longitude;
	private String ownerMail, district, ownerName;
	private Vector<String> goingMails, postIDs;
	
	
	public SimpleEvent(String id, String name, String category, String description,
			double latitude, double longitude, String ownerMail, String district,
			String goingMails, String postIDs,String date)
	{
		super(Type.EVENT);
		this.id=id;
		this.name=name;
		this.category=category;
		this.description=description;
		this.latitude=latitude;
		this.longitude=longitude;
		this.ownerMail=ownerMail;
		this.district=district;
		this.date = date;
		
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
	

}
