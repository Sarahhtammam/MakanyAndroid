package com.androidActivities;

import java.util.ArrayList;
import java.util.Vector;

import com.controllers.AdminController;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.PostController;
import com.controllers.Prepare_SignUp;
import com.controllers.UserController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class TestActivity extends Activity implements OnClickListener, Prepare_SignUp  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		Button B = (Button)findViewById(R.id.testButton);
		B.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) 
	{
		//PostController controllers = new PostController();

		//controllers.getPost("art", "maadi", "");
		
		AdminController adminController = new AdminController();
		adminController.getInterests(this);
		adminController.getDistricts(this);
		
		//Toast.makeText(getApplicationContext(),interests.get(0), Toast.LENGTH_LONG).show();
				
	}

	
	@Override
	public void collectInterests(ArrayList<String> output) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),"hii Interestssss      " + output.get(0), Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void collectDistricts(ArrayList<String> output) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),"hii Districts      " + output.get(0), Toast.LENGTH_LONG).show();
	}	
	
	/*@Override
	public void processFinish(String output) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),"hii " + output, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void processFinish2(String output) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),"districts " + output, Toast.LENGTH_LONG).show();
		
	}*/


}
