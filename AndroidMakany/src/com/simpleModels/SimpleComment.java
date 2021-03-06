package com.simpleModels;

public class SimpleComment extends Element
{
	
	private String id,userEmail, username, content, date, postID;
	
	public SimpleComment(String id,String userEmail,String username, String content, String date, String postID)
	{
		super(Type.COMMENT);
		this.id=id;
		this.userEmail=userEmail;
		this.username=username;
		this.content=content;
		this.postID=postID;
	}
	
	public String getID()
	{
		return id;
	}
	
	public String getUserEmail()
	{
		return userEmail;
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getPostID()
	{
		return postID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
