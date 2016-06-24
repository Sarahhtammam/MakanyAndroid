package com.androidActivities;

import java.util.ArrayList;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.simpleModels.SimpleStore;

import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowOffersActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleStore> stores;
	LinearLayout my_layout;
	LinearLayout my_layout_big;
	final ShowOffersActivity me = this;
	
	
	public ShowOffersActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_offers,
				container, false);
		final ShowOffersActivity me = this;
		
		stores = new ArrayList<SimpleStore>(); 
		        
		////////my_layout_big = (LinearLayout)rootView.findViewById(R.id.userPostsLayout);
		
		stores = Application.getStores();
		Application.setComments(null);

		return rootView;
		//userStoreLayout
		
		
	}

	@Override
	public void processFinish(String str) 
	{
		/*stores = Application.getStores();
		if (stores!=null)
		{
			my_layout_big.removeAllViews();
			
	
	        //loop of generation of stores 
			for (int i = 0; i < stores.size(); i++) 
			{
				final SimpleStore temp = stores.get(i);
				
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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

				TextView storeName = new TextView(getActivity());
				storeName.setText("  " + temp.getStoreName());
				storeName.setTypeface(null, Typeface.BOLD);
				storeName.setTextColor(color.darkpurple);
				lin_hor.addView(storeName);
				TextView owner2 = new TextView(getActivity());
				owner2.setText(" added a post");
				lin_hor.addView(owner2);

				my_layout.addView(lin_hor);

				TextView description = new TextView(getActivity());
				description.setText("  " + temp.getDescription());
				description.setTypeface(null, Typeface.BOLD);
				description.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Medium);
				my_layout.addView(description);

				LinearLayout lin_hor2 = new LinearLayout(getActivity());
				lin_hor2.setOrientation(0);

				TextView district = new TextView(getActivity());
				district.setText("  " + temp.getDistrict());
				lin_hor2.addView(district);

				TextView category = new TextView(getActivity());
				category.setText(" - " + temp.getCategory());
				lin_hor2.addView(category);

				LinearLayout lin_hor3 = new LinearLayout(getActivity());
				lin_hor3.setOrientation(0);
				
				
				my_layout.addView(lin_hor3);

				my_layout_big.addView(my_layout);
		       
		        
			}
			
		}

		
*/	
		}



	
}
