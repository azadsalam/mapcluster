package com.sid.mapClusering;


import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

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
			//Toast.makeText(getApplicationContext(), "Do this later!!", Toast.LENGTH_LONG).show();
			//requestToWeb(mapOverlay);
			
			SendPointsAndFetchResult spfr = new SendPointsAndFetchResult(this);
			
		}
	}

	private void toggle_button_handler() {
		toogleEditMode();
	}
	
	public void requestToWeb(MapOverlay mapOverlay) {
		
		ArrayList<GeoPoint> gpArrayList=mapOverlay.gpQueue;
		String msg="";
		
		for (GeoPoint geoPoint : gpArrayList) {
			msg+="Lat: "+geoPoint.getLatitudeE6()+" Lon: "+geoPoint.getLongitudeE6()+"\n";
		}
		
		Toast.makeText(getApplicationContext(),gpArrayList.size()+"\n"+msg, Toast.LENGTH_LONG).show();
		

		/*
		cManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo info=cManager.getActiveNetworkInfo();
			
        if(info!=null){
        	if(info.isAvailable()&&info.isConnected()){
        		
        		pd=ProgressDialog.show(MapClusteringActivity.this,"","Posting Data");
        		PostThread pThread=new PostThread();
        		pThread.start();
        	}
        }
        */
	}
	
	
	class PostThread extends Thread{
		
    	public void run(){
    		//do post request
    		DefaultHttpClient client=new DefaultHttpClient();
    		HttpPost post=new HttpPost("http://10.0.2.2/user_tracking/index.php/user/showCordinates");
    		List pairs=new ArrayList();
    		
    		pairs.add(new BasicNameValuePair("name","sid"));
    		
    		try {
				post.setEntity(new UrlEncodedFormEntity(pairs));
				ResponseHandler<String> respHandler=new BasicResponseHandler();
				String response=client.execute(post,respHandler);
				if(response.equalsIgnoreCase("success")){
					postHandler.sendEmptyMessage(1);
				}
				else{
					postHandler.sendEmptyMessage(0);
				}
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				postHandler.sendEmptyMessage(0);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				postHandler.sendEmptyMessage(0);
			} catch (IOException e) {
				e.printStackTrace();
				postHandler.sendEmptyMessage(0);
			}
    	}
    }
    
    Handler postHandler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		pd.dismiss();
    		if(msg.what==0){
    			Toast.makeText(getApplicationContext(), "Request FAiled", Toast.LENGTH_LONG).show();
    		}
    		else{
    			Toast.makeText(getApplicationContext(), "Successfully Data Inserted", Toast.LENGTH_LONG).show();
    		}
    	}
    };
    
}
