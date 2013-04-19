package com.sid.mapClusering;

import java.util.HashMap;

import android.content.Context;
import android.widget.Toast;

public class SendPointsAndFetchResult implements WebServiceUser
{
	String replyTokens[] = {"points"};
	HashMap<String, Object> data ;
	Context context;
	public SendPointsAndFetchResult(Context c)
	{
		context = c; 
		WebServiceAdapter wsu;
		data = new HashMap<String, Object>();
		data.put("mail","");
    	//data.put("password",password.getText().toString());
    	
    	//reply tokens
    	
		
       wsu = new WebServiceAdapter(this,context,"Calculating!!",Values.baseUrl +"mappoint",data,replyTokens);
		wsu.startWebService();
	}

	@Override
	public void processResult(HashMap<String, Object> data) {
		// TODO Auto-generated method stub
		
		if(data != null)
		{
			Toast.makeText(context, data.get(replyTokens[0]).toString(), Toast.LENGTH_LONG).show();
		}
	}
}
