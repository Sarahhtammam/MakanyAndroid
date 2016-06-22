package com.androidActivities;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.controllers.AsyncResponse;
import com.simpleModels.SimpleOffer;

public class ViewMyOffers extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleOffer> myOffers;
	LinearLayout my_layout;
	final ViewMyOffers me = this;
	
	public ViewMyOffers() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_view_my_offers,
				container, false);
		
		
		myOffers = new ArrayList<SimpleOffer>(); 
		        
		my_layout = (LinearLayout)rootView.findViewById(R.id.storeOffersLayout);
		
		return rootView;
	}

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		
	}
	




}
