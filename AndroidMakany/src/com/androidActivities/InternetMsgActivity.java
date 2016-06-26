package com.androidActivities;

import com.controllers.Application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;


public class InternetMsgActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internet_msg);
		showSettingsAlert();
	}
	
	public void showSettingsAlert(){
	    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
	  
	    // Setting Dialog Title
	    alertDialog.setTitle("No Internet");

	    // Setting Dialog Message
	    alertDialog.setMessage("There is no internet conncection. Do you want to go to settings menu?");
	    
	    // On pressing Settings button
	    alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog,int which) {
	        	
	            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
	            startActivity(intent);
	        }
	    });
	    

	    // on pressing cancel button
	    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        dialog.cancel();
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	        System.exit(0);
	        
	       

	        }
	    });

	    // Showing Alert Message
	    alertDialog.show();
	}

}
