package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.EventController;

public class CreateEventActivity extends Fragment implements OnClickListener {
	
	View rootView;
	EditText eventName;
	EditText eventCategory;
	EditText eventDescription;
	Button createEventButton;
	
	private TextView DisplayStartDate;
	private DatePicker dpResult;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;

	
	
	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	
	Button btnSelectStartDate,btnSelectStartTime, btnSelectEndDate,btnSelectEndTime;
    
	
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
		
		
		//////////////////////////////////////////////////////////////////////
		addListenerOnButton();
		/////////////////////////////////////////////////////////////////////
		
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
	
	
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	

	
	public void addListenerOnButton() {

		btnSelectStartDate = (Button) rootView.findViewById(R.id.buttonSelectStartDate);

		btnSelectStartDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				getActivity().showDialog(DATE_DIALOG_ID);

			}

		});

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(getActivity(), datePickerListener, year, month,day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener 
                = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			DisplayStartDate.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year)
			   .append(" "));

			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}
	};

	
	
	
	
}
