package com.aminapp.my_smartphone;

import com.aminapp.auto_tasks.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class State extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.all_task);
	
	Menu menu=new Menu();

	final ImageView Wifi=(ImageView) findViewById(R.id.WifiIcon);
	final ImageView Mdata=(ImageView) findViewById(R.id.MdataIcon);
	final ImageView Vibrate=(ImageView) findViewById(R.id.VibrateIcon);
	final ImageView Silent=(ImageView) findViewById(R.id.SilentIcon);
	final ImageView Light=(ImageView) findViewById(R.id.LightIcon);
	
	final SeekBar SBLight=(SeekBar) findViewById(R.id.SBLight);
	Button Save=(Button) findViewById(R.id.btnSave);
	
	final Dialog dialog=new Dialog(State.this);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	dialog.setContentView(R.layout.choice_dialog);
	final RadioButton noun=(RadioButton) dialog.findViewById(R.id.radio0);
	final RadioButton on=(RadioButton) dialog.findViewById(R.id.radio1);
	final RadioButton off=(RadioButton) dialog.findViewById(R.id.radio2);
	final Button dialogSave =(Button) dialog.findViewById(R.id.btnsave);

	final SharedPreferences.Editor editor=G.shPreferences.edit();
/*
	try {
        menu.curBrightnessValue=android.provider.Settings.System.getInt(
       getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
       Log.i("brightness",""+ menu.curBrightnessValue);
	    menu.curbrightness=true;
		Log.i("brightness", ""+ menu.curbrightness);
   } catch (SettingNotFoundException e) {
       e.printStackTrace();
   }
	*/
	

	Wifi.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dialog.show();
			dialogSave.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(on.isChecked())
					{
						editor.putBoolean("WifiOn", true);
					Wifi.setImageResource(R.drawable.wifi_on);
					editor.putBoolean("WifiOff", false);
					editor.putBoolean("WifiNoun", false);
						
					}
					if(off.isChecked())
					{
						editor.putBoolean("WifiOff", true);
						Wifi.setImageResource(R.drawable.wifi_off);
						editor.putBoolean("WifiOn", false);
						editor.putBoolean("WifiNoun", false);
					}
					if(noun.isChecked())
					{	editor.putBoolean("WifiNoun", true);
					Wifi.setImageResource(R.drawable.small_wifi);
					editor.putBoolean("WifiOn", false);
					editor.putBoolean("WifiOff", false);
					}
					editor.commit();
					dialog.dismiss();
				}
			});
		}
	});
	
	Mdata.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dialog.show();
dialogSave.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(on.isChecked())
						{editor.putBoolean("MdataOn", true);
						Mdata.setImageResource(R.drawable.mdata_on);
						editor.putBoolean("MdataOff", false);
						editor.putBoolean("MdataNoun", false);
					}
					if(off.isChecked())
						{editor.putBoolean("MdataOff", true);
						Mdata.setImageResource(R.drawable.mdata_off);
						editor.putBoolean("MdataOn", false);
						editor.putBoolean("MdataNoun", false);
					}
					if(noun.isChecked())
						{editor.putBoolean("MdataNoun", true);
						Mdata.setImageResource(R.drawable.small_mdata);
						editor.putBoolean("MdataOn", false);
						editor.putBoolean("MdataOff", false);
					}
					editor.commit();
					dialog.dismiss();
				}
			});
		}
	});
	
    Vibrate.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dialog.show();
dialogSave.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(on.isChecked())
						{editor.putBoolean("VibrateOn", true);
						Vibrate.setImageResource(R.drawable.vibrate_on);
						editor.putBoolean("VibrateOff", false);
						editor.putBoolean("VibrateNoun", false);
						
					}
					if(off.isChecked())
						{editor.putBoolean("VibrateOff", true);
						Vibrate.setImageResource(R.drawable.vibrate_off);
						editor.putBoolean("VibrateOn", false);
						editor.putBoolean("VibrateNoun", false);
					}
					if(noun.isChecked())
						{editor.putBoolean("VibrateNoun", true);
						Vibrate.setImageResource(R.drawable.small_vibrate);
						editor.putBoolean("VibrateOn", false);
						editor.putBoolean("VibrateOff", false);
						
					}
					editor.commit();
					dialog.dismiss();
				}
			});
		}
	});
    Silent.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		dialog.show();
		dialogSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(on.isChecked())
				{
					editor.putBoolean("SilentOn", true);
					Silent.setImageResource(R.drawable.silent_on);
					editor.putBoolean("SilentOff", false);
					editor.putBoolean("SilentNoun", false);
				
				}
				if(off.isChecked())
				{	editor.putBoolean("SilentOff", true);
				Silent.setImageResource(R.drawable.silent_off);
				editor.putBoolean("SilentOn", false);
				editor.putBoolean("SilentNoun", false);
			
				}
				if(noun.isChecked())
				{	editor.putBoolean("SilentNoun", true);
				Silent.setImageResource(R.drawable.small_silent);
				editor.putBoolean("SilentOn", false);
				editor.putBoolean("SilentOff", false);
				
				}
				editor.commit();
				dialog.dismiss();
			}
		});
	    }
    });
    Light.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		dialog.show();
		dialogSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(on.isChecked())
				{
					editor.putBoolean("LightOn", true);
					Light.setImageResource(R.drawable.light_on);
					editor.putBoolean("LightOff", false);
					editor.putBoolean("LightNoun", false);
				SBLight.setVisibility(View.VISIBLE);
				}
				if(off.isChecked())
				{
					editor.putBoolean("LightOff", true);
					Light.setImageResource(R.drawable.light_off);
					editor.putBoolean("LightOn", false);
					editor.putBoolean("LightNoun", false);
					SBLight.setVisibility(View.VISIBLE);
				}
				if(noun.isChecked()){
					editor.putBoolean("LightNoun", true);
				Light.setImageResource(R.drawable.small_light);
				editor.putBoolean("LightOn", false);
				editor.putBoolean("LightOff", false);
				SBLight.setVisibility(View.INVISIBLE);
				}
				editor.commit();
				dialog.dismiss();
			}
		});
	    }
    });
   // if(SBLight.getVisibility()==View.VISIBLE)
	
		SBLight.setMax(255);
	       float H_curBrightnessValue = 0;

	       try {
	                H_curBrightnessValue = android.provider.Settings.System.getInt(
	                getContentResolver(),
	                android.provider.Settings.System.SCREEN_BRIGHTNESS);
	           } catch (SettingNotFoundException e) {
	           e.printStackTrace();
	           }

	    int H_screen_brightness = (int) H_curBrightnessValue;
	    SBLight.setProgress(H_screen_brightness);
	    SBLight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	    int progress = 0;

	        @Override
	        public void onProgressChanged(SeekBar high, int progresValue,
	                boolean fromUser) {
	        	
	            progress = progresValue;
	            if (fromUser) {
	                android.provider.Settings.System.putInt(getContentResolver(),
	                        android.provider.Settings.System.SCREEN_BRIGHTNESS, progress);

	                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();  
	                layoutParams.screenBrightness = (float)progress / 255;
                 if(layoutParams.screenBrightness==0)
                 {
	                   layoutParams.screenBrightness=(float) 0.01;
                 }
                 getWindow().setAttributes(layoutParams);
                 editor.putFloat("Brightness Light", layoutParams.screenBrightness);
                 Log.i("Bright", "" +layoutParams.screenBrightness);
	            }
	            
	            
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	          
	        }
	    });
	editor.commit();
    Save.setOnClickListener(new OnClickListener(
    		) {
		
		@Override
		public void onClick(View v) {
			Menu menu=new Menu();
			menu.curbrightness=true;
			State.this.finish();
			
		}
	});

 }
}
