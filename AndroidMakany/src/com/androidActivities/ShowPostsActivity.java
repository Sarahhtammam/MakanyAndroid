package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.controllers.PostController;
import com.simpleModels.SimplePost;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowPostsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_posts);
		
		ArrayList<SimplePost> posts_Array = null;
		PostController postController = new PostController();
		postController.getPost("sarahhtammam@hotmail.com", "", "maadi", "", "");
	
		posts_Array = Application.getPosts();
		
		//System.out.println("Posts1: " + posts_Array.get(0).getContent());
		//System.out.println("Posts2: " + posts_Array.get(1).getContent());
				
		//Button viewPostsButton = (Button) findViewById(R.id.viewPostButton);
		
					
		/*viewPostsButton.setOnClickListener(new OnClickListener() 
		{
		
			@Override
			public void onClick(View v) 
			{
				ArrayList<SimplePost> post_Array = null;
				PostController postController = new PostController();
				postController.getPost("sarahhtammam@hotmail.com", "", "maadi", "", "");
			
			}
			});
	
		
*/		/*LinearLayout my_layout = (LinearLayout)findViewById(R.id.postLayout);

		for (int i = 0; i < post_Array.size(); i++) 
		{
		    TableRow row =new TableRow(this);
		    row.setId(i);
		    row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		    TextView post_content = new TextView(this);
		    post_content.setId(i);
		    post_content.setText(post_Array.get(i).getContent());
		    row.addView(post_content);  
		    my_layout.addView(row);
		}
		*/
	}
}
