package com.aminapp.my_smartphone;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SendToAllTask extends IntentService{

	public SendToAllTask() {
		super("SendToAllTask");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		Log.i("Log", "reStart" + "1");
		return START_STICKY;

	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Intent myintent=new Intent(G.context,AllTask.class);
		myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Log.i("Start", "send");
		startActivity(myintent);		
	}

	

}
