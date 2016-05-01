package com.androidActivities;

import com.controllers.Application;
import com.controllers.EventController;

import SimpleModels.SimpleEvent;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SingleEventActivity extends Activity implements OnClickListener{

	final SimpleEvent currentEvent = Application.getCurrentEvent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_event);
		
		//singleEventLayout
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.singleEventLayout);
		
		
		Toast.makeText(Application.getAppContext(), "Event name issssss: " + currentEvent.getName(),
				Toast.LENGTH_LONG).show();
	
		
		TextView name = new TextView(this);
        name.setText("Event Name: " + currentEvent.getName() );
        my_layout.addView(name); 

        
        TextView category = new TextView(this);
        category.setText("Event Category: " + currentEvent.getCategory() );
        my_layout.addView(category); 
       
        TextView description = new TextView(this);
        description.setText("Event description: " + currentEvent.getDescription() );
        my_layout.addView(description); 

        TextView owner = new TextView(this);
        owner.setText("Event owner: " + currentEvent.getOwner() );
        my_layout.addView(owner); 

        
/*        Button b = new Button(this);
        b.setText("Attend this event");
        b.setId(101);
        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        my_layout.addView(b);
*/

        Button b1= (Button) findViewById(R.id.reviewEvent);
		Button b22 = (Button) findViewById(R.id.goBack);
		
		b1.setOnClickListener(this);
		b22.setOnClickListener(this);
        
		
        /*        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	Toast.makeText(Application.getAppContext(), currentEvent.getID() +  Application.getCurrentEmail(), Toast.LENGTH_LONG).show();
	         	EventController eventController = new EventController();
  				eventController.joinEvent(currentEvent.getID() ,  Application.getCurrentEmail());
  				//Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
  				//Application.getAppContext().startActivity(homeActivity);
  				
            }
        });
        
        

       Button b2 = new Button(this);
        b2.setText("Cancel");
        b.setId(102);
        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        my_layout.addView(b2);
        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	Intent eventsIntent = new Intent(getApplicationContext(),EventsMenuActivity.class);
				startActivity(eventsIntent);
				
            }
        });
        */


	}
	
	
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{

		    case R.id.reviewEvent:
		    {
		    	Toast.makeText(Application.getAppContext(), currentEvent.getID() +  Application.getCurrentEmail(), Toast.LENGTH_LONG).show();
	         	//EventController eventController = new EventController();
  				//eventController.joinEvent(currentEvent.getID() ,  Application.getCurrentEmail());
  				break;
			}
	
		    case R.id.goBack:
		    {
		    	Toast.makeText(Application.getAppContext(),"GO BACK", Toast.LENGTH_LONG).show();
	         	break;
			}
	
		    default:
		    {    
		    	break;
		    }
	    
		}

		
		
	}

}
