package com.simpleModels;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.SingleEventActivity;
import com.androidActivities.SingleItemActivity;
import com.controllers.Application;
import com.controllers.EventController;

public class ViewElements {
	
	
	public void viewItem(final SimpleItem temp ,int i, LinearLayout my_layout, Context context )
	{
		TextView name = new TextView(context);
        name.setText("Item Namee: " + temp.getName() );
        my_layout.addView(name); 

        
        TextView description = new TextView(context);
        description.setText("Item Description: " + temp.getDescription() );
        my_layout.addView(description); 
        
        TextView category = new TextView(context);
        category.setText("Item Category: " + temp.getCategories() );
        my_layout.addView(category); 
        Button b = new Button(context);
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
	
	public void viewEvent(final SimpleEvent temp ,int i, LinearLayout my_layout, Context context )
	{
		TextView name2 = new TextView(context);
        name2.setText("Event Name: " + temp.getName() );
        my_layout.addView(name2); 

        
        TextView category2 = new TextView(context);
        category2.setText("Event Category: " + temp.getCategory() );
        my_layout.addView(category2); 
       

        Button b2 = new Button(context);
        b2.setText("View event details");
        b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        b2.setId(i+1);
        b2.setTag(temp.getID());
        b2.setOnClickListener(new OnClickListener() {
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
        my_layout.addView(b2);
	}
}
