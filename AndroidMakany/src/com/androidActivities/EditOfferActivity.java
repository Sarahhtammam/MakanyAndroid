package com.androidActivities;

import com.controllers.Application;
import com.controllers.OfferController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditOfferActivity extends Activity implements OnClickListener {
	
	EditText offerDescription;
	EditText offerPhoto;
	Button editebtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_offer);
		
		offerDescription = (EditText)  findViewById(R.id.offerDescription2);
		offerDescription.setText(Application.getCurrentOffer().getDescription());
		
		offerPhoto = (EditText)  findViewById(R.id.offerPhoto2);
		offerPhoto.setText(Application.getCurrentOffer().getPhoto());
		
		editebtn = (Button) findViewById(R.id.editOfferButton);
		editebtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (offerDescription.getText().toString().trim().equals("")) {
			offerDescription.setError("Offer Description is required!");

		} else {
			
			
			OfferController offer = new OfferController();
			offer.editOffer(Application.getCurrentOffer().getID(), offerDescription.getText().toString(),
					offerPhoto.getText().toString());
			
			
		}
	}

	
}
