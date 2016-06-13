package com.androidActivities;

import java.util.ArrayList;

import com.controllers.PostController;

import SimpleModels.SimplePost;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewPostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_post);
		
		
		ArrayList<SimplePost> post_Array = null;
		PostController postController = new PostController();
		postController.getPost("sarahhtammam@hotmail.com", "", "maadi", "", "");
		
		
		/*LinearLayout my_layout = (LinearLayout)findViewById(R.id.postLayout);

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
