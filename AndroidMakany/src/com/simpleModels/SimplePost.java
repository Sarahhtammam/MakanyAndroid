package com.simpleModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class SimplePost extends Element
{
		private String id;
		private String postType;
		private String content;
		private String photo;
		private String userName;
		private String userEmail;
		private String district;
		private String eventID;
		
		private ArrayList<String> approvalsMails,disapprovalsMails, reportMails, categories;
		private int score, numApprovals, numDisApprovals, numReports;
		
		private Vector<SimpleComment> comments;
		
		
		public SimplePost() {
			super(Type.POST);
			this.id=null;
			this.postType=null;
			this.content = null;
			this.photo=null;
			this.userEmail=null;
			this.district=null;
			this.comments=new Vector<SimpleComment>();
			
		}
		
		public SimplePost(String id,String type,String content,String photo,String userName, String userEmail,String district,
				String eventID, String score, String numApprovals, String numDisApprovals, String numReports, 
				String approvalsMails, String disapprovalsMails, String reportMails, String categories) 
		{
			super(Type.POST);
			this.id=id;
			this.postType=type;
			this.content = content;
			this.photo=photo;
			this.userName=userName;
			this.userEmail=userEmail;
			this.district=district;
			this.eventID=eventID;
			
			this.score=Integer.parseInt(score);
			this.numApprovals=Integer.parseInt(numApprovals);
			this.numDisApprovals=Integer.parseInt(numDisApprovals);
			this.numReports=Integer.parseInt(numReports);
			
			this.setReportMails(parseStrings(reportMails));
			this.setApprovalsMails(parseStrings(approvalsMails));
			this.setDisapprovalsMails(parseStrings(disapprovalsMails));
			this.setCategories(parseStrings(categories));
			
		}
		
		public String getID(){return id;}
		public String getPostType(){return postType;}
		public String getContent(){return content;}
		public String getPhoto(){return photo;}
		public String getUserEmail(){return userEmail;}
		public String getDistrict(){return district;}
		public int getNumApprovals(){return numApprovals;}
		public int getNumDisApprovals(){return numDisApprovals;}
		public int getNumReports(){return numReports;}

		public void setComments(Vector<SimpleComment> comments)
		{
			this.comments=comments;
		}
		
		public Vector<SimpleComment> getComments()
		{
			return this.comments;
		}
		
		private ArrayList<String> parseStrings (String string)
		{
			String[] array = string.split(";");
			return new ArrayList<String>(Arrays.asList(array));
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getEventID() {
			return eventID;
		}

		public void setEventID(String eventID) {
			this.eventID = eventID;
		}

		public int getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = Integer.parseInt(score);
		}

		public ArrayList<String> getReportMails() {
			return reportMails;
		}

		public void setReportMails(ArrayList<String> reportMails) {
			this.reportMails = reportMails;
		}

		public ArrayList<String> getDisapprovalsMails() {
			return disapprovalsMails;
		}

		public void setDisapprovalsMails(ArrayList<String> disapprovalsMails) {
			this.disapprovalsMails = disapprovalsMails;
		}

		public ArrayList<String> getCategories() {
			return categories;
		}

		public void setCategories(ArrayList<String> categories) {
			this.categories = categories;
		}

		public ArrayList<String> getApprovalsMails() {
			return approvalsMails;
		}

		public void setApprovalsMails(ArrayList<String> approvalsMails) {
			this.approvalsMails = approvalsMails;
		}

}
