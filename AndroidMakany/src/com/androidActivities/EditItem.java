package com.androidActivities;

import SimpleModels.SimpleItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.controllers.Application;
import com.controllers.ItemController;

public class EditItem extends Activity implements OnClickListener {
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
		String state = String.valueOf(itemStateSpinner.getSelectedItem());
		ItemController itemController = new ItemController();
		itemController.editItem(currentItem.getId(),itemnameEditText.getText().toString(), descriptionEditText.getText().toString(), 
		currentEmail,currentItem.getDistrict(),"",state, categoryEditText.getText().toString());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
