package com.androidActivities;

import com.controllers.Application;
import com.controllers.EventController;

import SimpleModels.SimpleEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SingleEventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_event);
	
		//singleEventLayout
		Intent currentIntent = getIntent();
		String currentEventID= currentIntent.getStringExtra("eventID");
		Toast.makeText(Application.getAppContext(), "Event id is: " + currentEventID,
				Toast.LENGTH_LONG).show();
	
		
		//LinearLayout my_layout = (LinearLayout)findViewById(R.id.singleEventLayout);
		
		SimpleEvent event = Application.getCurrentEvent();
		
		if (event==null)
			Toast.makeText(Application.getAppContext(), "NULLLL",
					Toast.LENGTH_LONG).show();
		
		else
			Toast.makeText(Application.getAppContext(), "Event name issssss: " + event.getName(),
				Toast.LENGTH_LONG).show();
	
	
	}
}
