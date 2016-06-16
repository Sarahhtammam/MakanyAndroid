package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.controllers.EventController;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class CreateEventActivity extends Activity implements OnClickListener {

	EditText eventName;
	EditText eventCategory;
	EditText eventDescription;
	Button createEventButton;
	
	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		
		eventName = (EditText) findViewById(R.id.eventName);
		eventDescription = (EditText) findViewById(R.id.eventDescription);
		
		myCategories = Application.getCategories();
		
		LinearLayout my_layout = (LinearLayout) findViewById(R.id.selectCategoryLayout_event);
		
		// loop of generation of check boxes
		for (int i = 0; i < myCategories.size(); i++) {
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setTag(myCategories);
			checkBox.setId(i);
			checkBox.setText(myCategories .get(i));
			checks.add(checkBox);
			row.addView(checkBox);
			my_layout.addView(row);

		}
		createEventButton= (Button) findViewById(R.id.createEventButton);
		
		createEventButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) 
	{
		if (eventName.getText().toString().trim().equals(""))
		{
			eventName.setError( "Event name is required!" );
			
		}
		
		else
		{
			/*for (int i = 0; i < checks.size(); i++) {
				if (checks.get(i).isChecked()) {
					checkedCategories += checks.get(i).getText() + ";";
					System.out.println(checkedCategories);
				}
			}*/


			for (int i = 0; i < checks.size(); i++) 
			{
				if (checks.get(i).isChecked()) {
					checkedCategories += checks.get(i).getText();
					break;
				}
			}
			
			EventController eventController = new EventController();
			eventController.createEvent(eventName.getText().toString(), checkedCategories, 
					eventDescription.getText().toString(), Integer.toString(0), Integer.toString(0), 
					Application.getCurrentUser().get_email(), Application.getCurrentDistrict());

		}
		
	}
}
       
