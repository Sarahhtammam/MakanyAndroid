package com.androidActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.controllers.GPSTracker;

public class TestActivity extends Activity implements OnClickListener  {
	
	GPSTracker gps;
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
		/*Application.setUserEmail("magieda@hotmail.com");
		EventController eventController = new EventController();
			eventController.getGoingEvents("magiedaa@hotmail.com", "");*/
		
		 // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled     
        if(gps.canGetLocation()){
             
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
             
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
	}
	
}
