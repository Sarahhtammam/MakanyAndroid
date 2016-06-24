package com.simpleModels;

public class SimpleReview 
{
	private String id, reviewerMail, reviewedID, review, date, rating;
	
	public SimpleReview(String id, String reviewerMail, String reviewedID, 
			String review,String date,String rating)
	{
		this.id=id;
		this.reviewerMail=reviewerMail;
		this.reviewedID=reviewedID;
		this.review=review;
		this.date=date;
		this.rating=rating;
	}
	
	public String getID(){return id;}
	public String getReviewerMail(){return reviewerMail;}
	public String getReviewedID(){return reviewedID;}
	public String getReview(){return review;}
	public String getDate(){return date;}
	public String getRating(){return rating;}
	
}
