package com.aminapp.my_smartphone;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class Brightness extends Activity{

	//Variable to store brightness value
	private int brightness;
	//Content resolver used as a handle to the system's settings
	private ContentResolver cResolver;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get the content resolver
		cResolver = getContentResolver();

		
		    // To handle the auto
			Settings.System.putInt(cResolver,
			Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
 //Get the current system brightness
		    
		boolean EnableChecked=G.shPreferences.getBoolean("Brightness ECheck", false);
		boolean DisableChecked=G.shPreferences.getBoolean("Brightness DCheck", false);
		Calendar calendar;
		calendar= Calendar.getInstance();
		int Ehour=G.shPreferences.getInt("Brightness Enable Hour", 100);
		int Emin=G.shPreferences.getInt("Brightness Enable Min", 100);
		int Dhour=G.shPreferences.getInt("Brightness Disable Hour", 100);
		int Dmin=G.shPreferences.getInt("Brightness Disable Min", 100);
		Float EnableLight=G.shPreferences.getFloat("Brightness Enable Light", 300);
		Float DisableLight=G.shPreferences.getFloat("Brightness Disable Light", 300);
		Task newtask=new Task();
		if(EnableChecked && Ehour==calendar.get(Calendar.HOUR_OF_DAY) && Emin==calendar.get(Calendar.MINUTE))
		{
			android.provider.Settings.System.putFloat(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS,  EnableLight);
			//Get the current window attributes
			WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
            //Set the brightness of this window
            layoutpars.screenBrightness = EnableLight;
			Log.i("Bright", "4" + "" + layoutpars.screenBrightness);
			  
            //Apply attribute changes to this window
			getWindow().setAttributes(layoutpars);
			Log.i("Bright", "5");
			Toast.makeText(G.context, " روشنایی افزایش یافت ", Toast.LENGTH_SHORT).show();
		}if(DisableChecked && Dhour==calendar.get(Calendar.HOUR_OF_DAY) && Dmin==calendar.get(Calendar.MINUTE))
		{
			 android.provider.Settings.System.putFloat(getContentResolver(),
                     android.provider.Settings.System.SCREEN_BRIGHTNESS,  DisableLight);
			//Get the current window attributes
			 WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
            //Set the brightness of this window
            layoutpars.screenBrightness =DisableLight;
			Log.i("Bright", "8" + "" + layoutpars.screenBrightness);
			 
            //Apply attribute changes to this window
			getWindow().setAttributes(layoutpars);
			Log.i("Bright", "9");
			Toast.makeText(G.context, " روشنایی کاهش یافت ", Toast.LENGTH_SHORT).show();
		}
		this.finish();	
	}
	
}
