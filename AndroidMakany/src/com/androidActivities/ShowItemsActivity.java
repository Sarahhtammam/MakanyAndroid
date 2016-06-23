package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.ItemController;
import com.simpleModels.SimpleItem;


public class ShowItemsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleItem> items;
	LinearLayout my_layout_big;
	ViewGroup container;
	
	public ShowItemsActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_items,
				container, false);
		
		container = this.container;
		
		
		final ShowItemsActivity me = this;
		
		
		
		items = new ArrayList<SimpleItem>(); 
		        
		
		my_layout_big = (LinearLayout)rootView.findViewById(R.id.itemsLayout);
		
		items = Application.getItems();
		
		final Spinner spinner = (Spinner) rootView.findViewById(R.id.openClosed_spinner);
		
		Button request= (Button) rootView.findViewById(R.id.ReqButton);
		request.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	String selectedState = String.valueOf(spinner.getSelectedItem());
            	ItemController itemController = new ItemController();
				itemController.getFilteredRequestItems(Application.getCurrentDistrict(), 
						selectedState ,me);
            }
        });
		
		Button loanButton= (Button) rootView.findViewById(R.id.loanButton);
		loanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	String selectedState = String.valueOf(spinner.getSelectedItem());
            	ItemController itemController = new ItemController();
				itemController.getFilteredLoanItems(Application.getCurrentDistrict(), 
						selectedState,me);
            }
        });
		
	
		

		return rootView;
		
		
	}
	

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		items = Application.getItems();
		
		if (items != null)
		{
			my_layout_big.removeAllViews();
			TextView x = new TextView(getActivity());
	        x.setText(str);
	        x.setTypeface(null, Typeface.BOLD);
	        my_layout_big.addView(x); 
	        
	        boolean isLoan = false;
	        if (str.equals("Loan Items"))
	        	isLoan = true;
	        	
			//loop of generation of items 
			for (int i = 0; i < items.size(); i++) 
			{
				final SimpleItem temp = items.get(i);
				
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

				TextView owner = new TextView(getActivity());
				owner.setText("  " + temp.getUserName());
				owner.setTypeface(null, Typeface.BOLD);
				owner.setTextColor(color.darkpurple);
				lin_hor.addView(owner);
				TextView owner2 = new TextView(getActivity());

				if (isLoan)
					owner2.setText(" is loaning an item");
				else
					owner2.setText(" is requesting an item");
				lin_hor.addView(owner2);

				my_layout.addView(lin_hor);

				TextView name = new TextView(getActivity());
				name.setText("  " + temp.getName());
				name.setTypeface(null, Typeface.BOLD);
				name.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
				my_layout.addView(name);

				if (!temp.getPhoto().equals("")) {
					TextView photo = new TextView(getActivity());
					photo.setText("  " + temp.getPhoto());
					Linkify.addLinks(photo, Linkify.ALL);
					my_layout.addView(photo);
				}

				LinearLayout lin_hor2 = new LinearLayout(getActivity());
				lin_hor2.setOrientation(0);

				TextView district = new TextView(getActivity());
				district.setText("  " + temp.getDistrict());
				lin_hor2.addView(district);

				TextView category = new TextView(getActivity());
				category.setText(" - " + temp.getCategories());
				lin_hor2.addView(category);

				Button b2 = new Button(getActivity());
				b2.setText("Show More");
				b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				b2.setId(i + 1);
				b2.setTag(temp.getId());
				b2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent selectedItem = new Intent(Application.getAppContext(),
								SingleItemActivity.class);
						selectedItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						Application.setCurrentItem(temp);
						Application.getAppContext().startActivity(selectedItem);

					}
				});
				lin_hor2.addView(b2);
				my_layout.addView(lin_hor2);

				my_layout_big.addView(my_layout);
			}
		}
		
		
	}
	
	

	


}
