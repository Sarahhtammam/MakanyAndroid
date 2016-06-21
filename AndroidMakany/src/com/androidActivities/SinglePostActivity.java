package com.androidActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.controllers.Application;

public class SinglePostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_post);
		
		//Toast.makeText(Application.getAppContext(),Application.getCurrentPost().getContent(),Toast.LENGTH_LONG).show();
	}
}
