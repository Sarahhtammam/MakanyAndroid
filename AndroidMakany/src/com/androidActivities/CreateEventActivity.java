package com.androidActivities;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ActionBar.LayoutParams;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import android.widget.TimePicker;
import android.widget.Toast;

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
	
	static String startDate="", endDate="", startTime="", endTime="";

	static boolean startDateSelected = true, startTimeSelected = true;
	/*
	 * Button btnSelectStartDate,btnSelectStartTime,
	 * btnSelectEndDate,btnSelectEndTime;
	 * 
	 * static final int DATE_DIALOG_ID_Start = 0; static final int
	 * TIME_DIALOG_ID_Start = 1; static final int DATE_DIALOG_ID_End = 2; static
	 * final int TIME_DIALOG_ID_End = 3;
	 * 
	 * // variables to save user selected date and time public int
	 * yearStart,monthStart,dayStart,hourStart,minuteStart; public int
	 * yearEnd,monthEnd,dayEnd,hourEnd,minuteEnd;
	 * 
	 * // declare the variables to Show/Set the date and time when Time and Date
	 * Picker Dialog first appears private int mYearStart, mMonthStart,
	 * mDayStart,mHourStart,mMinuteStart; private int mYearEnd, mMonthEnd,
	 * mDayEnd,mHourEnd,mMinuteEnd;
	 */

	public CreateEventActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_event, container,
				false);

		eventName = (EditText) rootView.findViewById(R.id.eventName);
		eventDescription = (EditText) rootView
				.findViewById(R.id.eventDescription);

		myCategories = Application.getCategories();

		LinearLayout my_layout = (LinearLayout) rootView
				.findViewById(R.id.selectCategoryLayout_event);

		if (myCategories != null) {
			// loop of generation of check boxes
			for (int i = 0; i < myCategories.size(); i++) {
				TableRow row = new TableRow(getActivity());
				row.setId(i);
				row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				CheckBox checkBox = new CheckBox(getActivity());
				checkBox.setTag(myCategories);
				checkBox.setId(i);
				checkBox.setText(myCategories.get(i));
				checks.add(checkBox);
				row.addView(checkBox);
				my_layout.addView(row);

			}
		}

		Button btnSelectStartDate = (Button) rootView
				.findViewById(R.id.buttonSelectStartDate);
		btnSelectStartDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				startDateSelected= true;
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});
		
		Button btnSelectEndDate = (Button) rootView
				.findViewById(R.id.buttonSelectEndDate);
		btnSelectEndDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				startDateSelected= false;
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});
		
		Button btnSelectStarttime = (Button) rootView
				.findViewById(R.id.buttonSelectStartTime);
		btnSelectStarttime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				startTimeSelected= true;
				DialogFragment newFragment = new TimePickerFragment();
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		Button btnSelectEndtime = (Button) rootView
				.findViewById(R.id.buttonSelectEndTime);
		btnSelectEndtime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				startTimeSelected= false;
				 DialogFragment newFragment = new TimePickerFragment();
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		

        createEventButton= (Button) rootView.findViewById(R.id.createEventButton);
		createEventButton.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onClick(View arg0) {
		
		if (eventName.getText().toString().trim().equals("")) {
			eventName.setError("Event name is required!");

		}

		else {
			/*
			 * for (int i = 0; i < checks.size(); i++) { if
			 * (checks.get(i).isChecked()) { checkedCategories +=
			 * checks.get(i).getText() + ";";
			 * System.out.println(checkedCategories); } }
			 */

			for (int i = 0; i < checks.size(); i++) {
				if (checks.get(i).isChecked()) {
					checkedCategories += checks.get(i).getText();
					break;
				}
			}

			String startDateTime = startDate + " " + startTime + " SAST";
			String endDateTime = endDate + " " +endTime + " SAST";
			
			EventController eventController = new EventController();
			eventController.createEvent(eventName.getText().toString(),
					checkedCategories, eventDescription.getText().toString(),
					Integer.toString(0), Integer.toString(0), Application.getCurrentUser().get_email(), 
					Application.getCurrentDistrict(), startDateTime, endDateTime);

		}

	}

	
	
	
	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user

			
			String stringYear = Integer.toString(year);
			String stringMonth = Integer.toString(month);
			String stringDay = Integer.toString(day);
			
			if(stringMonth.length()==1)
				stringMonth= "0" + stringMonth;

			if(stringDay.length()==1)
				stringDay= "0" + stringDay;
			
			if(startDateSelected)
			{
				startDate = stringYear +"-" + stringMonth +"-" + stringDay;  
				Toast.makeText(Application.getAppContext(), "start Date: " + startDate,
					Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				endDate = stringYear +"-" + stringMonth +"-" + stringDay;
				Toast.makeText(Application.getAppContext(), "end Date: " + endDate,
					Toast.LENGTH_SHORT).show();
			}
				
		}
	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user
			
			String stringHour = Integer.toString(hourOfDay);
			String stringMinute = Integer.toString(minute);
			
			if(stringHour.length()==1)
				stringHour = "0" + stringHour;
			
			if(stringMinute.length()==1)
				stringMinute = "0" + stringMinute;
			
			
			if(startTimeSelected)
			{
				startTime = "("+ stringHour +":" + stringMinute +":00.000)";  
				Toast.makeText(Application.getAppContext(), "start time: " + startTime,
					Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				endTime = "("+ stringHour +":" + stringMinute +":00.000)";   
				Toast.makeText(Application.getAppContext(), "end time: " + endTime,
					Toast.LENGTH_SHORT).show();
			}
				
		}
	}

}
