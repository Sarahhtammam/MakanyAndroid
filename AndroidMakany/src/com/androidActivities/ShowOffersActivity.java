package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.OfferController;
import com.controllers.StoreController;
import com.simpleModels.SimpleOffer;
import com.simpleModels.SimpleStore;

public class ShowOffersActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleOffer> offers;
	ViewGroup container;
	LinearLayout my_layout_big;
	final ShowOffersActivity me = this;
	
	public ShowOffersActivity() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_offers,
				container, false);
		
		container = this.container;
		offers = new ArrayList<SimpleOffer>(); 
        my_layout_big = (LinearLayout)rootView.findViewById(R.id.userOffersLayout);
		
		Button categoryButton= (Button) rootView.findViewById(R.id.filterOffersButton);
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
		offers = Application.getOffers();
		if (offers!=null)
		{
			my_layout_big.removeAllViews();
			
	
	        //loop of generation of offers 
			for (int i = 0; i < offers.size(); i++) 
			{
				final SimpleOffer temp = offers.get(i);
				
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
				storeName.setText("  " + temp.getStoreMail());
				storeName.setTypeface(null, Typeface.BOLD);
				storeName.setTextColor(color.darkpurple);
				lin_hor.addView(storeName);
				
				TextView owner2 = new TextView(getActivity());
				owner2.setText(" added an offer:");
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

				LinearLayout lin_hor3 = new LinearLayout(getActivity());
				lin_hor3.setOrientation(0);
				
								
				ImageView image2 = new ImageView(getActivity()); 
				image2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.thumbs_up));
				lin_hor3.addView(image2);
				
				TextView approval = new TextView(getActivity());
				approval.setText("  " + temp.getNumThumbsUp() + "  ");
				lin_hor3.addView(approval);
				
				ImageView image3 = new ImageView(getActivity()); 
				image3.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.thumbs_down));
				lin_hor3.addView(image3);
				
				TextView disapproval = new TextView(getActivity());
				disapproval.setText("  " + temp.getNumThumbsDown() + "  ");
				lin_hor3.addView(disapproval);

		        Button thunbsUp = new Button(getActivity());
		        thunbsUp.setText("Thumbs Up");
		        thunbsUp.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        thunbsUp.setId(i+1);
		        thunbsUp.setTag(temp.getID());
		        thunbsUp.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
		            	OfferController offerController = new OfferController();
		            	offerController.thumbsUp(temp.getID(), Application.getCurrentUser().get_email());
		            	
		            }
		        });
		        lin_hor3.addView(thunbsUp);
		        
		        Button thunbsDown = new Button(getActivity());
		        thunbsDown.setText("Thumbs Down");
		        thunbsDown.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        thunbsDown.setId(i+1);
		        thunbsDown.setTag(temp.getID());
		        thunbsDown.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
		            	OfferController offerController = new OfferController();
		            	offerController.thumbsDown(temp.getID(), Application.getCurrentUser().get_email());
		         
		            }
		        });
		        lin_hor3.addView(thunbsDown);

				my_layout.addView(lin_hor3);

				
				
				my_layout_big.addView(my_layout);
		       
		    }
			
		}
		else
			Toast.makeText(getActivity().getApplicationContext(), "Error, No offers",Toast.LENGTH_LONG).show();
		
		
	}



	public void Filter()
	{
		final ArrayList<Integer> mSelectedItems = new ArrayList();  // Where we track the selected items
		final ArrayList<String> storeNames = SimpleStore.getStoreNames(Application.getStores());
		final CharSequence[] items = storeNames.toArray(new CharSequence[Application.getCategories().size()]);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle("Choose Stores");
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
	            	   String storeMails = "";
	            	   for (int i = 0 ; i < mSelectedItems.size(); i++ )
	            	   {
	            		   int index = mSelectedItems.get(i);
	            		   storeMails += SimpleStore.getStoreMailByName(Application.getStores(), storeNames.get(index));
	            		   if (i != mSelectedItems.size() - 1)
	            			   storeMails += ";";	   
	            	   }
	            	   
	            	   StoreController storeController2 = new StoreController();
	   					storeController2.getFilteredOffersService(storeMails, "",Application.getCurrentDistrict(), "", me);
	   				
	                   
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
