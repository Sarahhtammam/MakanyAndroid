package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.OfferController;
import com.simpleModels.SimpleOffer;

public class ViewMyOffers extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleOffer> offers;
	LinearLayout my_layout_big;
	final ViewMyOffers me = this;
	
	public ViewMyOffers() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_view_my_offers,
				container, false);
		
		
		offers = Application.getOffers();
		        
		my_layout_big = (LinearLayout)rootView.findViewById(R.id.storeOffersLayout);
		
		return rootView;
	}

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		
		offers = Application.getOffers();
		
		if (offers != null)
		{
			my_layout_big.removeAllViews();

			//loop of generation of items 

			for (int i = 0; i < offers.size(); i++) 
			{
				final SimpleOffer temp = offers.get(i);
				
				LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				params.setMargins(0, 5, 0, 5);

				LinearLayout my_layout = new LinearLayout(getActivity());
				my_layout.setLayoutParams(params);

				my_layout.setOrientation(LinearLayout.VERTICAL);
				

				GradientDrawable border = new GradientDrawable();

				border.setColor(0xFFFFFFFF); // white background
				border.setStroke(1, 0xFF000000); // black border with full opacity
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
					my_layout.setBackgroundDrawable(border);
				} else {
					my_layout.setBackground(border);
				}

				TextView date = new TextView(getActivity());
				date.setText("  " + temp.getDate());
				my_layout.addView(date);

				LinearLayout lin_hor = new LinearLayout(getActivity());
				lin_hor.setOrientation(0);
				
	
		        TextView description = new TextView(getActivity());
		        description.setText("  " + temp.getDescription() );
		        description.setTypeface(null, Typeface.BOLD);
		        description.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
		        my_layout.addView(description); 
		        
		        if (!temp.getPhoto().equals(""))
				{
					TextView photo = new TextView(getActivity());
					photo.setText(temp.getPhoto());
					//photo.setMovementMethod(LinkMovementMethod.getInstance());
					Linkify.addLinks(photo, Linkify.ALL);
					
					my_layout.addView(photo);
				}
		        
		        LinearLayout lin_hor2 = new LinearLayout(getActivity());
				lin_hor2.setOrientation(0);
		       

		        Button EditButton = new Button(getActivity());
		        EditButton.setText("Edit offer");
		        EditButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        EditButton.setId(i+1);
		        EditButton.setTag(temp.getID());
		        EditButton.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
		            	Application.setCurrentOffer(temp);
		            	Intent editIntent = new Intent(Application.getAppContext(),EditOfferActivity.class);
		            	editIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						Application.getAppContext().startActivity(editIntent);

		            }
		        });
		        lin_hor2.addView(EditButton);
		        
		        Button deleteButton = new Button(getActivity());
		        deleteButton.setText("Delete offer");
		        deleteButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        deleteButton.setId(i+1);
		        deleteButton.setTag(temp.getID());
		        deleteButton.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
	    	         	OfferController offer = new OfferController ();
	    	         	offer.removeOffer(temp.getID());

		            }
		        });
		        lin_hor2.addView(deleteButton);
		        
		        my_layout.addView(lin_hor2);

				my_layout_big.addView(my_layout);
			}
		}
		
	}
	




}
