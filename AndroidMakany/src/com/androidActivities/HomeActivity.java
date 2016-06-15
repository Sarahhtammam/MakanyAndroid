package com.androidActivities;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.SessionController;
import com.controllers.UserController;
import com.controllers.WhatsNewController;

public class HomeActivity extends MyDrawerMenu
{
	String currentEmail ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		super.onCreateDrawer();
		
		Button postMenu = (Button) findViewById(R.id.PostMenuButton);
		Button events =  (Button) findViewById(R.id.eventsButton);
		Button items =  (Button) findViewById(R.id.itemsButton);
		Button whatsNew =  (Button) findViewById(R.id.whatsNew);
		
		currentEmail = Application.getUserEmail();
		
		Toast.makeText(getApplicationContext(),
		"Welcome User!\nYour Email is: " + currentEmail , Toast.LENGTH_SHORT).show();
		
		UserController userController = new UserController();
		
		
		
		if(!Application.loggedIn)
		{
			userController.getUser(currentEmail);
			SessionController.login();		
		}
		


	 events.setOnClickListener(new OnClickListener() 
     {
			
			@Override
			public void onClick(View arg0) 
			{
				Intent eventsIntent = new Intent(getApplicationContext(),EventsMenuActivity.class);
				startActivity(eventsIntent);
				
			}
		});

	
	 postMenu.setOnClickListener(new OnClickListener() 
     {
	
		@Override
		public void onClick(View v) 
		{
			Intent postMenuIntent = new Intent(Application.getAppContext(),PostsMenuActivity.class);
			startActivity(postMenuIntent);
			
			
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
	 
	 whatsNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WhatsNewController whatsNewController = new WhatsNewController();
				whatsNewController.getDynamicRecommendation(currentEmail);

			}
		});
	}
	 


}
