package com.androidActivities;

import java.util.ArrayList;

import com.controllers.AsyncResponse;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

public class ShowStoresActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	//ArrayList<SimpleStore> stores;
	LinearLayout my_layout;
	final ShowStoresActivity me = this;
	
	public ShowStoresActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_stores,
				container, false);


		return rootView;
		
		
	}

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		
	}



	
}
