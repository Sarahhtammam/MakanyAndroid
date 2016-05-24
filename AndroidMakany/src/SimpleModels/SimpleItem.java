package SimpleModels;


public class SimpleItem {
	
		private String id;
		private String name, description,userEmail, district, photo, state;
		private String categories;
		
		public SimpleItem(){
			this.id="";
			this.name="";
			this.description="";
			this.userEmail="";
			this.district="";
			this.photo="";
			this.state="";
			this.categories="";
		}
		
		public SimpleItem(String id,String name,String description,String userEmail,
				String district,String photo,String state,
				String categories){
			this.id=id;
			this.name=name;
			this.description=description;
			this.userEmail=userEmail;
			this.district=district;
			this.photo=photo;
			this.state=state;
			this.categories=categories;
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

}
