package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.MessageController;
import com.simpleModels.SimpleMessage;

public class SingleMessage extends MyDrawerMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_message);
		super.onCreateDrawer();
		
		LinearLayout my_layout_big = (LinearLayout)findViewById(R.id.singleMessageLayout);

		ArrayList<SimpleMessage> messgaes = new ArrayList<SimpleMessage>(); 
		messgaes = Application.getMessgaes();
		
		if (messgaes != null)
		{
			for (int i = 0; i < messgaes.size(); i++) 
			{
				final SimpleMessage temp = messgaes.get(i);
				
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
				
				
				TextView username = new TextView(this);
				username.setText("  " + temp.getSenderName() );
				username.setTypeface(null, Typeface.BOLD);
				username.setTextColor(color.darkpurple);
		        my_layout.addView(username); 
		        
		        TextView content = new TextView(this);
		        content.setText("  " + temp.getContent() );
		        content.setTypeface(null, Typeface.BOLD);
		        content.setTextAppearance(this,
						android.R.style.TextAppearance_Medium);
		        my_layout.addView(content); 
		        
		        my_layout_big.addView(my_layout);
		        
		        /*View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
				my_layout.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));*/

			}
			
		}
		
		
		
		final EditText sendContent = new EditText(this);
		sendContent.setHeight(150);
        my_layout_big.addView(sendContent); 
        
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
        my_layout_big.addView(b);
	}

	
}
