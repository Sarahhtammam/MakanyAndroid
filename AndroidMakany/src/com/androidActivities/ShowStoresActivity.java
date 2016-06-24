package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.StoreController;
import com.simpleModels.SimpleStore;

public class ShowStoresActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleStore> stores;
	ViewGroup container;
	LinearLayout my_layout_big;
	final ShowStoresActivity me = this;
	
	public ShowStoresActivity() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_stores,
				container, false);
		
		container = this.container;
		stores = new ArrayList<SimpleStore>(); 
        my_layout_big = (LinearLayout)rootView.findViewById(R.id.userStoreLayout);
		
		Button categoryButton= (Button) rootView.findViewById(R.id.filterStoresCButton);
		categoryButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	Filter();
            }
        });
		
		return rootView;
		
		
	}
	

	@Override
	public void processFinish(String str) 
	{
		stores = Application.getStores();
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
				owner2.setText(" has a store:");
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

				my_layout.addView(lin_hor2);

				my_layout_big.addView(my_layout);
		       
		    }
			
		}
		else
			Toast.makeText(getActivity().getApplicationContext(), "Error, No stores",Toast.LENGTH_LONG).show();
		
		
	}



	public void Filter()
	{
		final ArrayList<Integer> mSelectedItems = new ArrayList();  // Where we track the selected items
		
		final CharSequence[] items = Application.getCategories().toArray(new CharSequence[Application.getCategories().size()]);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle("Choose Categories");
	    // Specify the list array, the items to be selected by default (null for none),
	    // and the listener through which to receive callbacks when items are selected
	          builder.setMultiChoiceItems(items, null,
	                      new DialogInterface.OnMultiChoiceClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int which,
	                       boolean isChecked) {
	                   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
	                       mSelectedItems.add(which);
	                   } else if (mSelectedItems.contains(which)) {
	                       // Else, if the item is already in the array, remove it 
	                       mSelectedItems.remove(Integer.valueOf(which));
	                   }
	               }
	           })
	           // Set the action buttons
	           .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // User clicked OK, so save the mSelectedItems results somewhere
	                   // or return them to the component that opened the dialog
	            	   String categories = "";
	            	   for (int i = 0 ; i < mSelectedItems.size(); i++ )
	            	   {
	            		   int index = mSelectedItems.get(i);
	            		   categories +=  Application.getCategories().get(index);
	            		   
	            		   if (i != mSelectedItems.size() - 1)
	            			   categories += ";";	   
	            	   }
	            	   
	            	   StoreController storeController = new StoreController();
	            	   storeController.getFilteredStoresService(categories, Application.getCurrentDistrict(), "", me);
	         		
	                   
	               }
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                 
	               }
	           });
	          
	          builder.show();
	}

	
	
}
