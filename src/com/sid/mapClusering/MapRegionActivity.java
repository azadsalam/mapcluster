package com.sid.mapClusering;

import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapRegionActivity extends MapActivity {
	MapView mapView;
	MapController mapController;
	List<Overlay> mapOverlays;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        mapController = mapView.getController();
        

        mapController.setZoom(7);
        
       
        mapOverlays = mapView.getOverlays();
        
        Drawable drawable = this.getResources().getDrawable(R.drawable.green_point);
       
        MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(drawable, MapRegionActivity.this);
        
        GeoPoint geopoint = new GeoPoint(23784756, 90397353);
        mapController.setCenter(geopoint);
        OverlayItem overlayItem = new OverlayItem(geopoint, "CLUSTER 3", "7 PEPSODENT, I COLGATE :P");
        
        myItemizedOverlay.addOverlayItem(overlayItem);
        
        mapOverlays.add(myItemizedOverlay);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}