package com.simpleModels;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleOffer 
{
	private String id;
	private String description, storeMail, photo, date;
	private int numThumbsUp, numThumbsDown, numViews;
	private ArrayList<String> viewers,thumbsup,thumbsdown;
	
	public SimpleOffer(){
		this.id="";
		this.description="";
		this.storeMail="";
		this.photo="";
		this.date="";
		this.viewers=new ArrayList<String>();
		this.thumbsup=new ArrayList<String>();
		this.thumbsdown=new ArrayList<String>();
	}
	
	public SimpleOffer(String id,String description,String storeMail,String photo,String date, String numThumbsUp, 
			String numThumbsDown,String numViewers, String thumbsUp, String thumbsDown, String viewers)
	{
		this.id=id;
		this.description=description;
		this.storeMail=storeMail;
		this.photo=photo;
		this.date=date;

		this.setNumThumbsUp(Integer.parseInt(numThumbsUp));
		this.setNumThumbsDown(Integer.parseInt(numThumbsDown));
		this.setNumViews(Integer.parseInt(numViewers));
		
		this.setThumbsUp(parseStrings(thumbsUp));
		this.setThumbsDown(parseStrings(thumbsDown));
		this.setViewers(parseStrings(viewers));
	
	}
	
	private ArrayList<String> parseStrings (String string)
	{
		String[] array = string.split(";");
		return new ArrayList<String>(Arrays.asList(array));
	}
	
	public String getID(){return id;}
	public String getDescription(){return description;}
	public String getStoreMail(){return storeMail;}
	public String getPhoto(){return photo;}
	public String getDate(){return date;}
	public int getNumViewers(){return viewers.size();}
	public int getNumThumbsUp(){return numThumbsUp;}
	public int getNumThumbsDown(){return numThumbsDown;}


	public void setNumThumbsUp(int numThumbsUp) 
	{
		this.numThumbsUp = numThumbsUp;
	}


	public void setNumThumbsDown( int numThumbsDown) 
	{
		this.numThumbsDown = numThumbsDown;
	}

	public int getNumViews() 
	{
		return numViews;
	}

	public void setNumViews(int numViews) {
		this.numViews = numViews;
	}
	
	public ArrayList<String> getThumvsUp() {
		return thumbsup;
	}

	public void setThumbsUp(ArrayList<String> thumbsUp) {
		this.thumbsup = thumbsUp;
	}
	public ArrayList<String> getThumbsDown() {
		return thumbsdown;
	}

	public void setThumbsDown(ArrayList<String> thumbsDown) {
		this.thumbsdown = thumbsDown;
	}
	public ArrayList<String> getViewers() {
		return viewers;
	}

	public void setViewers(ArrayList<String> viewers) {
		this.viewers = viewers;
	}

}
