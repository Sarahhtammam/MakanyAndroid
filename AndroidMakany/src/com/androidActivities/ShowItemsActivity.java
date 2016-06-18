package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.ItemController;
import com.simpleModels.SimpleItem;


public class ShowItemsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleItem> items;
	LinearLayout my_layout;
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
		        
		
		my_layout = (LinearLayout)rootView.findViewById(R.id.itemsLayout);
		
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
			my_layout.removeAllViews();
			TextView x = new TextView(getActivity());
	        x.setText(str);
	        x.setTypeface(null, Typeface.BOLD);
	        my_layout.addView(x); 
			//loop of generation of items 
			for (int i = 0; i < items.size(); i++) 
			{
				final SimpleItem temp = items.get(i);
				
				TextView name = new TextView(getActivity());
		        name.setText("Item Name: " + temp.getName() );
		        my_layout.addView(name); 

		        
		        TextView description = new TextView(getActivity());
		        description.setText("Item Description: " + temp.getDescription() );
		        my_layout.addView(description); 
		        
		        TextView state = new TextView(getActivity());
		        state.setText("Item State: " + temp.getState() );
		        my_layout.addView(state); 
		       

		        Button b = new Button(getActivity());
		        b.setText("View item details");
		        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        b.setId(i+1);
		        b.setTag(temp.getId());
		        b.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
	    	         	Intent selectedItem = new Intent(Application.getAppContext(),SingleItemActivity.class);
	    	         	selectedItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  				Application.setCurrentItem(temp);
		  				Application.getAppContext().startActivity(selectedItem);

		            }
		        });
		        my_layout.addView(b);
			}
		}
		
		
	}
	
	

	


}
