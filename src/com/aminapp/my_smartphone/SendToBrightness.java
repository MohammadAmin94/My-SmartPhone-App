package com.aminapp.my_smartphone;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SendToBrightness extends IntentService{

	public SendToBrightness() {
		super("SendToBrightness");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		Log.i("Log", "reStart" + "1");
		return START_STICKY;

	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Intent myintent=new Intent(G.context,Brightness.class);
		myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(myintent);
	}
	
	

}
