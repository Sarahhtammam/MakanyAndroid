package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.simpleModels.SimplePost;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowPostsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimplePost> posts;
	LinearLayout my_layout;

	
	public ShowPostsActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_posts,
				container, false);
		
		
		final ShowPostsActivity me = this;
		
		posts = new ArrayList<SimplePost>(); 
		        
		my_layout = (LinearLayout)rootView.findViewById(R.id.userPostsLayout);
		
		posts = Application.getPosts();
		
		return rootView;
	}
	

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		posts = Application.getPosts();
		if (posts!=null)
		{
			my_layout.removeAllViews();
			TextView x = new TextView(getActivity());
	        x.setText(str);
	        x.setTypeface(null, Typeface.BOLD);
	        my_layout.addView(x); 
			//loop of generation of events 
			for (int i = 0; i < posts.size(); i++) 
			{
				final SimplePost temp = posts.get(i);
				
				//to get normal posts only, no event posts
				if(!temp.getPostType().equals("normal"))
					continue;
				
				TextView owner = new TextView(getActivity());
		        owner.setText("Post Owner: " + temp.getUserName());
		        my_layout.addView(owner); 

		        
		        TextView content = new TextView(getActivity());
		        content.setText("Content: " + temp.getContent() );
		        my_layout.addView(content); 
		       
		        TextView score = new TextView(getActivity());
		        score.setText("Score: " + (Integer.toString(temp.getScore())) );
		        my_layout.addView(score); 
		        
		        TextView line = new TextView(getActivity());
		        line.setText("----------------------------");
		        my_layout.addView(line); 
		        
		        
		        TextView approvals = new TextView(getActivity());
		        approvals.setText("Approvals: " + (Integer.toString(temp.getNumApprovals())) );
		        my_layout.addView(approvals); 
		       
		         TextView disAapprovals = new TextView(getActivity());
		        disAapprovals.setText("disAapprovals: " + (Integer.toString(temp.getNumDisApprovals())) );
		        my_layout.addView(disAapprovals); 
		        
		        TextView reports= new TextView(getActivity());
		        reports.setText("Reports: " + (Integer.toString(temp.getNumReports())) );
		        my_layout.addView(reports); 
		       
		        //this button to open the post in new intent with its details ( who approved .... and Comments)
		        Button b = new Button(getActivity());
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
	
	
	
}
