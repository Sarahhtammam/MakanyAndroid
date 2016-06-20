package com.androidActivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CreateOffer extends Fragment {
	View rootView;
	EditText offerName;
	
	public CreateOffer() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_offer, container,
				false);
		
		offerName = (EditText)  rootView.findViewById(R.id.offerName);
		
		return rootView;
	}
	
	
	
}
