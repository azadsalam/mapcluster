package com.sid.mapClusering;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.widget.Toast;

public class SendPointsAndFetchResult implements WebServiceUser {
	String replyTokens[] = { "points" };
	HashMap<String, Object> data;
	Context context;
	MapClusteringActivity mapClusteringActivity;

	public SendPointsAndFetchResult(
			MapClusteringActivity mapClusteringActivity, Context c,
			ArrayList<GeoPoint> points) {
		this.mapClusteringActivity = mapClusteringActivity;
		context = c;
		WebServiceAdapter wsu;
		data = new HashMap<String, Object>();

		// data.put("points", "");
		// data.put("password",password.getText().toString());
		// reply tokens

		JSONArray polygon = new JSONArray();
		for (int i = 0, count = 0; i < points.size(); i++) {

			try {
				count = 2 * i;
				polygon.put(count, points.get(i).getLatitudeE6());
				polygon.put((count + 1), points.get(i).getLongitudeE6());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		data.put("points", polygon);

		wsu = new WebServiceAdapter(this, context, "Calculating!!",
				Values.baseUrl + "mappoint/checkPoints", data, replyTokens);
		wsu.startWebService();
	}

	@Override
	public void processResult(HashMap<String, Object> data) {

		if (data != null) {
			// Toast.makeText(context, data.get(replyTokens[0]).toString(),
			// Toast.LENGTH_LONG).show();
			
			
			try 
			{
				String rep = (String)data.get(replyTokens[0]);
				JSONObject points = new JSONObject(rep) ;
	
				ArrayList<GeoPoint> geoPoints = new ArrayList<GeoPoint>();
	
				int len = points.length();
	
				double lat, lon;
				
				for (int i = 0; i < len; i += 2) 
				{
					
					lat =  Double.parseDouble( points.get("" + i).toString());
					lon =  Double.parseDouble( points.get(""	+ (i + 1)).toString());
					lat *= 1000000;
					lon *= 1000000; 
					geoPoints.add(new GeoPoint((int)lat,(int) lon));

				}
				mapClusteringActivity.showPoints(geoPoints);
			}

			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
