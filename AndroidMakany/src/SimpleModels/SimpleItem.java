package SimpleModels;

import java.util.Vector;

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

}
