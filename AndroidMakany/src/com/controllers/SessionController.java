package com.controllers;

import java.util.ArrayList;

import com.androidActivities.LoginActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class SessionController {

	public static void login()
	{
		Application.loggedIn = true;
		SharedPreferences pref = Application.getAppContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		//on the login store the login
		editor.putString("email", Application.getUserEmail()); 
		editor.putString("type", Application.getCurrentUserType()); 
		editor.commit();	
	}
	
	public static void signout()
	{
		Application.loggedIn = false;
		SharedPreferences pref = Application.getAppContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		editor.remove("email");
		editor.remove("type");
		editor.commit();
		
		Intent login = new Intent(Application.getAppContext(),LoginActivity.class);
			
		login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Application.getAppContext().startActivity(login);
	}
	
	public static void getPredefined()
	{
		AdminController adminController = new AdminController();
	    adminController.getCategories();
	    adminController.getDistricts();
	    Application.setHavePredefined(true);
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
	
	public static void ChangeDistrict(Context mContext)
	{
		final ArrayList<Integer> mSelectedItems = new ArrayList();  // Where we track the selected items
		final String oldValue = Application.getCurrentDistrict();
		final CharSequence[] items = Application.getDistricts().toArray(new CharSequence[Application.getDistricts().size()]);
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
	    // Set the dialog title
	    builder.setTitle("Choose District");
	    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item)
            {
            	Application.setCurrentDistrict(Application.getDistricts().get(item));
            }
	    });
	    
	    builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Toast.makeText(Application.getAppContext(), "District changed to " + Application.getCurrentDistrict(),
    					Toast.LENGTH_SHORT).show();

                
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Application.setCurrentDistrict(oldValue);
            }
        });
	    
	    builder.show();
	}

}
