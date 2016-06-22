package com.androidActivities;

import com.controllers.Application;
import com.controllers.OfferController;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateOffer extends Fragment implements OnClickListener {
	View rootView;
	EditText offerDescription;
	EditText offerPhoto;
	Button createbtn;
	
	public CreateOffer() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_offer, container,
				false);
		
		offerDescription = (EditText)  rootView.findViewById(R.id.offerDescription);
		offerPhoto = (EditText)  rootView.findViewById(R.id.offerPhoto);
		
		createbtn = (Button) rootView.findViewById(R.id.createOfferButton);
		createbtn.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (offerDescription.getText().toString().trim().equals("")) {
			offerDescription.setError("Offer Description is required!");

		} else {
			
			
			OfferController offer = new OfferController();
			offer.addOffer(Application.getUserEmail(), offerDescription.getText().toString(),
					offerPhoto.getText().toString());
			
		}
		
	}
	
	
	
}
