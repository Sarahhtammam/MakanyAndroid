package com.androidActivities;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.UserController;

public class StoreSignUpActivity extends Activity implements OnClickListener{
	
	EditText storeNameEditText;
	EditText emailEditText;
	EditText passwordEditText;
	EditText twitterEditText;
	Spinner districtSpinner;
	EditText discriptionEditText;
	Button signupButton;
	
	LinearLayout my_layout;
    RadioGroup ll;
    
	String selectedCategory = "";
	String selectedDistrict = "";
	ArrayList<String> myInterests = new ArrayList<String>();
	ArrayList<String> myDistricts = new ArrayList<String>();
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_sign_up);
		
		storeNameEditText = (EditText) findViewById(R.id.storeName);
		emailEditText = (EditText) findViewById(R.id.storeEmail);
		passwordEditText = (EditText) findViewById(R.id.storePassword);
		twitterEditText = (EditText) findViewById(R.id.storeTwitterAccount);
		districtSpinner= (Spinner) findViewById(R.id.districtSpinner_Store);
		discriptionEditText = (EditText) findViewById(R.id.storeDescription);
		signupButton = (Button) findViewById(R.id.storeRegistrationButton);
		
		myInterests = Application.getCategories();
		myDistricts = Application.getDistricts();

		my_layout = (LinearLayout) findViewById(R.id.storeInterestLayout);
		ll = new RadioGroup(this);
		
	    if (myInterests != null)
	    {	
	    	 ll.setOrientation(LinearLayout.VERTICAL);

	        for (int i = 0; i < myInterests.size(); i++) 
	        {
	            RadioButton rdbtn = new RadioButton(this);
	            rdbtn.setId(i);
	            rdbtn.setText(myInterests.get(i));
	            ll.addView(rdbtn);
	        }
	        my_layout.addView(ll);
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
	public void onClick(View v) 
	{

		UserController userController = new UserController();

		if(ll.getCheckedRadioButtonId()!=-1)
		{
		    int id= ll.getCheckedRadioButtonId();
		    selectedCategory = myInterests.get(id);
		}

		//Toast.makeText(Application.getAppContext(),selection,Toast.LENGTH_SHORT).show();
		//selectedCategory=
		//System.out.println(selectedCategory);
        
		selectedDistrict = String.valueOf(districtSpinner.getSelectedItem());
		
		
		if (emailEditText.getText().toString().trim().equals("")) {
			emailEditText.setError("Email is required!");

		}
		else if (passwordEditText.getText().toString().trim().equals("")) {
			passwordEditText.setError("Password is required!");

		}
		else if (storeNameEditText.getText().toString().trim().equals("")) {
			storeNameEditText.setError("Store Name is required!");
		}
		else
		{
			userController.store_Signup(storeNameEditText.getText().toString(),
					emailEditText.getText().toString(), passwordEditText.getText().toString(), "", 
					selectedDistrict, discriptionEditText.getText().toString(), "", 
					twitterEditText.getText().toString(), "", selectedCategory);
			//add lattitude and longitude after back end modification
		}
		


	}
	

}
