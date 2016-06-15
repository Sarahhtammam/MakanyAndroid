package com.simpleModels;

import java.util.Vector;

public class SimplePost extends Element
{
		private String id;
		private String postType;
		private String content;
		private String photo;
		private String userEmail;
		private String district;
		private String eventID;
		private String score;
		
		private Vector<String> approvalsMails,disapprovalsMails, reportMails;
		private int numApprovals, numDisApprovals, numReports;
		
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
//not used
		public SimplePost(String id,String type,String content,String photo,String userEmail,String district,
				Vector<String> categories, Vector<SimpleComment> comments, Vector<SimpleReport> reports) 
		{
			super(Type.POST);
			this.id=id;
			this.postType=type;
			this.content = content;
			this.photo=photo;
			this.userEmail=userEmail;
			this.district=district;
			this.comments=comments;
		}
		
		
		public SimplePost(String id,String type,String content,String photo,String userEmail,String district,
				String eventID, String score, String numApprovals, String numDisApprovals, String numReports, 
				String approvalsMails, String disapprovalsMails, String reportMails) 
		{
			super(Type.POST);
			this.id=id;
			this.postType=type;
			this.content = content;
			this.photo=photo;
			this.userEmail=userEmail;
			this.district=district;
			this.eventID=eventID;
			this.score=score;
			this.numApprovals=Integer.parseInt(numApprovals);
			this.numDisApprovals=Integer.parseInt(numDisApprovals);
			this.numReports=Integer.parseInt(numReports);
			this.reportMails=new Vector <String>();
			this.approvalsMails=new Vector<String>();
			this.disapprovalsMails=new Vector<String>();
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

}
