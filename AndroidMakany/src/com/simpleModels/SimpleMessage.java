package com.simpleModels;

public class SimpleMessage {

	
	private String senderMail;
	private String reciverMail;
	private String content;
	private String senderName;
	private String reciverName;
	
	public SimpleMessage(String senderMail, String reciverMail, String content,
			String senderName, String reciverName) {
		super();
		this.senderMail = senderMail;
		this.reciverMail = reciverMail;
		this.content = content;
		this.senderName = senderName;
		this.reciverName = reciverName;
	}
	
	public SimpleMessage(String senderName, String senderMail) {
		super();
		this.senderName = senderName;
		this.senderMail = senderMail;
	}
	
	public String getSenderMail() {
		return senderMail;
	}
	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	public String getReciverMail() {
		return reciverMail;
	}
	public void setReciverMail(String reciverMail) {
		this.reciverMail = reciverMail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReciverName() {
		return reciverName;
	}

	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}
	
	
}
