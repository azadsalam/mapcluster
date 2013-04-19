package com.sid.mapClusering;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.*;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> 
{
	public ArrayList<OverlayItem> overlayItemList;
	public Context context;
	public MyItemizedOverlay(Drawable drawable,Context c) 
	{
		// TODO Auto-generated constructor stub
		super(boundCenterBottom(drawable));
		context = c;
		overlayItemList = new ArrayList<OverlayItem>();
	}
	
	@Override
	protected OverlayItem createItem(int index) {
		// TODO Auto-generated method stub
		return overlayItemList.get(index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return overlayItemList.size();
	}

	public void addOverlayItem(OverlayItem overlayItem)
	{
		
		overlayItemList.add(overlayItem);
		populate();
	}
	
	@Override
	protected boolean onTap(int index) {
		// TODO Auto-generated method stub
		
		OverlayItem overlayItem = overlayItemList.get(index);
		Builder alertDialog = new Builder(context); 
		alertDialog.setTitle(overlayItem.getTitle());
		alertDialog.setMessage(overlayItem.getSnippet());
		alertDialog.show();
		return true;
	}
}
