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
import com.controllers.EventController;
import com.simpleModels.SimpleEvent;


public class ShowEventsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleEvent> events;
	LinearLayout my_layout_big;
	ViewGroup container;
	final ShowEventsActivity me = this;
	
	public ShowEventsActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_events,
				container, false);
		
		container = this.container;
		
		
		final String currentEmail = Application.getUserEmail();
		events = new ArrayList<SimpleEvent>(); 
		        
		my_layout_big = (LinearLayout)rootView.findViewById(R.id.userEventsLayout);
		
		events = Application.getEvents();
		
		
		Button categoryButton= (Button) rootView.findViewById(R.id.filterEventsCButton);
		categoryButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	Filter();
            }
        });
		
		
		Button myEventsButton= (Button) rootView.findViewById(R.id.getMyEventsButton);
		myEventsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	EventController eventController = new EventController();
  				eventController.getGoingEvents(currentEmail, "",me);
            }
        });
		
	

		return rootView;
		
		
	}
	

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		events = Application.getEvents();
		
		if (events != null)
		{
			my_layout_big.removeAllViews();

			
	      //loop of generation of events 
			for (int i = 0; i < events.size(); i++) 
			{
				final SimpleEvent temp = events.get(i);
				
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
				owner.setText("  " + temp.getOwnerName());
				owner.setTypeface(null, Typeface.BOLD);
				owner.setTextColor(color.darkpurple);
				lin_hor.addView(owner);
				TextView owner2 = new TextView(getActivity());
				owner2.setText(" organized an event");
				lin_hor.addView(owner2);

				my_layout.addView(lin_hor);
				
				TextView onDate = new TextView(getActivity());
				onDate.setText("  on " + temp.getFrom());
				my_layout.addView(onDate);

				TextView name = new TextView(getActivity());
				name.setText("  " + temp.getName());
				name.setTypeface(null, Typeface.BOLD);
				name.setTextAppearance(getActivity(), android.R.style.TextAppearance_Medium);
				my_layout.addView(name);

				LinearLayout lin_hor2 = new LinearLayout(getActivity());
				lin_hor2.setOrientation(0);

				TextView district = new TextView(getActivity());
				district.setText("  " + temp.getDistrict());
				lin_hor2.addView(district);

				TextView category = new TextView(getActivity());
				category.setText(" - " + temp.getCategory());
				lin_hor2.addView(category);

				Button b2 = new Button(getActivity());
				b2.setText("Show More");
				b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				b2.setId(i + 1);
				b2.setTag(temp.getID());
				b2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent selectedEvent = new Intent(Application.getAppContext(),
								SingleEventActivity.class);
						selectedEvent.putExtra("eventID", temp.getID());
						selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						EventController eventController = new EventController();
						eventController.getEventByID(temp.getID());
						Application.setCurrentEvent(temp);
						Application.getAppContext().startActivity(selectedEvent);

					}
				});
				lin_hor2.addView(b2);
				my_layout.addView(lin_hor2);

				my_layout_big.addView(my_layout);
			}
		}
		else
			Toast.makeText(Application.getAppContext(), "Error, No events",Toast.LENGTH_LONG).show();
		
		
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
	            	   
	            	   EventController eventController = new EventController();
	     				eventController.getFilteredEvents(categories, 
	     						Application.getCurrentDistrict(),me);

	                   
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
