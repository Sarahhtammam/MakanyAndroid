package com.androidActivities;

import java.util.ArrayList;

import SimpleModels.Element;
import SimpleModels.SimpleEvent;
import SimpleModels.SimpleItem;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.EventController;

public class WhatsNew extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whats_new);
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.whatsNewLayout);
		
		ArrayList<Element> elements = new ArrayList<Element>(); 
		elements = Application.getElements();
		
		for (int i = 0; i < elements.size(); i++) 
		{
			switch(elements.get(i).type)
			{
				case ITEM:
					final SimpleItem temp = (SimpleItem) elements.get(i);
					
					TextView name = new TextView(this);
			        name.setText("Item Name: " + temp.getName() );
			        my_layout.addView(name); 

			        
			        TextView description = new TextView(this);
			        description.setText("Item Description: " + temp.getDescription() );
			        my_layout.addView(description); 
			        
			        TextView category = new TextView(this);
			        category.setText("Item Category: " + temp.getCategories() );
			        my_layout.addView(category); 
			        Button b = new Button(this);
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
			        break;
					
					
				case EVENT:	
					final SimpleEvent temp2 = (SimpleEvent)elements.get(i);
					
					TextView name2 = new TextView(this);
			        name2.setText("Event Name: " + temp2.getName() );
			        my_layout.addView(name2); 

			        
			        TextView category2 = new TextView(this);
			        category2.setText("Event Category: " + temp2.getCategory() );
			        my_layout.addView(category2); 
			       

			        Button b2 = new Button(this);
			        b2.setText("View event details");
			        b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			        b2.setId(i+1);
			        b2.setTag(temp2.getID());
			        b2.setOnClickListener(new OnClickListener() {
			            public void onClick(View v) 
			            {
		    	         	Intent selectedEvent = new Intent(Application.getAppContext(),SingleEventActivity.class);
			  				selectedEvent.putExtra("eventID", temp2.getID());
			  				selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  				EventController eventController = new EventController();
			  				eventController.getEventByID(temp2.getID());
			  				Application.setCurrentEvent(temp2);
			  				Application.getAppContext().startActivity(selectedEvent);

			            }
			        });
			        my_layout.addView(b2);
			        break;
				
			}
			

	       
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whats_new, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
