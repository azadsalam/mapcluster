package com.sid.mapClusering;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class EmptyOverlay extends Overlay {
	private float x1,y1;
	private MapClusteringActivity mv = null;
	private Overlay overlay = null;

	public EmptyOverlay(MapClusteringActivity mapV){
	    mv = mapV;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
	        long when) {
	    // TODO Auto-generated method stub
	    return super.draw(canvas, mapView, shadow, when);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {
	    if(mv.isEditMode()){
	        if(e.getAction() == MotionEvent.ACTION_DOWN){
	            //when user presses the map add a new overlay to the map
	            //move events will be catched by newly created overlay
	            x1 = y1 = 0;
	            x1 = e.getX();
	            y1 = e.getY();

	            overlay = new MapOverlay(mv, x1, y1);
	            mapView.getOverlays().add(overlay);

	        }
	        if(e.getAction() == MotionEvent.ACTION_MOVE){
	        	
	        }
	        //---when user lifts his finger---
	        if (e.getAction() == MotionEvent.ACTION_UP) {                
	        	
	        }
	        
	        return true;
	    }
	    return false;
	}
		
}

