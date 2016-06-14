package com.androidActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.controllers.Application;
import com.controllers.MessageController;

public class MyMessages extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_messages);
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.myMessagesLayout);

		ArrayList<String> msgNames = new ArrayList<String>(); 
		msgNames = Application.getMsgNames();

		for (int i = 0; i < msgNames.size(); i++) 
		{
			final String temp = msgNames.get(i);
			TextView name = new TextView(this);
	        name.setText("Name: " + temp  );
	        my_layout.addView(name); 

	        
	        Button b = new Button(this);
	        b.setText("Show messages");
	        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        b.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) 
	            {
	            	MessageController msgcontroller = new MessageController();
	            	msgcontroller.getChatMessages(Application.getCurrentUser().get_email(), temp);

	            }
	        });
	        my_layout.addView(b);
	        
			View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
			my_layout.addView(ruler,
			 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
		}
	}
	
	


}
