package com.androidActivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.EventController;
import com.controllers.PostController;
import com.simpleModels.SimpleEvent;

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

        Button reviewEvent = (Button) findViewById(R.id.reviewEvent);
		Button cancel = (Button) findViewById(R.id.goBack);
    	Button atendEvent = (Button) findViewById(R.id.AttendEvent);
        Button disAttendEvent = (Button) findViewById(R.id.DisAttendEvent);
        Button addPost = (Button) findViewById(R.id.postOnEvent);
    	
        reviewEvent.setOnClickListener(this);
		cancel.setOnClickListener(this);
		atendEvent.setOnClickListener(this);
		disAttendEvent.setOnClickListener(this);
		addPost.setOnClickListener(this);
		
		//     	EventController eventController = new EventController();
		//  	eventController.joinEvent(currentEvent.getID() ,  Application.getCurrentEmail());



	}
	
	
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{

		    case R.id.reviewEvent:
		    {
		    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

		    	alert.setTitle("Event Review");
		    	alert.setMessage("write your review");

		    	
		    	LinearLayout layout = new LinearLayout(this);
		    	layout.setOrientation(LinearLayout.VERTICAL);

		    	final EditText userEventReview = new EditText(this);
		    	userEventReview.setHint("review");
		    	layout.addView(userEventReview);

		    	final EditText userEventRating = new EditText(this);
		    	userEventRating.setHint("rating ( 1- 10 ) ");
		    	layout.addView(userEventRating);

		    	
		    	alert.setView(layout);
		    	//alert.setView(userEventRating);

		    	alert.setPositiveButton("Add Review", new DialogInterface.OnClickListener() {
			    	public void onClick(DialogInterface dialog, int whichButton) 
			    	{
			    		String reviewText = userEventReview.getText().toString() ;
			    		String ratingText = userEventRating.getText().toString() ;
			    		
			    		EventController eventController = new EventController();
			    		eventController.reviewEvent(Application.getUserEmail(),currentEvent.getID() , reviewText, ratingText);
			    	}
		    	});

		    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    	  public void onClick(DialogInterface dialog, int whichButton) 
		    	  {
		    			Toast.makeText(Application.getAppContext(),"Your review is cancelled", Toast.LENGTH_LONG).show();
				   }
		    	});

		    	alert.show();
		    	
		    	
		    	//EventController eventController = new EventController();
  				//eventController.joinEvent(currentEvent.getID() ,  Application.getCurrentEmail());
  				break;
			}
		    
		    
		    
		    case R.id.postOnEvent:
		    {
		    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

		    	alert.setTitle("Add Posr");
		    	alert.setMessage("write your post");

		    	
		    	LinearLayout layout = new LinearLayout(this);
		    	layout.setOrientation(LinearLayout.VERTICAL);

		    	final EditText postText = new EditText(this);
		    	postText.setHint("post");
		    	layout.addView(postText);
		    	alert.setView(layout);

		    	alert.setPositiveButton("Add Post", new DialogInterface.OnClickListener() {
			    	public void onClick(DialogInterface dialog, int whichButton) 
			    	{
			    		String postTextt = postText.getText().toString() ;
			    		PostController postController = new PostController();
			 			postController.addPost("event", postTextt, "no-pic", 
			 			"maadi", currentEvent.getID(), Application.getUserEmail(), "");
			    	}
		    	});

		    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    	  public void onClick(DialogInterface dialog, int whichButton) 
		    	  {
		    			Toast.makeText(Application.getAppContext(),"Your post is cancelled", Toast.LENGTH_LONG).show();
				   }
		    	});

		    	alert.show();
		    	
		    	
		    	break;
			}
	
		    
		    
	
		    case R.id.goBack:
		    {
		    	Intent eventsIntent = new Intent(getApplicationContext(),EventsMenuActivity.class);
				startActivity(eventsIntent);
				break;
			}
		    
		    case R.id.AttendEvent:
		    {
		    	
		    	EventController eventController = new EventController();
  				eventController.joinEvent(currentEvent.getID(), Application.getUserEmail());
  				break;
		    }
		    
		    case R.id.DisAttendEvent:
		    {
		    	EventController eventController = new EventController();
  				eventController.cancelGoingEvent(currentEvent.getID(), Application.getUserEmail());
  				break;
			}
	
		   	   
			
		    default:
		    {    
		    	break;
		    }
	    
		}

		
		
	}

}
