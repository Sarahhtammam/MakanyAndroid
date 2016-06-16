package com.simpleModels;

import java.util.Vector;

public class SimpleUser 
{
		private String id;
		private String name, email, password, birthDate, district, gender;
		private String twitter, foursquare;
		private int trust;
		
		private Vector<String> interests;
		
		public SimpleUser(String email)
		{
			this.setId("");
			this.name="";
			this.email=email;
			this.setPassword("");
			this.setBirthDate("");
			this.district="";
			this.setGender("");
			this.setTwitter("");
			this.setFoursquare("");
			this.setInterests(null);
		}

		
		public SimpleUser(String id, String name,String email,String password,String birthDate,
				String district, String gender, String twitter, String foursquare, int trust,
				Vector<String> interests)
		{
			this.setId(id);
			this.name=name;
			this.email=email;
			this.setPassword(password);
			this.setBirthDate(birthDate);
			this.district=district;
			this.setGender(gender);
			this.setTwitter(twitter);
			this.setFoursquare(foursquare);
			this.setInterests(interests);
			this.setTrust(trust);
		}
		
		public String get_email () 
		{
			return email;
		}


		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}


		public String getDistrict() {
			// TODO Auto-generated method stub
			return district;
		}


		public String getFoursquare() {
			return foursquare;
		}


		public void setFoursquare(String foursquare) {
			this.foursquare = foursquare;
		}


		public String getTwitter() {
			return twitter;
		}


		public void setTwitter(String twitter) {
			this.twitter = twitter;
		}


		public String getBirthDate() {
			return birthDate;
		}


		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public int getTrust() {
			return trust;
		}


		public void setTrust(int trust) {
			this.trust = trust;
		}


		public Vector<String> getInterests() {
			return interests;
		}


		public void setInterests(Vector<String> interests) {
			this.interests = interests;
		}



}
