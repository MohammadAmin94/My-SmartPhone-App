package com.aminapp.my_smartphone;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class G extends Application{
	public static Context         context;
    public static AlarmManager alarmManager;
    public static SharedPreferences shPreferences;

	 @Override
	    public void onCreate() {
	        super.onCreate();

	        context = getApplicationContext();
	        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	        shPreferences=PreferenceManager.getDefaultSharedPreferences(context);
	    }
}
