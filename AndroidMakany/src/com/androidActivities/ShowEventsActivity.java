package com.androidActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ShowEventsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_events);
		
		ArrayList<String> events = new ArrayList<String>();
		
		Intent currentIntent = getIntent();
		events = currentIntent.getStringArrayListExtra("events");
		
		System.out.println("Events: " + events);
	}
}
