package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.simpleModels.SimplePost;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowPostsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_posts);
		
		ArrayList<SimplePost> posts =new ArrayList<SimplePost>();
		
		
		//System.out.println("Posts1: " + posts_Array.get(0).getContent());
		//System.out.println("Posts2: " + posts_Array.get(1).getContent());
				
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.userPostsLayout);
		
		posts = Application.getPosts();
		
		if (posts!=null)
		
		//loop of generation of events 
		for (int i = 0; i < posts.size(); i++) 
		{
			final SimplePost temp = posts.get(i);
			
			//to get normal posts only, no event posts
			if(!temp.getPostType().equals("normal"))
				continue;
			
			TextView owner = new TextView(this);
	        owner.setText("Post Owner: " + temp.getUserName());
	        my_layout.addView(owner); 

	        
	        TextView content = new TextView(this);
	        content.setText("Content: " + temp.getContent() );
	        my_layout.addView(content); 
	       
	        TextView score = new TextView(this);
	        score.setText("Score: " + (Integer.toString(temp.getScore())) );
	        my_layout.addView(score); 
	        
	        TextView line = new TextView(this);
	        line.setText("----------------------------");
	        my_layout.addView(line); 
	        
	        
	        TextView approvals = new TextView(this);
	        approvals.setText("Approvals: " + (Integer.toString(temp.getNumApprovals())) );
	        my_layout.addView(approvals); 
	       
	         TextView disAapprovals = new TextView(this);
	        disAapprovals.setText("disAapprovals: " + (Integer.toString(temp.getNumDisApprovals())) );
	        my_layout.addView(disAapprovals); 
	        
	        TextView reports= new TextView(this);
	        reports.setText("Reports: " + (Integer.toString(temp.getNumReports())) );
	        my_layout.addView(reports); 
	       
	        //this button to open the post in new intent with its details ( who approved .... and Comments)
	        Button b = new Button(this);
	        b.setText("View post details");
	        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        b.setId(i+1);
	        b.setTag(temp.getID());
	        b.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) 
	            {
	            	//PostController postController = new PostController();
					//postController.getPost("", "", "", "", temp.getID());
					
    	         	/*Intent selectedEvent = new Intent(Application.getAppContext(),SingleEventActivity.class);
	  				selectedEvent.putExtra("eventID", temp.getID());
	  				selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  				EventController eventController = new EventController();
	  				eventController.getEventByID(temp.getID());
	  				Application.setCurrentEvent(temp);
	  				Application.getAppContext().startActivity(selectedEvent);

*/
	            	}
	        });
	        my_layout.addView(b);
	        
		}
		
	}
	
	
	
}
