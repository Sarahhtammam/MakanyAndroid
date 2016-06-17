package com.androidActivities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.controllers.Application;
import com.controllers.ItemController;
import com.simpleModels.SimpleItem;

public class EditItem extends MyDrawerMenu implements OnClickListener {
	final SimpleItem currentItem = Application.getCurrentItem();
	EditText itemnameEditText;
	EditText descriptionEditText;
	EditText usermailEditText;
	EditText categoryEditText;
	Spinner itemStateSpinner;
	Button editbutton;
	String currentEmail ="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		super.onCreateDrawer();
		
		currentEmail = Application.getCurrentUser().get_email();
		
		itemnameEditText = (EditText) findViewById(R.id.name);
		descriptionEditText=(EditText) findViewById(R.id.description);
		categoryEditText = (EditText) findViewById(R.id.category);
		itemStateSpinner = (Spinner) findViewById(R.id.ItemStateSpinner);
		editbutton= (Button) findViewById(R.id.editbutton);
		
		itemnameEditText.setText(currentItem.getName());
		descriptionEditText.setText(currentItem.getDescription());
		categoryEditText.setText(currentItem.getCategories());

		String compareValue = currentItem.getState();
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ItemState_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		itemStateSpinner.setAdapter(adapter);
		if (!compareValue.equals(null)) {
		    int spinnerPosition = adapter.getPosition(compareValue);
		    itemStateSpinner.setSelection(spinnerPosition);
		}
		
		editbutton.setOnClickListener(this);
		
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
			String state = String.valueOf(itemStateSpinner.getSelectedItem());
			ItemController itemController = new ItemController();
			itemController.editItem(currentItem.getId(),itemnameEditText.getText().toString(), descriptionEditText.getText().toString(), 
			currentEmail,currentItem.getDistrict(),"",state, categoryEditText.getText().toString());
			
		}
		
		
	}

	
}
