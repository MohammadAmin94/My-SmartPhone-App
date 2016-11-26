package com.aminapp.my_smartphone;

import java.util.Calendar;

import com.aminapp.auto_tasks.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrightnessTask extends Activity{

	PendingIntent pendingIntent;
	 Calendar calendar;

	public int Eh,Em,Dh,Dm ;
	public boolean check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brightness_task);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Menu menu=new Menu();

		final SharedPreferences.Editor editor=G.shPreferences.edit();
	 	
		 final EditText Ehour = (EditText) findViewById(R.id.Ehour);
		 final EditText Emin = (EditText) findViewById(R.id.Emin);
		 final EditText Dhour = (EditText) findViewById(R.id.Dhour);
		 final EditText Dmin = (EditText) findViewById(R.id.Dmin);

	    final CheckBox cbT=(CheckBox) findViewById(R.id.cbTekrar);
	    final CheckBox cbD=(CheckBox) findViewById(R.id.cbDisable);
	    final CheckBox cbE=(CheckBox) findViewById(R.id.cbEnable);

	    final SeekBar low=(SeekBar) findViewById(R.id.SBlow);
	    final SeekBar high=(SeekBar) findViewById(R.id.SBhigh);
	    
	    Button agree=(Button) findViewById(R.id.btnAgree);
	    
	    menu.curbrightness=true;
	    Log.i("brightness", "" +menu.curbrightness);

	    
		 high.setMax(255);
	       float H_curBrightnessValue = 0;

	       try {
	                H_curBrightnessValue = android.provider.Settings.System.getInt(
	                getContentResolver(),
	                android.provider.Settings.System.SCREEN_BRIGHTNESS);
	           } catch (SettingNotFoundException e) {
	           e.printStackTrace();
	           }

	    int H_screen_brightness = (int) H_curBrightnessValue;
	    high.setProgress(H_screen_brightness);
	    high.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
                    editor.putFloat("Brightness Enable Light", layoutParams.screenBrightness);
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
	    
	    
	    low.setMax(255);
	       float L_curBrightnessValue = 0;

	       try {
	                L_curBrightnessValue = android.provider.Settings.System.getInt(
	                getContentResolver(),
	                android.provider.Settings.System.SCREEN_BRIGHTNESS);
	           } catch (SettingNotFoundException e) {
	           e.printStackTrace();
	           }

	    int L_screen_brightness = (int) L_curBrightnessValue;
	    low.setProgress(L_screen_brightness);
	    low.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
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
                 editor.putFloat("Brightness Disable Light", layoutParams.screenBrightness);

	            }
	            
	            
	        }

	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	        }

	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {
	          
	        }
	    });
	    
 cbE.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
if(isChecked)
{
	Ehour.setEnabled(true);
	Ehour.setBackgroundResource(R.drawable.edt_shape);
	//Ehour.setFocusable(true);
	Emin.setEnabled(true);
	Emin.setBackgroundResource(R.drawable.edt_shape);
	//Emin.setFocusable(true);
	high.setEnabled(true);

}
else
{
	//Ehour.setFocusable(false);
	Ehour.setEnabled(false);
	Ehour.setBackgroundResource(R.drawable.edt_disable);
	Emin.setEnabled(false);
	Emin.setBackgroundResource(R.drawable.edt_disable);
	//Emin.setFocusable(false);
	high.setEnabled(false);


}
			}
		});
        
cbD.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
if(isChecked)
{
	Dhour.setEnabled(true);
	Dhour.setBackgroundResource(R.drawable.edt_shape);
	//Dhour.setFocusable(true);
	Dmin.setEnabled(true);
	Dmin.setBackgroundResource(R.drawable.edt_shape);
	//Dmin.setFocusable(true);
	low.setEnabled(true);

}
else
{
	//Dhour.setFocusable(false);
	Dhour.setEnabled(false);
	Dhour.setBackgroundResource(R.drawable.edt_disable);
	Dmin.setEnabled(false);
	Dmin.setBackgroundResource(R.drawable.edt_disable);
    //Dmin.setFocusable(false);
	low.setEnabled(false);

}
			}
		});

agree.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {

		if(cbE.isChecked())
		 {
			 Eh = Integer.valueOf(Ehour.getText().toString());
		        Em = Integer.valueOf(Emin.getText().toString());
		        if(Eh!=0 && Em!=0)
		        {
		        if(Eh>=0 && Eh<=23 && Em>=0 && Em<=59)
		        {
		        	calendar= Calendar.getInstance();
					calendar.set(calendar.YEAR, calendar.get(Calendar.YEAR));
					calendar.set(calendar.MONTH, calendar.get(Calendar.MONTH));
					if(Eh>=calendar.get(Calendar.HOUR_OF_DAY) && Em>=calendar.get(Calendar.MINUTE))
					{
						calendar.set(calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
					}else
						calendar.set(calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH)+1));
					calendar.set(calendar.HOUR_OF_DAY, Eh);
					calendar.set(calendar.MINUTE, Em);
					calendar.set(calendar.SECOND, 0);
					
				
		        
                    //save time in sharedpreferences for alarm
    				editor.putLong("Brightness Enable", calendar.getTimeInMillis());
					//save time in sharedpreferences for dialog
    				editor.putInt("Brightness Enable Hour", Eh);
					editor.putInt("Brightness Enable Min", Em);
					//check wifi for enable or disable for Brightness class
					editor.putBoolean("Brightness ECheck", true);
                    	Intent intent5 = new Intent(G.context, SendToBrightness.class);
			        intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        PendingIntent pendingIntent5 = PendingIntent.getService(G.context, 0, intent5, 0);
			        if(cbT.isChecked())
			        {
			        	//save repeat enable
						editor.putBoolean("Brightness Eday", true);
			        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent5);
			        }else
			        {
						editor.putBoolean("Brightness E", true);
			        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent5);
			        }
    				editor.commit();
		        }}
		        }
		else{
			editor.putBoolean("Brightness ECheck", false);
			editor.putBoolean("Brightness Eday", false);
			editor.putInt("Brightness Enable Hour", 100);
			editor.putInt("Brightness Enable Min", 100);
			}
		if(cbD.isChecked())
		{
			Dh = Integer.valueOf(Dhour.getText().toString());
	        Dm = Integer.valueOf(Dmin.getText().toString());
	        if(Dh!=0 && Dm!=0)
	        {
	        if(Dh>=0 && Dh<=23 && Dm>=0 && Dm<=59)
	        {
	        	calendar= Calendar.getInstance();
				calendar.set(calendar.YEAR, calendar.get(Calendar.YEAR));
				calendar.set(calendar.MONTH, calendar.get(Calendar.MONTH));
				if(Dh>=calendar.get(Calendar.HOUR_OF_DAY) && Dm>=calendar.get(Calendar.MINUTE))
				{
					calendar.set(calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
				}else
					calendar.set(calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH)+1));
				calendar.set(calendar.HOUR_OF_DAY, Dh);
				calendar.set(calendar.MINUTE, Dm);
				calendar.set(calendar.SECOND, 0);
				
				 
	        
                //save time in sharedpreferences for alarm
				editor.putLong("Brightness Disable", calendar.getTimeInMillis());
				//save time in sharedpreferences for dialog
				editor.putInt("Brightness Disable Hour", Dh);
				editor.putInt("Brightness Disable Min", Dm);
				//check wifi for enable or disable for Brightness class
				editor.putBoolean("Brightness DCheck", true);
                	Intent intent5 = new Intent(G.context, SendToBrightness.class);
		        intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        PendingIntent pendingIntent5 = PendingIntent.getService(G.context, 1, intent5, 0);
		        if(cbT.isChecked())
		        {
		        	//save repeat enable
					editor.putBoolean("Brightness Dday", true);
		        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent5);
		        }else
		        {
					editor.putBoolean("Brightness D", true);
		        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent5);
		        }
				editor.commit();
	        }
	        }
		}//if
		else{
			editor.putBoolean("Brightness DCheck", false);
			editor.putBoolean("Brightness Dday", false);
			editor.putInt("Brightness Disable Hour", 100);
			editor.putInt("Brightness Disable Min", 100);
		}
		editor.commit();
		BrightnessTask.this.finish();

	}

});
	}
}
