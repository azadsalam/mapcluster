package com.sid.mapClusering;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapOverlay extends Overlay {
	
	private float x1,y1,x2,y2;
	private GeoPoint p1=null,p2=null;
	private MapClusteringActivity mv = null;
	private Paint paint = new Paint();
	private boolean isUp = false;
	Context context;
	ArrayList<GeoPoint> gpQueue;

	//constructor receiving the initial point
	public MapOverlay(MapClusteringActivity mapV,float x,float y){
		
		
		gpQueue=new ArrayList<GeoPoint>();
	    paint.setStrokeWidth(2.0f);
	    
	    //paint.setColor(0x88158923);
	   
	    Random rnd = new Random();
	    paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	    
        paint.setStyle(Style.STROKE);
        
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        
	    x1 = x;
	    y1 = y;
	    mv = mapV;
	    p1 = mapV.getMapView().getProjection().fromPixels((int)x1,(int)y1);
	}
	//override draw method to add our custom drawings
	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		
		/*if(gpQueue.size()>1){
			
			for (int i = 0; i < gpQueue.size()-1; i++) {
				GeoPoint gp1=gpQueue.get(i);
				GeoPoint gp2=gpQueue.get(i+1);
				
				//get the 2 geopoints defining the area and transform them to pixels
		        //this way if we move or zoom the map rectangle will follow accordingly
		        Point screenPts1 = new Point();
		        mapView.getProjection().toPixels(gp1, screenPts1);
		        Point screenPts2 = new Point();
		        mapView.getProjection().toPixels(gp2, screenPts2);
		        
		        //draw line
		        canvas.drawLine(screenPts1.x, screenPts1.y, screenPts2.x, screenPts2.y, paint);
			}
			
			if(isUp){
				Point screenPts1 = new Point();
		        mapView.getProjection().toPixels(gpQueue.get(gpQueue.size()-1), screenPts1);
		        Point screenPts2 = new Point();
		        mapView.getProjection().toPixels(gpQueue.get(0), screenPts2);
		        
		        //draw line
		        canvas.drawLine(screenPts1.x, screenPts1.y, screenPts2.x, screenPts2.y, paint);
			}
		}*/
		
		if(gpQueue.size()>1){
			ArrayList<Point> p=new ArrayList<Point>();
	        
	        for(int i=0;i<gpQueue.size();i++){
	        	p.add(new Point());
	        }

	        Path path = new Path();
	        
	        for(int i=0;i<gpQueue.size();i++){
	        	mapView.getProjection().toPixels(gpQueue.get(i),p.get(i));
	        }
	        
	        path.moveTo(p.get(0).x, p.get(0).y);
	        for(int i=1;i<p.size();i++){
		        path.lineTo(p.get(i).x, p.get(i).y);
	        }
	        
	        canvas.drawPath(path, paint);
	        
	        if(isUp){
	        	paint.setStyle(Style.FILL_AND_STROKE);
	        	paint.setAlpha(40);
	        }
		}
					
	    return true;
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {
	    if(mv.isEditMode() && !isUp){
	        if(e.getAction() == MotionEvent.ACTION_DOWN){
	            x1 = y1 = 0;
	            x1 = e.getX();
	            y1 = e.getY();
	            p1 = mapView.getProjection().fromPixels((int)x1,(int)y1);
	            gpQueue.add(p1);

	        }
	        //here we constantly change geopoint p2 as we move out finger
	        if(e.getAction() == MotionEvent.ACTION_MOVE){
	            x2 = e.getX();
	            y2 = e.getY();
	            p2=mapView.getProjection().fromPixels((int)x2,(int)y2);
	            gpQueue.add(p2);
	        }

	        //---when user lifts his finger---
	        if (e.getAction() == MotionEvent.ACTION_UP) {                
	            isUp = true;
	            
	            //mv.showPoints(gpQueue);
	            SendPointsAndFetchResult spfr = new SendPointsAndFetchResult(mv,mv,gpQueue);
	            
	        }    
	        return true;
	    }
	    return false;
	}
			
}
