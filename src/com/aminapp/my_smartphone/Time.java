package com.aminapp.my_smartphone;

import java.util.Calendar;

import com.aminapp.auto_tasks.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Time extends Activity{


	public static int hour,min;;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.time);

	final EditText Hour=(EditText) findViewById(R.id.Ehour);
	final EditText Min=(EditText) findViewById(R.id.Emin);
	Button Ok=(Button) findViewById(R.id.btnSave);
	
	final SharedPreferences.Editor editor=G.shPreferences.edit();

	Ok.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			if(!(Hour.getText().length()==0) && !(Min.getText().length()==0))
			{
				hour=Integer.valueOf(Hour.getText().toString());
				min=Integer.valueOf(Min.getText().toString());

				if(hour>=0 && hour<=23 && min>=0 && min<=59)
				{
					Calendar calendar;
					calendar= Calendar.getInstance();
					calendar.set(calendar.YEAR, calendar.get(Calendar.YEAR));
					calendar.set(calendar.MONTH, calendar.get(Calendar.MONTH));
					if(hour>=calendar.get(Calendar.HOUR_OF_DAY) && min>=calendar.get(Calendar.MINUTE))
					{
						calendar.set(calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
					}else
						calendar.set(calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH)+1));
					calendar.set(calendar.HOUR_OF_DAY, hour);
					calendar.set(calendar.MINUTE, min);
					calendar.set(calendar.SECOND, 0);
					
					editor.putLong("Calendar Value", calendar.getTimeInMillis());
					editor.commit();
					
					Intent myintent = new Intent(G.context, SendToAllTask.class);
			        myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        PendingIntent pendingIntent = PendingIntent.getService(G.context, 0, myintent, 0);
			        G.alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
			        
					Intent intent=new Intent(Time.this,State.class);
					Time.this.startActivity(intent);
					Time.this.finish();
				}
				else
				Toast.makeText(G.context, "زمان نامعتبر است", Toast.LENGTH_SHORT).show()	;
			}else
				Toast.makeText(G.context, "زمان وارد نشده است", Toast.LENGTH_SHORT).show()	;

		}
	});
 }

}
