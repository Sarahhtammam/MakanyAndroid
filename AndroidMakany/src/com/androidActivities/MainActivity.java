package com.androidActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.controllers.AdminController;
import com.controllers.Application;
import com.controllers.UserController;

public class MainActivity extends Activity implements OnClickListener
{

	
	Button login;
	Button signUp;
	Button signUpStore;
	Button test; //to be removed
	ArrayList<String> tempInterests = new ArrayList<String>();;
	ArrayList<String> tempDistricts = new ArrayList<String>();
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		login = (Button) findViewById(R.id.login);
		signUp = (Button) findViewById(R.id.signUp);
		signUpStore = (Button) findViewById(R.id.signUpStore);
		test = (Button) findViewById(R.id.test);
		 
		login.setOnClickListener(this); 
		signUp.setOnClickListener(this);     
		signUpStore.setOnClickListener(this);
		test.setOnClickListener(this);
  
		
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		if (!pref.getString("email", "").equals(""))
		{
			UserController userController = new UserController();
			userController.getUser(pref.getString("email", ""));
			
			Application.setUserEmail(pref.getString("email", ""));
			
			Intent homeIntent = new Intent(Application.getAppContext(),HomeActivity.class);
			homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Application.getAppContext().startActivity(homeIntent);
		}
    }
    
	
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{

		    case R.id.login:
		    {
	    		Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(loginIntent);
				break;
			}
	
		    case R.id.signUp:
		    {
		    	AdminController adminController = new AdminController();
				adminController.getInterests();
				adminController.getDistricts();
				break;
			}
	
		    case R.id.signUpStore:
		    {
		    	break;
		    }
		    
		    case R.id.test:
		    {
	    		Intent testIntent = new Intent(getApplicationContext(),TestActivity.class);
				startActivity(testIntent);
				
				
				break;
		    }
		    
		    default:
		    {    
		    	break;
		    }
	    
		}

		
		
	}


}
