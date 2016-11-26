package com.aminapp.my_smartphone;

import java.util.Calendar;

import com.aminapp.auto_tasks.R;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class Task extends Activity{
	PendingIntent pendingIntent;
	 Calendar calendar;

	public int Eh,Em,Dh,Dm ;
	public boolean check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task);

	    
		final Menu menu=new Menu();
		final int counter=G.shPreferences.getInt("Counter", 0);	

		 final EditText Ehour = (EditText) findViewById(R.id.Ehour);
		 final EditText Emin = (EditText) findViewById(R.id.Emin);
		 final EditText Dhour = (EditText) findViewById(R.id.Dhour);
		 final EditText Dmin = (EditText) findViewById(R.id.Dmin);

	    final CheckBox cbT=(CheckBox) findViewById(R.id.cbTekrar);
	    final CheckBox cbD=(CheckBox) findViewById(R.id.cbDisable);
	    final CheckBox cbE=(CheckBox) findViewById(R.id.cbEnable);

	    final Button agree=(Button) findViewById(R.id.btnAgree);
	        
	       
	    
	   
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

}
else
{
	//Ehour.setFocusable(false);
	Ehour.setEnabled(false);
	Ehour.setBackgroundResource(R.drawable.edt_disable);
	Emin.setEnabled(false);
	Emin.setBackgroundResource(R.drawable.edt_disable);
	//Emin.setFocusable(false);

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

}
else
{
	//Dhour.setFocusable(false);
	Dhour.setEnabled(false);
	Dhour.setBackgroundResource(R.drawable.edt_disable);
	Dmin.setEnabled(false);
	Dmin.setBackgroundResource(R.drawable.edt_disable);
    //Dmin.setFocusable(false);

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
							
							

							switch(counter)
							{
							case(1):
								SharedPreferences.Editor editor1=G.shPreferences.edit();
							//save time in sharedpreferences for alarm
							editor1.putLong("Wifi Enable", calendar.getTimeInMillis());
							//save time in sharedpreferences for dialog
							editor1.putInt("Wifi Enable Hour", Eh);
							editor1.putInt("Wifi Enable Min", Em);
							//check wifi for enable or disable for Wifi class
							editor1.putBoolean("Wifi ECheck", true);
								Intent intent1 = new Intent(G.context, Wifi.class);
					        PendingIntent pendingIntent1 = PendingIntent.getService(G.context, 0, intent1, 0);
					        if(cbT.isChecked())
					        {
					        	//save repeat enable
					        	editor1.putBoolean("Wifi Eday", true);
					        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
					        }else
					        {
					        	editor1.putBoolean("Wifi E", true);
					        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent1);
					        }
							editor1.commit();

								break;
                            case(2):
                            	SharedPreferences.Editor editor2=G.shPreferences.edit();
                          //save time in sharedpreferences for alarm
            				editor2.putLong("Mdata Enable", calendar.getTimeInMillis());
							//save time in sharedpreferences for dialog
            				editor2.putInt("Mdata Enable Hour", Eh);
							editor2.putInt("Mdata Enable Min", Em);
							//check wifi for enable or disable for MobileData class
							editor2.putBoolean("Mdata ECheck", true);
                            	Intent intent2 = new Intent(G.context, MobileData.class);
					        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					        PendingIntent pendingIntent2 = PendingIntent.getService(G.context, 0, intent2, 0);
					        if(cbT.isChecked())
					        {
					        	//save repeat enable
					        	editor2.putBoolean("Mdata Eday", true);
					        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
					        }else
					        {
					        	editor2.putBoolean("Mdata E", true);
					        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
					        }
            				editor2.commit();
								break;
                            case(3):
                            	SharedPreferences.Editor editor3=G.shPreferences.edit();
                            //save time in sharedpreferences for alarm
            				editor3.putLong("Silent Enable", calendar.getTimeInMillis());
							//save time in sharedpreferences for dialog
            				editor3.putInt("Silent Enable Hour", Eh);
							editor3.putInt("Silent Enable Min", Em);
							//check wifi for enable or disable for Silent class
							editor3.putBoolean("Silent ECheck", true);
                            	Intent intent3 = new Intent(G.context, Silent.class);
					        intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					        PendingIntent pendingIntent3 = PendingIntent.getService(G.context, 0, intent3,0);
					        if(cbT.isChecked())
					        {
					        	//save repeat enable
								editor3.putBoolean("Silent Eday", true);
					        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent3);
					        }else
					        {
								editor3.putBoolean("Silent E", true);
					        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent3);
					        }
            				editor3.commit();
	                            break;
                            case(4):
                            	SharedPreferences.Editor editor4=G.shPreferences.edit();
                            //save time in sharedpreferences for alarm
            				editor4.putLong("Vibrate Enable", calendar.getTimeInMillis());
							//save time in sharedpreferences for dialog
            				editor4.putInt("Vibrate Enable Hour", Eh);
							editor4.putInt("Vibrate Enable Min", Em);
							//check wifi for enable or disable for Vibrate class
							editor4.putBoolean("Vibrate ECheck", true);
                            	Intent intent4 = new Intent(G.context, Vibrate.class);
					        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					        PendingIntent pendingIntent4 = PendingIntent.getService(G.context, 0, intent4, 0);
					        if(cbT.isChecked())
					        {
					        	//save repeat enable								editor4.putBoolean("Vibrate Eday", true);
					        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent4);
					        }else
					        {
								editor4.putBoolean("Vibrate E", true);
					        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent4);
					        }
            				editor4.commit();
	                            break;
							
							}

				        }
				        else
				        	Toast.makeText(G.context, " زمان نامعتبر است ", Toast.LENGTH_SHORT).show();
				        }else
				        	Toast.makeText(G.context, " زمان وارد نشده است ", Toast.LENGTH_SHORT).show();
				 }
				 else
				 {
						SharedPreferences.Editor editor=G.shPreferences.edit();
					 switch(counter)
					 {
					 case(1):
						 editor.putBoolean("Wifi ECheck", false);
			        	editor.putBoolean("Wifi Eday", false);
						editor.putInt("Wifi Enable Hour", 100);
						editor.putInt("Wifi Enable Min", 100);
						 break;
					 case(2):
						 editor.putBoolean("Mdata ECheck", false);
			        	editor.putBoolean("Mdata Eday", false);
						editor.putInt("Mdata Enable Hour", 100);
						editor.putInt("Mdata Enable Min", 100);
						 break;
					 case(3):
							editor.putBoolean("Silent ECheck", false);
							editor.putBoolean("Silent Eday", false);
							editor.putInt("Silent Enable Hour", 100);
							editor.putInt("Silent Enable Min", 100);
						 break;
					 case(4):
						 editor.putBoolean("Vibrate ECheck", false);
						editor.putBoolean("Vibrate Eday", false);
						editor.putInt("Vibrate Enable Hour", 100);
						editor.putInt("Vibrate Enable Min", 100);
						 break;
					 }
						editor.commit();

						
						
						
						
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

				

				switch(counter)
				{
				case(1):
					SharedPreferences.Editor editor1=G.shPreferences.edit();
                //save time in sharedpreferences for alarm
				editor1.putLong("Wifi Disable", calendar.getTimeInMillis());
				//save time in sharedpreferences for dialog
				editor1.putInt("Wifi Disable Hour", Dh);
				editor1.putInt("Wifi Disable Min", Dm);
				//check wifi for enable or disable for Wifi class
				editor1.putBoolean("Wifi DCheck", true);
					Intent intent1 = new Intent(G.context, Wifi.class);
		        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        PendingIntent pendingIntent1 = PendingIntent.getService(G.context, 1, intent1, 0);
		        if(cbT.isChecked())
		        {
		        	//save repeat enable
					editor1.putBoolean("Wifi Dday", true);
		        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
		        }else
		        {
					editor1.putBoolean("Wifi D", true);
		        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent1);
		        }
				editor1.commit();
					break;
                case(2):
                	SharedPreferences.Editor editor2=G.shPreferences.edit();
                //save time in sharedpreferences for alarm 
				editor2.putLong("Mdata Disable", calendar.getTimeInMillis());
				//save time in sharedpreferences for dialog
				editor2.putInt("Mdata Disable Hour", Dh);
				editor2.putInt("Mdata Disable Min", Dm);
				//check wifi for enable or disable for MobileData class
				editor2.putBoolean("Mdata DCheck", true);
                	Intent intent2 = new Intent(G.context, MobileData.class);
		        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        PendingIntent pendingIntent2 = PendingIntent.getService(G.context, 1, intent2, 0);
		        if(cbT.isChecked())
		        {
		        	//save repeat enable
					editor2.putBoolean("Mdata Dday", true);
		        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
		        }else
		        {
					editor2.putBoolean("Mdata D", true);
		        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
		        }
				editor2.commit();
					break;
                case(3):
                	SharedPreferences.Editor editor3=G.shPreferences.edit();
                //save time in sharedpreferences for alarm
				editor3.putLong("Silent Disable", calendar.getTimeInMillis());
				//save time in sharedpreferences for dialog
				editor3.putInt("Silent Disable Hour", Dh);
				editor3.putInt("Silent Disable Min", Dm);
				//check wifi for enable or disable for Silent class
				editor3.putBoolean("Silent DCheck", true);
                	Intent intent3 = new Intent(G.context, Silent.class);
		        intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        PendingIntent pendingIntent3 = PendingIntent.getService(G.context, 1, intent3, 0);
		        if(cbT.isChecked())
		        {
		        	//save repeat enable
					editor3.putBoolean("Silent Dday", true);
		        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent3);
		        }else
		        {
					editor3.putBoolean("Silent D", true);
		        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent3);
		        }
				editor3.commit();
                    break;
                case(4):
                	SharedPreferences.Editor editor4=G.shPreferences.edit();
                //save time in sharedpreferences for alarm
				editor4.putLong("Vibrate Disable", calendar.getTimeInMillis());
				//save time in sharedpreferences for dialog
				editor4.putInt("Vibrate Disable Hour", Dh);
				editor4.putInt("Vibrate Disable Min", Dm);
				//check wifi for enable or disable for Vibrate class
				editor4.putBoolean("Vibrate DCheck", true);
                	Intent intent4 = new Intent(G.context, Vibrate.class);
		        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        PendingIntent pendingIntent4 = PendingIntent.getService(G.context, 1, intent4, 0);
		        if(cbT.isChecked())
		        {
		        	//save repeat enable
					editor4.putBoolean("Vibrate Dday", true);
		        	G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent4);
		        }else
		        {
					editor4.putBoolean("Vibrate D", true);
		        G.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent4);
		        }
				editor4.commit();
                    break;
				
                    
				}
				
	        }
	        else
				Toast.makeText(G.context, " زمان نامعتبر است ", Toast.LENGTH_SHORT).show();	
		
		}else
        	Toast.makeText(G.context, " زمان وارد نشده است ", Toast.LENGTH_SHORT).show();
		}
		else
		{
			SharedPreferences.Editor editor=G.shPreferences.edit();
			switch(counter)
			{
			case(1):
				editor.putBoolean("Wifi DCheck", false);
			editor.putBoolean("Wifi Dday", false);
			editor.putInt("Wifi Disable Hour", 100);
			editor.putInt("Wifi Disable Min", 100);
				break;
			case(2):
				editor.putBoolean("Mdata DCheck", false);
			editor.putBoolean("Mdata Dday", false);
			editor.putInt("Mdata Disable Hour", 100);
			editor.putInt("Mdata Disable Min", 100);
				break;
			case(3):
				editor.putBoolean("Silent DCheck", false);
			editor.putBoolean("Silent Dday", false);
			editor.putInt("Silent Disable Hour", 100);
			editor.putInt("Silent Disable Min", 100);
				break;
			case(4):
				editor.putBoolean("Vibrate DCheck", false);
				editor.putBoolean("Vibrate Dday", false);
				editor.putInt("Vibrate Disable Hour", 100);
				editor.putInt("Vibrate Disable Min", 100);
				break;
			}
			editor.commit();
					
					
					
					
					
					
			
		}
    	Task.this.finish();

							
			}
		});
	}//onCreate
}
