package com.simpleModels;

import java.util.Vector;

public class FoursquarePlace extends Element {

	private String name, address;
	private String rating, phone;
	private String distance; // with meters
	private String category;
	private String latitude, longitude;

	public FoursquarePlace(String name, String address, String rating,
			String phone, String distance, String latitude, String longitude,
			String category) {
		super(Type.FOURSQUAREPLACE);

		this.name = name;

		this.address = address;

		this.rating = rating;

		this.phone = phone;

		this.distance = distance;

		this.latitude = latitude;

		this.longitude = longitude;
		this.category = category;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "FoursquareModel [name=" + name + ", address=" + address
				+ ", rating=" + rating + ", phone=" + phone + ", distance="
				+ distance + ", category=" + category + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}

}
