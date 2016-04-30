package com.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.controllers.EventController;
import com.controllers.ItemController;


public class CreateItemActivity extends Activity implements OnClickListener{

	EditText itemnameEditText;
	EditText descriptionEditText;
	EditText usermailEditText;
	EditText categoryEditText;
	Button request;
	String currentEmail ="";

	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_item);
		
		itemnameEditText = (EditText) findViewById(R.id.name);
		descriptionEditText=(EditText) findViewById(R.id.description);
		usermailEditText = (EditText) findViewById(R.id.usermail);
		categoryEditText = (EditText) findViewById(R.id.category);
		request= (Button) findViewById(R.id.request);
		
		Intent currentIntent = getIntent();
		currentEmail = currentIntent.getStringExtra("email");
		
		request.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ItemController itemController = new ItemController();
		itemController.createLoanItem(itemnameEditText.getText().toString(), descriptionEditText.getText().toString(), 
		currentEmail, "zamalek","","1","art");
		
		
	}

}
