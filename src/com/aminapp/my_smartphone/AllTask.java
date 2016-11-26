package com.aminapp.my_smartphone;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;

public class AllTask extends Activity{

	private ContentResolver cResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Start", "run");
		cResolver = getContentResolver();

		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		AudioManager audioManager = (AudioManager)G.context.getSystemService(Context.AUDIO_SERVICE);

		boolean WifiOn=G.shPreferences.getBoolean("WifiOn", false);
		boolean WifiOff=G.shPreferences.getBoolean("WifiOff", false);
		boolean MdataOn=G.shPreferences.getBoolean("MdataOn", false);
		boolean MdataOff=G.shPreferences.getBoolean("MdataOff", false);
		boolean VibrateOn=G.shPreferences.getBoolean("VibrateOn", false);
		boolean VibrateOff=G.shPreferences.getBoolean("VibrateOff", false);
		boolean SilentOn=G.shPreferences.getBoolean("SilentOn", false);
		boolean SilentOff=G.shPreferences.getBoolean("SilentOff", false);
		boolean LightOn=G.shPreferences.getBoolean("LightOn", false);
		boolean LightOff=G.shPreferences.getBoolean("LightOff", false);
		float Light=G.shPreferences.getFloat("Brightness Light", 300);

		if(WifiOn)
		{
			wifi.setWifiEnabled(true);

		}else if(WifiOff)
		{
			Log.i("Start", "wifi 1");
			wifi.setWifiEnabled(false);
			Log.i("Start", "wifi 2");

		}
		
		if(MdataOn)
		{
			  try {
					setMobileDataEnabled(this,true);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(MdataOff)
		{
			try {
				setMobileDataEnabled(this,false);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(VibrateOn)
		{	           
			audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			
		}else if(VibrateOff)
		{
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
		if(SilentOn)
		{
		    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			
		}else if(SilentOff)
		{
		    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
		if(LightOn || LightOff)
		{
			Settings.System.putInt(cResolver,
					Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			
			android.provider.Settings.System.putFloat(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS,  Light);
			//Get the current window attributes
			WindowManager.LayoutParams layoutpars = getWindow().getAttributes();
            //Set the brightness of this window
            layoutpars.screenBrightness = Light;		
            Log.i("Start", "A"+" " +Light);
            //Apply attribute changes to this window
			getWindow().setAttributes(layoutpars);	
            Log.i("Start", "B"+ " " +Light);

		}
        this.finish();
	}
	
	
	public void setMobileDataState(boolean mobileDataEnabled)
	{
	    try
	    {
	        TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

	        Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

	        if (null != setMobileDataEnabledMethod)
	        {
	      	  Log.i("Time", "true");
	            setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
	        }
	    }
	    catch (Exception ex)
	    {
	        Log.e("TAG", "Error setting mobile data state", ex);
	    }
	}
	
	
	private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
	    final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	  Log.i("Time", "true1");
	    final Class conmanClass = Class.forName(conman.getClass().getName());
	    final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
    	  Log.i("Time", "true2");
	    iConnectivityManagerField.setAccessible(true);
	    final Object iConnectivityManager = iConnectivityManagerField.get(conman);
    	  Log.i("Time", "true3");
	    final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
	    final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
	    setMobileDataEnabledMethod.setAccessible(true);
    	  Log.i("Time", "true4");
	    setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
	}
}
