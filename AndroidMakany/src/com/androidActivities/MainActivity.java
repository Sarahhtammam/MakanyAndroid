package com.androidActivities;

import java.util.ArrayList;

import com.androidActivities.LoginActivity;
import com.androidActivities.R;
import com.controllers.AdminController;
import com.controllers.Prepare_SignUp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, Prepare_SignUp {

	
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
  
		AdminController adminController = new AdminController();
		adminController.getInterests(this);
		adminController.getDistricts(this);
    }
    
	@Override
    public void collectInterests(ArrayList<String> output) 
	{
		tempInterests = output;
	}
	
	@Override
	public void collectDistricts(ArrayList<String> output) 
	{
		tempDistricts = output;
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
				Intent signUpIntent = new Intent(getApplicationContext(),SignUpActivity.class);
				signUpIntent.putStringArrayListExtra("interestsArrayList", tempInterests);
				signUpIntent.putStringArrayListExtra("districtsArrayList", tempDistricts);
				startActivity(signUpIntent);
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
