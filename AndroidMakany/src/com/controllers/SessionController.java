package com.controllers;

import com.androidActivities.LoginActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionController {

	public static void login()
	{
		Application.loggedIn = true;
		SharedPreferences pref = Application.getAppContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		//on the login store the login
		editor.putString("email", Application.getUserEmail()); 
		editor.commit();	
	}
	
	public static void signout()
	{
		Application.loggedIn = false;
		SharedPreferences pref = Application.getAppContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		editor.remove("email");
		editor.commit();
		
		Intent login = new Intent(Application.getAppContext(),LoginActivity.class);
			
		login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Application.getAppContext().startActivity(login);
	}
	
	
	public static void showSignoutDialog(Context mContext)
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        
        // Setting Dialog Title
        alertDialog.setTitle("Signout");
  
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to signout?");
  
        // On pressing Settings button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
            	signout();
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
	}

}
