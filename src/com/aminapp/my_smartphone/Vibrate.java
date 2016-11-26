package com.aminapp.my_smartphone;

import java.util.Calendar;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Vibrate extends IntentService{
public Vibrate() {
		super("Vibrate");
	}

@Override
public int onStartCommand(Intent intent, int flags, int startId) { 
	Log.i("Log", "reStart" + "1");
	return START_STICKY;

}
@Override
protected void onHandleIntent(Intent intent) {
	
	AudioManager audioManager = (AudioManager)G.context.getSystemService(Context.AUDIO_SERVICE);
	boolean EnableChecked=G.shPreferences.getBoolean("Vibrate ECheck", false);
	boolean DisableChecked=G.shPreferences.getBoolean("Vibrate DCheck", false);
	Calendar calendar;
	calendar= Calendar.getInstance();
	int Ehour=G.shPreferences.getInt("Vibrate Enable Hour", 100);
	int Emin=G.shPreferences.getInt("Vibrate Enable Min", 100);
	int Dhour=G.shPreferences.getInt("Vibrate Disable Hour", 100);
	int Dmin=G.shPreferences.getInt("Vibrate Disable Min", 100);
	
	Task newtask=new Task();
             if(EnableChecked && Ehour==calendar.get(Calendar.HOUR_OF_DAY) && Emin==calendar.get(Calendar.MINUTE))
               {
	               audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	   			Toast.makeText(G.context, " فعال شد vibrate ", Toast.LENGTH_SHORT).show();
               } if(DisableChecked  && Dhour==calendar.get(Calendar.HOUR_OF_DAY) && Dmin==calendar.get(Calendar.MINUTE))
                 {
	               audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);	
	   			Toast.makeText(G.context, " غیرفعال شد vibrate ", Toast.LENGTH_SHORT).show();
                 }	
}
}
