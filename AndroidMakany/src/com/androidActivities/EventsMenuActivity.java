package com.androidActivities;

import com.controllers.EventController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventsMenuActivity extends Activity 
{
	String currentEmail ="";

	Button createEventButton, showMyEvents; //to be removed
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
		  super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_events_menu);
	      createEventButton = (Button) findViewById(R.id.createEventButton);
  	      showMyEvents = (Button) findViewById(R.id.getMyEventsButton);
	      
	      Intent currentIntent = getIntent();
		  currentEmail = currentIntent.getStringExtra("email");
		
          
          createEventButton.setOnClickListener(new OnClickListener() 
          {
  			
  			@Override
  			public void onClick(View arg0) 
  			{
  				// TODO Auto-generated method stub
  				Intent createEventIntent = new Intent(getApplicationContext(),CreateEventActivity.class);
  				createEventIntent.putExtra("email", currentEmail);
  				startActivity(createEventIntent);
  				
  			}
  		});
          
          showMyEvents.setOnClickListener(new OnClickListener() 
          {
  			
  			@Override
  			public void onClick(View arg0) 
  			{
  				EventController eventController = new EventController();
  				eventController.getGoingEvents(currentEmail, "");
  				
  			}
  		});
     
    }
}



