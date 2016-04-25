package com.androidActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.controllers.EventController;

public class TestActivity extends Activity implements OnClickListener  {

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
		
		EventController eventController = new EventController();
			eventController.getGoingEvents("magiedaa@hotmail.com", "");
			
	}

	
}
