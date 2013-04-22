package com.sid.mapClusering;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapClusteringActivity extends MapActivity implements OnClickListener{

	private MapView mapView;
	private boolean isEditMode = false;
	private Button toogle,btnCluster;
	private MapController mapController;
	ConnectivityManager cManager;
	private ProgressDialog pd;

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main);
		
		toogle = (Button)findViewById(R.id.toogle_id);        
	    toogle.setOnClickListener(this);
	    btnCluster=(Button) findViewById(R.id.btnCluster);
	    btnCluster.setOnClickListener(this);
	    
	    
	    mapView = (MapView)findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true); 
	    //add one empty overlay acting as a overlay loader. This will catch press events and will add the actual overlays
	    mapView.getOverlays().add(new EmptyOverlay(this));
	    mapView.postInvalidate();
		
	    mapController = mapView.getController();
        mapController.setZoom(7);
        GeoPoint geopoint = new GeoPoint(23784756, 90397353);
        mapController.setCenter(geopoint);	    
        
        
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	private void toogleEditMode(){
	    isEditMode = !isEditMode;
	    
	    if(isEditMode){
	    	toogle.setText("Stop drawing");
	    }
	    else{
	    	toogle.setText("Start Drawing");
	    }
	}
	
	@Override
	protected boolean isLocationDisplayed() {
	    return false;
	}

	public boolean isEditMode(){
	    return this.isEditMode;
	}

	public MapView getMapView(){
	    return this.mapView;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.toogle_id){
			toggle_button_handler();
		}
		if(v.getId()==R.id.btnCluster){
			
		/*	
			List <Overlay> overlayList = mapView.getOverlays();
			
			for(int i =0;  i<overlayList.size();i++)
			{
				try 
				{
					MapOverlay mapOverlay  = (MapOverlay)overlayList.get(i);
					ArrayList<GeoPoint> points = mapOverlay.gpQueue; 
					
					SendPointsAndFetchResult spfr = new SendPointsAndFetchResult(this,points);
					
					return ;
				} 
				catch (Exception e) 
				{
					// TODO: handle exception
				}
				
			}*/
			
			
			Toast.makeText(getApplicationContext(), "Do this later!!", Toast.LENGTH_LONG).show();
			
		}
	}

	private void toggle_button_handler() {
		toogleEditMode();
	}
	
	public void requestToWeb(MapOverlay mapOverlay) {
		
	/*	ArrayList<GeoPoint> gpArrayList=mapOverlay.gpQueue;
		String msg="";
		
		for (GeoPoint geoPoint : gpArrayList) {
			msg+="Lat: "+geoPoint.getLatitudeE6()+" Lon: "+geoPoint.getLongitudeE6()+"\n";
		}
		
		Toast.makeText(getApplicationContext(),gpArrayList.size()+"\n"+msg, Toast.LENGTH_LONG).show();*/
		
		//SendPointsAndFetchResult spfr = new SendPointsAndFetchResult(this,mapOverlay);
		
	}
	
	public void showPoints(ArrayList<GeoPoint> locationResult){
		
		
	    Drawable markar=getResources().getDrawable(R.drawable.icon);
        MyItemizedOverlay myoverlay=new MyItemizedOverlay(markar,this);
        
        for (GeoPoint geoPoint : locationResult) {
        	OverlayItem item=new OverlayItem(geoPoint, "Hello", "I am sid");
        	myoverlay.addOverlayItem(item);
		}
        
        mapView.getOverlays().add(myoverlay);
	
	}
    
}
