package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.MessageController;
import com.simpleModels.SimpleMessage;

public class SingleMessage extends MyDrawerMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_message);
		super.onCreateDrawer();
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.singleMessageLayout);

		ArrayList<SimpleMessage> messgaes = new ArrayList<SimpleMessage>(); 
		messgaes = Application.getMessgaes();

		for (int i = 0; i < messgaes.size(); i++) 
		{
			final SimpleMessage temp = messgaes.get(i);
			TextView username = new TextView(this);
			username.setText("Name: " + temp.getSenderName() );
	        my_layout.addView(username); 
	        
	        TextView content = new TextView(this);
	        content.setText(temp.getContent() );
	        content.setTypeface(null, Typeface.BOLD);
	        my_layout.addView(content); 
	        
	        View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
			my_layout.addView(ruler,
			 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));

		}
		
		final EditText sendContent = new EditText(this);
		sendContent.setHeight(150);
        my_layout.addView(sendContent); 
        
        Button b = new Button(this);
        b.setText("Send");
        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	MessageController msgcontroller = new MessageController();
            	msgcontroller.sendMessage(Application.getCurrentUser().get_email(), Application.getMsgTo(), sendContent.getText().toString());
            	finish();
            	msgcontroller = new MessageController();
            	msgcontroller.getChatMessages(Application.getCurrentUser().get_email(), Application.getMsgTo() );
            	
            }
        });
        my_layout.addView(b);
	}

	
}
