package com.simpleModels;

import java.util.Vector;

public class SimpleOffer 
{
	private String id;
	private String description, storeMail, photo, date;
	private Vector<String> viewers,thumbsup,thumbsdown;
	
	public SimpleOffer(){
		this.id="";
		this.description="";
		this.storeMail="";
		this.photo="";
		this.date="";
		this.viewers=new Vector<String>();
		this.thumbsup=new Vector<String>();
		this.thumbsdown=new Vector<String>();
	}
	
	public SimpleOffer(String id,String description,String storeMail,String photo,String date){
		this.id=id;
		this.description=description;
		this.storeMail=storeMail;
		this.photo=photo;
		this.date=date;
	}
	
	public String getID(){return id;}
	public String getDescription(){return description;}
	public String getStoreMail(){return storeMail;}
	public String getPhoto(){return photo;}
	public String getDate(){return date;}
	public int getNumViewers(){return viewers.size();}
	public int getNumThumbsUp(){return thumbsup.size();}
	public int getNumThumbsDown(){return thumbsdown.size();}
	

}
