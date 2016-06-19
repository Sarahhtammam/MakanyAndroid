package com.simpleModels;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.CalendarContract.Colors;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.R.color;
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
        
        TextView state = new TextView(context);
        state.setText("Item State: " + temp.getState() );
        my_layout.addView(state); 
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
		TextView date = new TextView(context);
        date.setText(temp.getDate() );
        my_layout.addView(date); 
        
        LinearLayout lin_hor = new LinearLayout(context);
        lin_hor.setOrientation(0);
        TextView owner = new TextView(context);
        owner.setText( temp.getOwnerName());
        owner.setTypeface(null, Typeface.BOLD);
        owner.setTextColor(color.darkpurple);
        lin_hor.addView(owner);
        TextView owner2 = new TextView(context);
        owner2.setText( " organized an event" );
        lin_hor.addView(owner2);
        
        my_layout.addView(lin_hor); 
        
		TextView name = new TextView(context);
        name.setText(temp.getName());
        name.setTypeface(null, Typeface.BOLD);
        name.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        my_layout.addView(name); 

        LinearLayout lin_hor2 = new LinearLayout(context);
        lin_hor2.setOrientation(0);
        
        TextView category = new TextView(context);
        category.setText(temp.getCategory() );
        lin_hor2.addView(category); 
       

        Button b2 = new Button(context);
        b2.setText("Show More");
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
        lin_hor2.addView(b2);
        my_layout.addView(lin_hor2);
	}
}
