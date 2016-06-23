package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.MessageController;
import com.simpleModels.SimpleMessage;

public class MyMessages extends MyDrawerMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_messages);
		super.onCreateDrawer();
		
		LinearLayout my_layout_big = (LinearLayout)findViewById(R.id.myMessagesLayout);

		ArrayList<SimpleMessage> msgNames = new ArrayList<SimpleMessage>(); 
		msgNames = Application.getMsgNames();
		
		if (msgNames != null)
		{
			for (int i = 0; i < msgNames.size(); i++) 
			{
				final SimpleMessage temp = msgNames.get(i);
				
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.setMargins(0, 5, 0, 5);

				LinearLayout my_layout = new LinearLayout(this);
				my_layout.setLayoutParams(params);

				my_layout.setOrientation(LinearLayout.VERTICAL);

				GradientDrawable border = new GradientDrawable();

				border.setColor(0xFFFFFFFF); // white background
				border.setStroke(1, 0xFF000000); // black border with full opacity
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
					my_layout.setBackgroundDrawable(border);
				} else {
					my_layout.setBackground(border);
				}

				
				TextView name = new TextView(this);
		        name.setText("  " + temp.getSenderName()  );
		        name.setTypeface(null, Typeface.BOLD);
		        name.setTextColor(color.darkpurple);
		        my_layout.addView(name); 

		        
		        Button b = new Button(this);
		        b.setText("Show messages");
		      
		        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		        b.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) 
		            {
		            	Application.setMsgTo(temp.getSenderMail());
		            	MessageController msgcontroller = new MessageController();
		            	msgcontroller.getChatMessages(Application.getCurrentUser().get_email(), temp.getSenderMail());

		            }
		        });
		        
		        
		        
		        my_layout.addView(b);
		        
		       
		        
		        my_layout_big.addView(my_layout);
		        
				/*View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
				my_layout.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));*/
			}
		}
		
	}
	
	


}
