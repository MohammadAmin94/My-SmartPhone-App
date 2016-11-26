package com.aminapp.my_smartphone;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Long WE=G.shPreferences.getLong("Wifi Enable", 0);		
		Long WD=G.shPreferences.getLong("Wifi Disable", 0);	
		
		Long ME=G.shPreferences.getLong("Mdata Enable", 0);		
		Long MD=G.shPreferences.getLong("Mdata Disable", 0);
		
		Long SE=G.shPreferences.getLong("Silent Enable", 0);
		Long SD=G.shPreferences.getLong("Silent Disable", 0);

		Long VE=G.shPreferences.getLong("Vibrate Enable", 0);
		Long VD=G.shPreferences.getLong("Vibrate Disable", 0);

		Long BE=G.shPreferences.getLong("Brightness Enable", 0);
		Long BD=G.shPreferences.getLong("Brightness Disable", 0);
		
		Long calendarValue=G.shPreferences.getLong("Calendar Value", 0);


		if(calendarValue!=0)
		{
			Intent myintent = new Intent(G.context, SendToAllTask.class);
	        myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent = PendingIntent.getService(G.context, 0, myintent, 0);
	        if(calendarValue +100>System.currentTimeMillis())
			{
               G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendarValue, pendingIntent);
	        }
		}
		
		if(WE!=0)
		{
			Intent intent1 = new Intent(G.context, Wifi.class);
	        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent1 = PendingIntent.getService(G.context, 0, intent1, 0);
			boolean WifiEnableDay=G.shPreferences.getBoolean("Wifi Eday", false);
			boolean WifiEnable=G.shPreferences.getBoolean("Wifi E", false);	
			
			if(WifiEnableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, WE, pendingIntent1);
			else if(WifiEnable)
			{
				if(WE +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, WE, pendingIntent1);
			    }
			}
		}
		if(WD!=0)
		{
			boolean WifiDisableDay=G.shPreferences.getBoolean("Wifi Dday", false);
			boolean WifiDisable=G.shPreferences.getBoolean("Wifi D", false);		
			Intent intent1 = new Intent(G.context, Wifi.class);
	        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent1 = PendingIntent.getService(G.context, 1, intent1, 0);
	        if(WifiDisableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, WD, pendingIntent1);
			else if(WifiDisable)
			{
				if(WD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, WD, pendingIntent1);
	            }
			}
		}
		if(ME!=0)
		{
			boolean MdataEnableDay=G.shPreferences.getBoolean("Mdata Eday", false);
			boolean MdataEnable=G.shPreferences.getBoolean("Mdata E", false);		
			Intent intent2 = new Intent(G.context, MobileData.class);
	        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent2 = PendingIntent.getService(G.context, 0, intent2, 0);
	        if(MdataEnableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, ME, pendingIntent2);
			else if(MdataEnable)
			{
				if(ME +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, ME, pendingIntent2);
				}
	        }
		}
		if(MD!=0)
		{
			boolean MdataDisableDay=G.shPreferences.getBoolean("Mdata Dday", false);
			boolean MdataDisable=G.shPreferences.getBoolean("Mdata D", false);	
			Intent intent2 = new Intent(G.context, MobileData.class);
	        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent2 = PendingIntent.getService(G.context, 1, intent2, 0);
	        if(MdataDisableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, MD, pendingIntent2);
			else if(MdataDisable)
			{
				if(MD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, MD, pendingIntent2);
				}
	        }
		}
		if(SE!=0)
		{
			boolean SilentEnableDay=G.shPreferences.getBoolean("Silent Eday", false);
			boolean SilentEnable=G.shPreferences.getBoolean("Silent E", false);	
			Intent intent3 = new Intent(G.context, Silent.class);
	        intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent3 = PendingIntent.getService(G.context, 0, intent3,0);
	        if(SilentEnableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, SE, pendingIntent3);
			else if(SilentEnable)
			{
				if(WD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, SE, pendingIntent3);
				}
	        }
		}
		if(SD!=0)
		{
			boolean SilentDisableDay=G.shPreferences.getBoolean("Silent Dday", false);
			boolean SilentDisable=G.shPreferences.getBoolean("Silent D", false);	
			Intent intent3 = new Intent(G.context, Silent.class);
	        intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent3 = PendingIntent.getService(G.context, 1, intent3, 0);
	        if(SilentDisableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, SD, pendingIntent3);
			else if(SilentDisable)
			{
				if(SD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, SD, pendingIntent3);
				}
	        }
		}
		if(VE!=0)
		{
			boolean VibrateEnableDay=G.shPreferences.getBoolean("Vibrate Eday", false);
			boolean VibrateEnable=G.shPreferences.getBoolean("Vibrate E", false);	
			Intent intent4 = new Intent(G.context, Vibrate.class);
	        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent4 = PendingIntent.getService(G.context, 0, intent4, 0);
	        if(VibrateEnableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, VE, pendingIntent4);
			else if(VibrateEnable)
			{
				if(VE +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, VE, pendingIntent4);
				}
	        }
		}
		if(VD!=0)
		{
			boolean VibrateDisableDay=G.shPreferences.getBoolean("Vibrate Dday", false);
			boolean VibrateDisable=G.shPreferences.getBoolean("Vibrate D", false);	
			Intent intent4 = new Intent(G.context, Vibrate.class);
	        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent4 = PendingIntent.getService(G.context, 1, intent4, 0);
	        if(VibrateDisableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, VD, pendingIntent4);
			else if(VibrateDisable)
			{
				if(VD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, VD, pendingIntent4);
				}
	        }
		}
		if(BE!=0)
		{
			boolean BrightnessEnableDay=G.shPreferences.getBoolean("Brightness Eday", false);
			boolean BrightnessEnable=G.shPreferences.getBoolean("Brightness E", false);	
			Intent intent5 = new Intent(G.context, SendToBrightness.class);
	        intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent5 = PendingIntent.getService(G.context, 0, intent5, 0);
	        if(BrightnessEnableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, BE, pendingIntent5);
			else if(BrightnessEnable)
			{
				if(BE +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, BE, pendingIntent5);
		        }
	        }
		}
		if(BD!=0)
		{
			boolean BrightnessDisableDay=G.shPreferences.getBoolean("Brightness Dday", false);
			boolean BrightnessDisable=G.shPreferences.getBoolean("Brightness D", false);	
			Intent intent5 = new Intent(G.context, SendToBrightness.class);
	        intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        PendingIntent pendingIntent5 = PendingIntent.getService(G.context, 1, intent5, 0);
	        if(BrightnessDisableDay)
				G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, AlarmManager.INTERVAL_DAY, BD, pendingIntent5);
			else if(BrightnessDisable)
			{
				if(BD +100>System.currentTimeMillis())
				{
	        G.alarmManager.set(AlarmManager.RTC_WAKEUP, BD, pendingIntent5);
				}
			}
		}
	}//onCreate

}
