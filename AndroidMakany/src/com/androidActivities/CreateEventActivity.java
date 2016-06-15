package com.androidActivities;

import com.controllers.Application;
import com.controllers.EventController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateEventActivity extends Activity {

	EditText eventName;
	EditText eventCategory;
	EditText eventDescription;
	
	String currentEmail ="";
	String currentDistrict ="None";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		
		eventName = (EditText) findViewById(R.id.eventName);
		eventCategory =(EditText) findViewById(R.id.eventCategory);
		eventDescription = (EditText) findViewById(R.id.eventDescription);
		
		currentEmail =Application.getUserEmail();
		currentDistrict =Application.getCurrentUser().getDistrict();
		
		
		Button createEventButton = (Button) findViewById(R.id.createEventButton);
		
		 createEventButton.setOnClickListener(new OnClickListener() 
         {
 			
 			@Override
 			public void onClick(View arg0) 
 			{
 				//TODO Auto-generated method stub
 				if (eventName.getText().toString().trim().equals(""))
 				{
 					eventName.setError( "Event name is required!" );
 					
 				}
 				else if (eventCategory.getText().toString().trim().equals(""))
 				{
 					eventCategory.setError( "Event Category is required!" );
 					
 				}
 				else
 				{
 					EventController eventController = new EventController();
 					eventController.createEvent(eventName.getText().toString(), eventCategory.getText().toString(), 
 					eventDescription.getText().toString(), Integer.toString(0), Integer.toString(0), currentEmail, currentDistrict);

 				}
 				
 			}
 		});
       
	}
}
