package com.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.controllers.Application;
import com.controllers.ItemController;


public class CreateItemActivity extends Activity implements OnClickListener{

	EditText itemnameEditText;
	EditText descriptionEditText;
	EditText usermailEditText;
	EditText categoryEditText;
	Spinner itemTypeSpinner;
	Button request;
	String currentEmail ="";

	
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_item);
		
		itemnameEditText = (EditText) findViewById(R.id.name);
		descriptionEditText=(EditText) findViewById(R.id.description);
		categoryEditText = (EditText) findViewById(R.id.category);
		itemTypeSpinner = (Spinner) findViewById(R.id.ItemTypeSpinner);
		request= (Button) findViewById(R.id.request);
		
		Intent currentIntent = getIntent();
		currentEmail = Application.getCurrentUser().get_email();
		
		
		request.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String selectedType = String.valueOf(itemTypeSpinner.getSelectedItem());
		ItemController itemController = new ItemController();
		itemController.createItem(itemnameEditText.getText().toString(), descriptionEditText.getText().toString(), 
		currentEmail, Application.getCurrentUser().getDistrict(),"",categoryEditText.getText().toString(),selectedType);
		
		
	}

}
