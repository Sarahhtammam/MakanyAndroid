package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.controllers.Application;
import com.controllers.EventController;

public class CreateEventActivity extends Fragment implements OnClickListener {
	
	View rootView;
	EditText eventName;
	EditText eventCategory;
	EditText eventDescription;
	Button createEventButton;
	
	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	
	public CreateEventActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_event, container,
				false);
		
		eventName = (EditText)  rootView.findViewById(R.id.eventName);
		eventDescription = (EditText)  rootView.findViewById(R.id.eventDescription);
		
		myCategories = Application.getCategories();
		
		LinearLayout my_layout = (LinearLayout)  rootView.findViewById(R.id.selectCategoryLayout_event);
		
		if (myCategories != null)
		{
			// loop of generation of check boxes
			for (int i = 0; i < myCategories.size(); i++) {
				TableRow row = new TableRow(getActivity());
				row.setId(i);
				row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				CheckBox checkBox = new CheckBox(getActivity());
				checkBox.setTag(myCategories);
				checkBox.setId(i);
				checkBox.setText(myCategories .get(i));
				checks.add(checkBox);
				row.addView(checkBox);
				my_layout.addView(row);

			}
		}
		
		createEventButton= (Button) rootView.findViewById(R.id.createEventButton);
		
		createEventButton.setOnClickListener(this);
		
		
		return rootView;
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
       
