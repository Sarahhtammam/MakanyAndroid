package com.androidActivities;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.SessionController;
import com.controllers.UserController;

public class UpdateStoreProfileActivity extends Fragment implements OnClickListener {
	
	View rootView;
	EditText storeNameEditText;
	EditText emailEditText;
	EditText passwordEditText;
	EditText twitterEditText;
	Spinner districtSpinner;
	EditText descriptionEditText;
	Button editButton;
	
	LinearLayout my_layout;
    RadioGroup ll;
    
	String selectedCategory = "";
	String selectedDistrict = "";
	ArrayList<String> myInterests = new ArrayList<String>();
	ArrayList<String> myDistricts = new ArrayList<String>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_update_store_profile, container,
				false);
		
		Button signoutButton= (Button) rootView.findViewById(R.id.signoutStoreButton);
		signoutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	SessionController.showSignoutDialog(getActivity());
            }
        });
		
		if (Application.getCurrentStore() != null)
		{
			storeNameEditText = (EditText) rootView.findViewById(R.id.storeName2);
			storeNameEditText.setText(Application.getCurrentStore().getStoreName());
			
			emailEditText = (EditText) rootView.findViewById(R.id.storeEmail2);
			emailEditText.setText(Application.getCurrentStore().getEmail());
			
			passwordEditText = (EditText) rootView.findViewById(R.id.storePassword2);
			passwordEditText.setText(Application.getCurrentStore().getPassword());
			
			districtSpinner= (Spinner) rootView.findViewById(R.id.districtSpinner_Store2);

			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item, Application.getDistricts());
			dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			districtSpinner.setAdapter(dataAdapter2);
			
			
			String compareValue = Application.getCurrentStore().getDistrict();
			if (!compareValue.equals(null)) {
			    int spinnerPosition = dataAdapter2.getPosition(compareValue);
			    districtSpinner.setSelection(spinnerPosition);
			}
			
			
			descriptionEditText = (EditText) rootView.findViewById(R.id.storeDescription2);
			descriptionEditText.setText(Application.getCurrentStore().getDescription());
			
			editButton = (Button) rootView.findViewById(R.id.storeUpdateButton);
			
			myInterests = Application.getCategories();
			myDistricts = Application.getDistricts();

			my_layout = (LinearLayout) rootView.findViewById(R.id.storeInterestLayout2);
			ll = new RadioGroup(getActivity());
			
		    if (myInterests != null)
		    {	
		    	 ll.setOrientation(LinearLayout.VERTICAL);

		        for (int i = 0; i < myInterests.size(); i++) 
		        {
		            RadioButton rdbtn = new RadioButton(getActivity());
		            rdbtn.setId(i);
		            rdbtn.setText(myInterests.get(i));
		            if (Application.getCurrentStore().getCategory().equals(myInterests.get(i)))
		            	rdbtn.setChecked(true);
		            ll.addView(rdbtn);
		        }
		        my_layout.addView(ll);
		    }    
	        

			editButton.setOnClickListener(this);
		}
		
		
		return rootView;
	}
	
	

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		UserController userController = new UserController();

		if(ll.getCheckedRadioButtonId()!=-1)
		{
		    int id= ll.getCheckedRadioButtonId();
		    selectedCategory = myInterests.get(id);
		}

        
		selectedDistrict = String.valueOf(districtSpinner.getSelectedItem());
		
		userController.EditProfileStore(emailEditText.getText().toString(),
				storeNameEditText.getText().toString(),passwordEditText.getText().toString()
				,selectedCategory, descriptionEditText.getText().toString(), selectedDistrict, "", "");
	
		
		
		
	}


}
