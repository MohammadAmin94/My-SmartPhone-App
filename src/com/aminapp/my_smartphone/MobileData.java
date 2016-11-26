package com.aminapp.my_smartphone;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MobileData extends IntentService{

	public MobileData() {
		super("MobileData");
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) { 
		Log.i("Log", "reStart" + "1");
		return START_STICKY;

	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Calendar calendar;
		calendar= Calendar.getInstance();
		int Ehour=G.shPreferences.getInt("Mdata Enable Hour", 100);
		int Emin=G.shPreferences.getInt("Mdata Enable Min", 100);
		int Dhour=G.shPreferences.getInt("Mdata Disable Hour", 100);
		int Dmin=G.shPreferences.getInt("Mdata Disable Min", 100);
		boolean EnableChecked=G.shPreferences.getBoolean("Mdata ECheck", false);
		boolean DisableChecked=G.shPreferences.getBoolean("Mdata DCheck", false);
		Task newtask=new Task();
      if(EnableChecked && Ehour==calendar.get(Calendar.HOUR_OF_DAY) && Emin==calendar.get(Calendar.MINUTE))
         {
    	  Log.i("Time", "yes");
    	  //setMobileDataState(true);
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
			Toast.makeText(G.context, " فعال شد mobile data ", Toast.LENGTH_SHORT).show();
         } if(DisableChecked && Dhour==calendar.get(Calendar.HOUR_OF_DAY) && Dmin==calendar.get(Calendar.MINUTE))
          {
       	  Log.i("Time", "no");
        	 //setMobileDataState(false);
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
			Toast.makeText(G.context, " غیرفعال شد mobile data ", Toast.LENGTH_SHORT).show();

          }		
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
