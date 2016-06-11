package com.androidActivities;

import com.controllers.Application;
import com.controllers.UserController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StoreHomeActivity extends Activity {
	
	String currentEmail ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_home);
		
		Button addOffer  = (Button) findViewById(R.id.addOffer);
		Button editOffer =  (Button) findViewById(R.id.editOffer);
		Button removeOffer =  (Button) findViewById(R.id.removeOffer);
		
		currentEmail = Application.getCurrentEmail();
		Toast.makeText(getApplicationContext(),
		"Welocome User!\nYour Email is: " + currentEmail , Toast.LENGTH_LONG).show();
				
				
		UserController userController = Application.getUserController();
			
		if (userController == null)
		{
			Toast.makeText(getApplicationContext(), "null! ", Toast.LENGTH_LONG).show();
		}
		
		else
		{
			if(!Application.loggedIn)
			{
				userController.getUser(currentEmail);
				Application.loggedIn = true;
				
				Toast.makeText(getApplicationContext(),
						"first visit to homepage", Toast.LENGTH_LONG).show();
						
			}
		}
	
		
		

	 addOffer.setOnClickListener(new OnClickListener() 
     {
		 @Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent eventsIntent = new Intent(getApplicationContext(),AddOfferActivity.class);
				startActivity(eventsIntent);
				
			}
			
			});

	 editOffer.setOnClickListener(new OnClickListener() 
     {
		 @Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				/*Intent eventsIntent = new Intent(getApplicationContext(),editOfferActivity.class);
				startActivity(eventsIntent);*/
				
			}
			
			});
	 
	 
	 removeOffer.setOnClickListener(new OnClickListener() 
     {
		 @Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				/*Intent eventsIntent = new Intent(getApplicationContext(),removeOfferActivity.class);
				startActivity(eventsIntent);*/
				
			}
			
			});



}
}