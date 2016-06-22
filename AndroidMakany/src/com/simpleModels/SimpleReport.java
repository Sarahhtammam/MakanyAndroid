package com.simpleModels;

public class SimpleReport 
{
	private String userEmail, reason;
	
	public SimpleReport(String userEmail,String reason)
	{
		this.setUserEmail(userEmail);
		this.setReason(reason);
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
