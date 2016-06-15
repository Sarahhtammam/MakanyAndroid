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
		
		
		private Vector<String> categories;
		private Vector<String> approvals,disapprovals;
		private Vector<SimpleComment> comments;
		private Vector<SimpleReport> reports;
		
		
		public SimplePost() {
			super(Type.POST);
			this.id=null;
			this.postType=null;
			this.content = null;
			this.photo=null;
			this.userEmail=null;
			this.district=null;
			this.categories=new Vector<String>();
			this.comments=new Vector<SimpleComment>();
			this.reports=new Vector<SimpleReport>();
			this.approvals=new Vector<String>();
			this.disapprovals=new Vector<String>();
		}

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
			this.categories=categories;
			this.comments=comments;
			this.reports=reports;
			this.approvals=new Vector<String>();
			this.disapprovals=new Vector<String>();
		}
		
		
		public SimplePost(String id,String type,String content,String photo,String userEmail,String district,
				String eventID, String score) 
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
			//this.categories=categories;
			//this.comments=comments;
			//this.reports=reports;
			//this.approvals=new Vector<String>();
			//this.disapprovals=new Vector<String>();
		}
		
		public String getID(){return id;}
		public String getPostType(){return postType;}
		public String getContent(){return content;}
		public String getPhoto(){return photo;}
		public String getUserEmail(){return userEmail;}
		public String getDistrict(){return district;}
		public int getNumApprovals(){return approvals.size();}
		public int getNumDisApprovals(){return disapprovals.size();}
		public int getNumReports(){return reports.size();}


}
