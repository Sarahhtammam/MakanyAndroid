package com.androidActivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.controllers.Application;
import com.controllers.ItemController;


public class CreateItemActivity extends Fragment implements OnClickListener{
	
	View rootView;
	EditText itemnameEditText;
	EditText descriptionEditText;
	EditText usermailEditText;
	EditText categoryEditText;
	Spinner itemTypeSpinner;
	Button request;
	String currentEmail ="";

	public CreateItemActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_item,
				container, false);
		
		
		itemnameEditText = (EditText) rootView.findViewById(R.id.name);
		descriptionEditText=(EditText) rootView.findViewById(R.id.description);
		categoryEditText = (EditText) rootView.findViewById(R.id.category);
		itemTypeSpinner = (Spinner) rootView.findViewById(R.id.ItemTypeSpinner);
		request= (Button) rootView.findViewById(R.id.request);
		
		currentEmail = Application.getCurrentUser().get_email();
		
		
		request.setOnClickListener(this);
		
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (itemnameEditText.getText().toString().trim().equals(""))
		{
			itemnameEditText.setError( "Item name is required!" );
			
		}
		else
		{
			String selectedType = String.valueOf(itemTypeSpinner.getSelectedItem());
			ItemController itemController = new ItemController();
			itemController.createItem(itemnameEditText.getText().toString(), descriptionEditText.getText().toString(), 
			currentEmail, Application.getCurrentUser().getDistrict(),"",categoryEditText.getText().toString(),selectedType);
			
		}
		
		
		
	}
	
	

}
