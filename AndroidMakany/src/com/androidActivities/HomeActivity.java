package com.androidActivities;


import com.controllers.Application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	String currentEmail ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Button addPost = (Button) findViewById(R.id.addPostButton);
		Button events =  (Button) findViewById(R.id.eventsButton);
		Button items =  (Button) findViewById(R.id.itemsButton);
		
		currentEmail = Application.getUserEmail();
		
		Toast.makeText(getApplicationContext(),
		"Welocome User!\nYour Email is: " + currentEmail , Toast.LENGTH_LONG).show();
		

	 events.setOnClickListener(new OnClickListener() 
     {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent eventsIntent = new Intent(getApplicationContext(),EventsMenuActivity.class);
				eventsIntent.putExtra("email", currentEmail);
				startActivity(eventsIntent);
				
			}
		});

	
	 addPost.setOnClickListener(new OnClickListener() 
     {
	
		@Override
		public void onClick(View v) 
		{
			Intent postIntent = new Intent(Application.getAppContext(),PostActivity.class);
			postIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			postIntent.putExtra("email", currentEmail);
			Application.getAppContext().startActivity(postIntent);
			
			
		}
		});
	 
	 
	 items.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent itemIntent = new Intent(getApplicationContext(),ItemsMenuActivity.class);
			itemIntent.putExtra("email", currentEmail);
			startActivity(itemIntent);
			
		}
	});
	}

}
