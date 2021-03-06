package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
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

public class SignUpActivity extends Activity implements OnClickListener {

	EditText emailEditText;
	EditText usernameEditText;
	EditText passwordEditText;
	Spinner districtSpinner;
	EditText birthdateEditText;
	Spinner genderSpinner;
	EditText twitterAccountEditText;
	EditText foursquareAccountEditText;

	Button signupButton;

	String checkedInterests = "";
	String selectedDistrict = "";
	String selectedGender = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	ArrayList<String> myDistricts = new ArrayList<String>();

	// Set<String> interestsSet = new HashSet<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		emailEditText = (EditText) findViewById(R.id.email);
		usernameEditText = (EditText) findViewById(R.id.name);
		passwordEditText = (EditText) findViewById(R.id.password);
		districtSpinner = (Spinner) findViewById(R.id.districtSpinner);
		birthdateEditText = (EditText) findViewById(R.id.birthDate);
		genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
		twitterAccountEditText = (EditText) findViewById(R.id.twitterAccount);
		foursquareAccountEditText = (EditText) findViewById(R.id.foursquareAccount);

		signupButton = (Button) findViewById(R.id.RegistrationButton);

		myCategories = Application.getCategories();
		myDistricts = Application.getDistricts();
		
		LinearLayout my_layout = (LinearLayout) findViewById(R.id.interestLayout);
		
		if (myCategories != null)
		{
			// loop of generation of check boxes
			for (int i = 0; i < myCategories.size(); i++) {
				TableRow row = new TableRow(this);
				row.setId(i);
				row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				CheckBox checkBox = new CheckBox(this);
				checkBox.setTag(myCategories);
				checkBox.setId(i);
				checkBox.setText(myCategories.get(i));
				checks.add(checkBox);
				row.addView(checkBox);
				my_layout.addView(row);

			}
		}
		
		if (myDistricts != null)
		{
			// method of generation of districts drop down menu items
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, myDistricts);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			districtSpinner.setAdapter(dataAdapter);
		}
		

		signupButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		UserController userController = new UserController();

		for (int i = 0; i < checks.size(); i++) {
			if (checks.get(i).isChecked()) {
				checkedInterests += checks.get(i).getText() + ";";
				System.out.println(checkedInterests);
			}
		}

		selectedDistrict = String.valueOf(districtSpinner.getSelectedItem());
		selectedGender = String.valueOf(genderSpinner.getSelectedItem());

	/*	System.out.println("\nSelected interests: " + checkedInterests);
		System.out.println("\nSelected districts: " + selectedDistrict);
		System.out.println("\nSelected interests: " + selectedGender);
		
*/
		if (emailEditText.getText().toString().trim().equals("")) {
			emailEditText.setError("Email is required!");

		}
		else if (passwordEditText.getText().toString().trim().equals("")) {
			passwordEditText.setError("Password is required!");

		}
		else
		{
			userController.Signup(usernameEditText.getText().toString(),
					emailEditText.getText().toString(), passwordEditText.getText()
							.toString(), birthdateEditText.getText().toString(),
					selectedDistrict, "",
					selectedGender, twitterAccountEditText.getText().toString(),
					foursquareAccountEditText.getText().toString(),
					checkedInterests);
		}
		

	}

}