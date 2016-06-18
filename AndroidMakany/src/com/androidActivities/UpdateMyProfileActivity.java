package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.UserController;

public class UpdateMyProfileActivity extends MyDrawerMenu {

	String checkedInterests = "";
	String selectedDistrict = "";
	String selectedGender = "";
	
	EditText name_update;
	EditText email_update;
	EditText twitterAccount_update;
	EditText foursquareAccount_update;
	
	Spinner districtSpinner;
	Spinner genderSpinner;
	EditText passwordEditText_old;
	EditText passwordEditText_new;
	
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myInterests = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_my_profile);
		super.onCreateDrawer();
		
		name_update = (EditText) findViewById(R.id.name_update);
		name_update.setText(Application.getCurrentUser().getName());
		
		email_update = (EditText) findViewById(R.id.email_update);
		email_update.setText(Application.getCurrentUser().get_email());
		
		twitterAccount_update = (EditText) findViewById(R.id.twitterAccount_update);
		twitterAccount_update.setText(Application.getCurrentUser().getTwitter());
		
		foursquareAccount_update = (EditText) findViewById(R.id.foursquareAccount_update);
		foursquareAccount_update.setText(Application.getCurrentUser().getFoursquare());
		

		genderSpinner = (Spinner) findViewById(R.id.genderSpinner_update);
		districtSpinner = (Spinner) findViewById(R.id.districtSpinner_update);
		passwordEditText_old = (EditText) findViewById(R.id.password_update_old);
		passwordEditText_new = (EditText) findViewById(R.id.password_update_new);
		
		// method of generation of districts drop down menu items
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Application.getDistricts());
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSpinner.setAdapter(dataAdapter);
		
		String compareValue = Application.getCurrentUser().getDistrict();
		if (!compareValue.equals(null)) {
		    int spinnerPosition = dataAdapter.getPosition(compareValue);
		    districtSpinner.setSelection(spinnerPosition);
		}
		
		// Gender
		
		String compareValue2 =Application.getCurrentUser().getGender();
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
		genderSpinner.setAdapter(adapter);
		if (!compareValue2.equals(null)) {
		    int spinnerPosition = adapter.getPosition(compareValue2);
		    genderSpinner.setSelection(spinnerPosition);
		}
		
		myInterests = Application.getCategories();
		
		LinearLayout my_layout = (LinearLayout) findViewById(R.id.interestLayout_update);

		if(myInterests!=null)
		// loop of generation of check boxes
		for (int i = 0; i < myInterests.size(); i++) {
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setTag(myInterests);
			checkBox.setId(i);
			checkBox.setText(myInterests.get(i));
			
			if (isInMyInterest(myInterests.get(i)))
				checkBox.setChecked(true);
			
			checks.add(checkBox);
			row.addView(checkBox);
			my_layout.addView(row);

		}


		Button updateButton = (Button) findViewById(R.id.updateButton);


		updateButton.setOnClickListener(new OnClickListener() 
	     {
				public void onClick(View arg0) 
				{

					for (int i = 0; i < checks.size(); i++) 
					{
						if (checks.get(i).isChecked()) 
						{
							checkedInterests += checks.get(i).getText() + ";";
						}
					}

					selectedDistrict = String.valueOf(districtSpinner.getSelectedItem());
					selectedGender = String.valueOf(genderSpinner.getSelectedItem());

					System.out.println("\nSelected interests: " + checkedInterests);
					System.out.println("\nSelected districts: " + selectedDistrict);
					System.out.println("\nSelected Gender: " + selectedGender);
					
					if(passwordEditText_old.getText().toString().equals(Application.getCurrentUser().getPassword()))
					{
						System.out.println("OK same pass");
						UserController userController = new UserController();

						if(passwordEditText_new.getText().toString().trim().equals(""))
							//update with old password
							userController.EditProfile(email_update.getText().toString(),name_update.getText().toString(), 
							passwordEditText_old.getText().toString(), Application.getCurrentUser().getBirthDate(), 
							selectedDistrict, selectedGender, twitterAccount_update.getText().toString(), 
							foursquareAccount_update.getText().toString(), checkedInterests);
					
						else
							//update with new password
							userController.EditProfile(email_update.getText().toString(),name_update.getText().toString(), 
									passwordEditText_new.getText().toString(), Application.getCurrentUser().getBirthDate(), 
									selectedDistrict, selectedGender, twitterAccount_update.getText().toString(), 
									foursquareAccount_update.getText().toString(), checkedInterests);
							
					}
					
					else
					
					{
						Toast.makeText(getApplicationContext(),"you entered your old password wrong", Toast.LENGTH_SHORT).show();
						passwordEditText_old.setError( "Wrong password!" );
						
					}

//	checkk for password		
	
					
				}
		});
	}
	
	
	public boolean isInMyInterest(String myinterest)
	{
		for (String I : Application.getCurrentUser().getInterests())
		{
			if (I.equals(myinterest))
				return true;
		}
		return false;
	}
}
