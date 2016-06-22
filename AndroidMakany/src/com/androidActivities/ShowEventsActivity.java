package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.EventController;
import com.simpleModels.SimpleEvent;


public class ShowEventsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimpleEvent> events;
	LinearLayout my_layout;
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
		        
		my_layout = (LinearLayout)rootView.findViewById(R.id.userEventsLayout);
		
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
			my_layout.removeAllViews();
			TextView x = new TextView(getActivity());
	        x.setText(str);
	        x.setTypeface(null, Typeface.BOLD);
	        my_layout.addView(x); 
			
	      //loop of generation of events 
			for (int i = 0; i < events.size(); i++) 
			{
				final SimpleEvent temp = events.get(i);
				
				TextView name = new TextView(getActivity());
		        name.setText("Event Name: " + temp.getName() );
		        my_layout.addView(name); 

		        
		        TextView category = new TextView(getActivity());
		        category.setText("Event Category: " + temp.getCategory() );
		        my_layout.addView(category); 
		       

		        Button b = new Button(getActivity());
		        b.setText("View event details");
		        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        b.setId(i+1);
		        b.setTag(temp.getID());
		        b.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
	    	         	Intent selectedEvent = new Intent(Application.getAppContext(),SingleEventActivity.class);
		  				selectedEvent.putExtra("eventID", temp.getID());
		  				selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  				EventController eventController = new EventController();
		  				eventController.getEventByID(temp.getID());
		  				Application.setCurrentEvent(temp);
		  				Application.getAppContext().startActivity(selectedEvent);

		            }
		        });
		        my_layout.addView(b);
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
