package com.simpleModels;


public class SimpleItem extends Element {
	
		private String id, userName;
		private String name, description,userEmail, district, photo, state;
		private String date, itemType;
		private String categories;
		
		public SimpleItem(){
			super(Type.ITEM);
			this.id="";
			this.userName = "";
			this.name="";
			this.description="";
			this.userEmail="";
			this.district="";
			this.photo="";
			this.state="";
			this.categories="";
			this.date = "";
			this.itemType = "";
		}
		
		public SimpleItem(String id,String userName ,String name,String description,String userEmail,
				String district,String photo,String state,
				String categories,String date, String itemType){
			super(Type.ITEM);
			this.id=id;
			this.userName = userName;
			this.name=name;
			this.description=description;
			this.userEmail=userEmail;
			this.district=district;
			this.photo=photo;
			this.state=state;
			this.categories=categories;
			this.date = date;
			this.itemType = itemType;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCategories() {
			return categories;
		}

		public void setCategories(String categories) {
			this.categories = categories;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

}
