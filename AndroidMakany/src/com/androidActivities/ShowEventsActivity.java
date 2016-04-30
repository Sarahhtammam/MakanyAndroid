package com.androidActivities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SimpleModels.SimpleEvent;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.EventController;

public class ShowEventsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_events);
		
		ArrayList<SimpleEvent> events = new ArrayList<SimpleEvent>(); 
		Map<Integer, String> eventsIDs = new HashMap<Integer, String>();
		        
		
		events = Application.getEvents();
		//System.out.println("Events1: " + events.get(0).getName());
		//System.out.println("Events2: " + events.get(0).getName());
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.userEventsLayout);
		
		
		//loop of generation of check boxes
		for (int i = 0; i < events.size(); i++) 
		{
			final SimpleEvent temp = events.get(i);
			
			TextView name = new TextView(this);
	        name.setText("Event Name: " + temp.getName() );
	        my_layout.addView(name); 

	        
	        TextView category = new TextView(this);
	        category.setText("Event Category: " + temp.getCategory() );
	        my_layout.addView(category); 
	       

	        Button b = new Button(this);
	        b.setText("View event details");
	        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        b.setId(i+1);
	        b.setTag(temp.getID());
	        b.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) 
	            {
    	         	Intent selectedEvent = new Intent(Application.getAppContext(),SingleEventActivity.class);
	  				selectedEvent.putExtra("eventID", temp.getID());
	  				selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  				EventController eventController = new EventController();
	  				eventController.getEventByID(temp.getID());
	  				Application.setCurrentEvent(temp);
	  				Application.getAppContext().startActivity(selectedEvent);

	            }
	        });
	        my_layout.addView(b);
	        
	        eventsIDs.put(i, temp.getID());
		
		
	    
		
		
		
		}
		
	}
	
	
	
}
