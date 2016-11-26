package com.aminapp.my_smartphone;

import java.util.Calendar;

import com.aminapp.auto_tasks.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends Activity {

	public int counter;
	public float curBrightnessValue;
	public static boolean curbrightness;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		ImageView wifi=(ImageView) findViewById(R.id.imgWifi);
		ImageView mdata=(ImageView) findViewById(R.id.imgMdata);
		ImageView vibrate=(ImageView) findViewById(R.id.imgVibrate);
		ImageView silent=(ImageView) findViewById(R.id.imgSilent);
		ImageView brightness=(ImageView) findViewById(R.id.imgLight);
		ImageView about=(ImageView) findViewById(R.id.imgAbout);
		ImageView help=(ImageView) findViewById(R.id.imgHelp);
		ImageView star=(ImageView) findViewById(R.id.imgStar);

		Button One=(Button) findViewById(R.id.btnStateOne);
		
		int Ehour,Emin,Dhour,Dmin;
		final Task task=new Task();
		
		 try {
	         curBrightnessValue=android.provider.Settings.System.getInt(
	        getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
	        Log.i("brightness",""+ curBrightnessValue);
			Log.i("brightness", ""+ curbrightness);
	    } catch (SettingNotFoundException e) {
	        e.printStackTrace();
	    }

		wifi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=1;
				SharedPreferences.Editor editCounter=G.shPreferences.edit();
				editCounter.putInt("Counter", counter);
				editCounter.commit();
			Intent myIntent=new Intent(Menu.this,Task.class);
			startActivity(myIntent);
			

				
			}
		});
		
		wifi.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 Dialog dialog=new Dialog(Menu.this);
				dialog.setContentView(R.layout.wifi_dialog);
				dialog.setTitle("اطلاعات وای فای");
				 TextView txtEnable=(TextView) dialog.findViewById(R.id.txtEnable);
				 TextView txtDisable=(TextView) dialog.findViewById(R.id.txtDisable);
				 TextView txtCheck=(TextView) dialog.findViewById(R.id.txtCheck);
				int wifiEH=G.shPreferences.getInt("Wifi Enable Hour", 100);
				int wifiEM=G.shPreferences.getInt("Wifi Enable Min", 100);
				int wifiDH=G.shPreferences.getInt("Wifi Disable Hour", 100);
				int wifiDM=G.shPreferences.getInt("Wifi Disable Min", 100);
				boolean repeat1=G.shPreferences.getBoolean("Wifi Eday", false);
				boolean repeat2=G.shPreferences.getBoolean("Wifi Dday", false);

				Log.i("wifi", "1 "+ "wifiData");
				if(wifiEH!=100 && wifiEM!=100)
				{
					Log.i("wifi", "2 "+ wifiEH +" "+ wifiEM);
					String enable=String.valueOf(wifiEH) + " : " + String.valueOf(wifiEM);
					txtEnable.setText(enable);
				}
				if(wifiDH!=100 && wifiDM!=100)
				{
					Log.i("wifi", "3 "+ wifiDH +" "+ wifiDM);
					String enable=String.valueOf(wifiDH) + " : " + String.valueOf(wifiDM);
					txtDisable.setText(enable);

				}
				if(repeat1 || repeat2)
				{
					String enable="فعال";
					txtCheck.setText(enable);
					Log.i("wifi", "4 "+ enable);

				}

				dialog.show();
				return false;
			}
		});
		
		mdata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=2;
				SharedPreferences.Editor editCounter=G.shPreferences.edit();
				editCounter.putInt("Counter", counter);
				editCounter.commit();
				Intent myIntent=new Intent(Menu.this,Task.class);
				Menu.this.startActivity(myIntent);
				
			}
		});
		
        mdata.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 Dialog dialog=new Dialog(Menu.this);
				dialog.setContentView(R.layout.mdata_dialog);
				dialog.setTitle("اطلاعات اینترنت همراه");
				 TextView txtEnable=(TextView) dialog.findViewById(R.id.txtEnable);
				 TextView txtDisable=(TextView) dialog.findViewById(R.id.txtDisable);
				 TextView txtCheck=(TextView) dialog.findViewById(R.id.txtCheck);
				int mdataEH=G.shPreferences.getInt("Mdata Enable Hour", 100);
				int mdataEM=G.shPreferences.getInt("Mdata Enable Min", 100);
				int mdataDH=G.shPreferences.getInt("Mdata Disable Hour", 100);
				int mdataDM=G.shPreferences.getInt("Mdata Disable Min", 100);
				boolean repeat1=G.shPreferences.getBoolean("Mdata Eday", false);
				boolean repeat2=G.shPreferences.getBoolean("Mdata Dday", false);



				if(mdataEH!=100 && mdataEM!=100)
				{
					String enable=String.valueOf(mdataEH) + " : " + String.valueOf(mdataEM);
					txtEnable.setText(enable);
					Log.i("mdata", "2 "+ mdataEH +" "+ mdataEM);
				}
				if(mdataDH!=100 && mdataDM!=100)
				{
					String enable=String.valueOf(mdataDH) + " : " + String.valueOf(mdataDM);
					txtDisable.setText(enable);
					Log.i("mdata", "3 "+ mdataDH +" "+ mdataDM);

				}
				if(repeat1 || repeat2)
				{
					String enable="فعال";
					txtCheck.setText(enable);
					Log.i("mdata", "4 "+ enable);

				}
				dialog.show();
				return false;
			}
		});

		silent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=3;
				SharedPreferences.Editor editCounter=G.shPreferences.edit();
				editCounter.putInt("Counter", counter);
				editCounter.commit();
				Intent myIntent=new Intent(Menu.this,Task.class);
				Menu.this.startActivity(myIntent);
				
			}
		});
		
        silent.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 Dialog dialog=new Dialog(Menu.this);
				dialog.setContentView(R.layout.silent_dialog);
				dialog.setTitle("اطلاعات سایلنت");
				 TextView txtEnable=(TextView) dialog.findViewById(R.id.txtEnable);
				 TextView txtDisable=(TextView) dialog.findViewById(R.id.txtDisable);
				 TextView txtCheck=(TextView) dialog.findViewById(R.id.txtCheck);
				int SilentEH=G.shPreferences.getInt("Silent Enable Hour", 100);
				int SilentEM=G.shPreferences.getInt("Silent Enable Min", 100);
				int SilentDH=G.shPreferences.getInt("Silent Disable Hour", 100);
				int SilentDM=G.shPreferences.getInt("Silent Disable Min", 100);
				boolean repeat1=G.shPreferences.getBoolean("Silent Eday", false);
				boolean repeat2=G.shPreferences.getBoolean("Silent Dday", false);

				Log.i("silent", "1 "+ "silentData");
				if(SilentEH!=100 && SilentEM!=100)
				{
					Log.i("silent", "2 "+ SilentEH +" "+ SilentEM);
					String enable=String.valueOf(SilentEH) + " : " + String.valueOf(SilentEM);
					txtEnable.setText(enable);
				}
				if(SilentDH!=100 && SilentDM!=100)
				{
					Log.i("silent", "3 "+ SilentDH +" "+ SilentDM);
					String enable=String.valueOf(SilentDH) + " : " + String.valueOf(SilentDM);
					txtDisable.setText(enable);

				}
				if(repeat1 || repeat2)
				{
					String enable="فعال";
					txtCheck.setText(enable);
					Log.i("silent", "4 "+ enable);

				}
				dialog.show();
				return false;
			}
		});

		vibrate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=4;
				SharedPreferences.Editor editCounter=G.shPreferences.edit();
				editCounter.putInt("Counter", counter);
				editCounter.commit();
				Intent myIntent=new Intent(Menu.this,Task.class);
				Menu.this.startActivity(myIntent);
				
			}
		});

        vibrate.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 Dialog dialog=new Dialog(Menu.this);
				dialog.setContentView(R.layout.vibrate_dialog);
				dialog.setTitle("اطلاعات ویبره");
				 TextView txtEnable=(TextView) dialog.findViewById(R.id.txtEnable);
				 TextView txtDisable=(TextView) dialog.findViewById(R.id.txtDisable);
				 TextView txtCheck=(TextView) dialog.findViewById(R.id.txtCheck);
				int VibrateEH=G.shPreferences.getInt("Vibrate Enable Hour", 100);
				int VibrateEM=G.shPreferences.getInt("Vibrate Enable Min", 100);
				int VibrateDH=G.shPreferences.getInt("Vibrate Disable Hour", 100);
				int VibrateDM=G.shPreferences.getInt("Vibrate Disable Min", 100);
				boolean repeat1=G.shPreferences.getBoolean("Vibrate Eday", false);
				boolean repeat2=G.shPreferences.getBoolean("Vibrate Dday", false);

				if(VibrateEH!=100 && VibrateEM!=100)
				{
					String enable=String.valueOf(VibrateEH) + " : " + String.valueOf(VibrateEM);
					txtEnable.setText(enable);
				}
				if(VibrateDH!=100 && VibrateDM!=100)
				{
					String enable=String.valueOf(VibrateDH) + " : " + String.valueOf(VibrateDM);
					txtDisable.setText(enable);

				}
				if(repeat1 || repeat2)
				{
					String enable="فعال";
					txtCheck.setText(enable);

				}
				dialog.show();
				return false;
			}
		});

		brightness.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter=5;
				SharedPreferences.Editor editCounter=G.shPreferences.edit();
				editCounter.putInt("Counter", counter);
				editCounter.commit();
				Intent myIntent=new Intent(Menu.this,BrightnessTask.class);
				Menu.this.startActivity(myIntent);
				
			}
		});
		
        brightness.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 Dialog dialog=new Dialog(Menu.this);
				dialog.setContentView(R.layout.brightness_dialog);
				dialog.setTitle("اطلاعات روشنایی صفحه نمایش");
				 TextView txtEnable=(TextView) dialog.findViewById(R.id.txtEnable);
				 TextView txtDisable=(TextView) dialog.findViewById(R.id.txtDisable);
				 TextView txtCheck=(TextView) dialog.findViewById(R.id.txtCheck);
				int BrightnessEH=G.shPreferences.getInt("Brightness Enable Hour", 100);
				int BrightnessEM=G.shPreferences.getInt("Brightness Enable Min", 100);
				int BrightnessDH=G.shPreferences.getInt("Brightness Disable Hour", 100);
				int BrightnessDM=G.shPreferences.getInt("Brightness Disable Min", 100);
				boolean repeat1=G.shPreferences.getBoolean("Brightness Eday", false);
				boolean repeat2=G.shPreferences.getBoolean("Brightness Dday", false);

				if(BrightnessEH!=100 && BrightnessEM!=100)
				{
					String enable=String.valueOf(BrightnessEH) + " : " + String.valueOf(BrightnessEM);
					txtEnable.setText(enable);
				}
				if(BrightnessDH!=100 && BrightnessDM!=100)
				{
					String enable=String.valueOf(BrightnessDH) + " : " + String.valueOf(BrightnessDM);
					txtDisable.setText(enable);

				}
				if(repeat1 || repeat2)
				{
					String enable="فعال";
					txtCheck.setText(enable);

				}
				dialog.show();
				return false;
			}
		});
        
        One.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent=new Intent(Menu.this,Time.class);
				Menu.this.startActivity(myIntent);
			}
		});

        help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog help=new Dialog(Menu.this);
				help.setContentView(R.layout.help);
				help.setTitle("راهنمای برنامه");
				
				TextView set=(TextView) help.findViewById(R.id.txtSet);
				TextView reset=(TextView) help.findViewById(R.id.txtReset);
				TextView info=(TextView) help.findViewById(R.id.txtInfo);
				TextView all=(TextView) help.findViewById(R.id.txtAll);
				Button Ok=(Button) help.findViewById(R.id.Ok);
				
				help.show();
				
				set.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Dialog set=new Dialog(Menu.this);
					    set.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
						set.setContentView(R.layout.help_set);
						set.show();
					}
				});
				
				reset.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Dialog reset=new Dialog(Menu.this);
					    reset.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
						reset.setContentView(R.layout.help_reset);
						reset.show();
					}
				});

				info.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Dialog info=new Dialog(Menu.this);
					    info.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
						info.setContentView(R.layout.help_info);
						info.show();
					}
				});
				
				all.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Dialog all=new Dialog(Menu.this);
					    all.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
						all.setContentView(R.layout.help_all);
						all.show();
					}
				});
				
				Ok.setOnClickListener(new  OnClickListener() {
					
					@Override
					public void onClick(View v) {
				help.dismiss();		
					}
				});
				
			}
		});
        
        about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Dialog about=new Dialog(Menu.this);
			    about.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
				about.setContentView(R.layout.about);
				about.show();
			}
		});
        
        star.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = "bazaar://details?id=PackageName";
				Intent next = new Intent(Intent.ACTION_EDIT);
				try {
					next.setData(Uri.parse(url));
					startActivity(next);
				} catch (Exception e) {
					next.setData(Uri.parse("http://cafebazaar.ir/"));
					startActivity(next);
				}
			}
		});
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("brightness", "startOnResume");
		Log.i("brightness", "onResume      "+ curbrightness);
		if(curbrightness==true)
		{
			
			 WindowManager.LayoutParams layoutParams = getWindow().getAttributes();  
             layoutParams.screenBrightness = (float)curBrightnessValue;
             if(layoutParams.screenBrightness==0)
             {
                layoutParams.screenBrightness=(float) 0.01;
             }
             android.provider.Settings.System.putInt(getContentResolver(),
                     android.provider.Settings.System.SCREEN_BRIGHTNESS, (int) layoutParams.screenBrightness);
             getWindow().setAttributes(layoutParams);
     		Log.i("brightness", "lets go");
     		curbrightness=false;
		}
	}
}
