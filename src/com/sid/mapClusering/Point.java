package com.sid.mapClusering;

import com.google.android.maps.GeoPoint;

public class Point {
	private GeoPoint gp;
	private int latitude;
	private int longitude;
	
	public Point(int latitude, int longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		gp=new GeoPoint(latitude, longitude);
	}
	
	public GeoPoint getGp() {
		return gp;
	}
	public void setGp(GeoPoint gp) {
		this.gp = gp;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
}
