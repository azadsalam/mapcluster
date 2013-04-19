package com.sid.mapClusering;

public class User {
	private int userId;
	private String email;
	private double latitude;
	private double longitude;
	
	public User(int userId, String email, double latitude, double longitude) {
		super();
		this.userId = userId;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
