package com.androidActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.PostController;
import com.controllers.UserController;


public class CreatePostActivity extends Activity implements OnClickListener {

	EditText postEditText;
	Button postButton, viewPostsButton;
	
	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);
		postEditText = (EditText) findViewById(R.id.postEditText);
		postButton = (Button) findViewById(R.id.postButton);
		
		myCategories = Application.getCategories();
		
		LinearLayout my_layout = (LinearLayout) findViewById(R.id.selectCategoryLayout);
		
		// loop of generation of check boxes
		for (int i = 0; i < myCategories.size(); i++) {
			TableRow row = new TableRow(this);
			row.setId(i);
			row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			CheckBox checkBox = new CheckBox(this);
			checkBox.setTag(myCategories);
			checkBox.setId(i);
			checkBox.setText(myCategories .get(i));
			checks.add(checkBox);
			row.addView(checkBox);
			my_layout.addView(row);

		}

		postButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) 
	{
		for (int i = 0; i < checks.size(); i++) {
			if (checks.get(i).isChecked()) {
				checkedCategories += checks.get(i).getText() + ";";
				System.out.println(checkedCategories);
			}
		}

		if(Application.getCurrentUser().get_email().equals("") || Application.getCurrentUser().get_email()==null)
		{
			Toast.makeText(getApplicationContext(),"Error, you are not logged in!!", Toast.LENGTH_SHORT).show();
					
		}
		else
		{	
			PostController postController = new PostController();
			postController.addPost("normal", postEditText.getText().toString(), "no-pic", 
					Application.getCurrentDistrict(),"", Application.getCurrentUser().get_email(), checkedCategories);	
		}
	}
			

}

