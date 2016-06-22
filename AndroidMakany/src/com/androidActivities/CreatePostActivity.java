package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.PostController;


public class CreatePostActivity extends Fragment implements OnClickListener {
	
	View rootView;
	EditText postEditText;
	Button postButton, viewPostsButton;
	
	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();
	
	
	public CreatePostActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_post, container,
				false);
		
		postEditText = (EditText)  rootView.findViewById(R.id.postEditText);
		postButton = (Button)  rootView.findViewById(R.id.postButton);
		
		myCategories = Application.getCategories();
		
		LinearLayout my_layout = (LinearLayout)  rootView.findViewById(R.id.selectCategoryLayout);
		
		if (myCategories != null)
		{
			// loop of generation of check boxes
			for (int i = 0; i < myCategories.size(); i++) {
				TableRow row = new TableRow(getActivity());
				row.setId(i);
				row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				CheckBox checkBox = new CheckBox(getActivity());
				checkBox.setTag(myCategories);
				checkBox.setId(i);
				checkBox.setText(myCategories .get(i));
				checks.add(checkBox);
				row.addView(checkBox);
				my_layout.addView(row);

			}
		}
		

		postButton.setOnClickListener(this);
		return rootView;
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
			Toast.makeText(getActivity().getApplicationContext(),"Error, you are not logged in!!", Toast.LENGTH_SHORT).show();
					
		}
		else
		{	
			PostController postController = new PostController();
			postController.addPost("normal", postEditText.getText().toString(), "", 
					Application.getCurrentDistrict(),"", Application.getCurrentUser().get_email(), checkedCategories);	
		}
	}
			

}

