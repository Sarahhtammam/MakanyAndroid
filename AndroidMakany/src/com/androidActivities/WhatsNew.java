package com.androidActivities;

import java.util.ArrayList;

import SimpleModels.Element;
import SimpleModels.SimpleEvent;
import SimpleModels.SimpleItem;
import SimpleModels.ViewElements;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.controllers.Application;
import com.controllers.WhatsNewController;


public class WhatsNew extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whats_new);
		
		
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.whatsNewLayout);
		
		Button b = new Button(this);
        b.setText("Go to dynamic");
        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) 
            {
            	WhatsNewController whatsNewController = new WhatsNewController();
				whatsNewController.getDynamicRecommendation(Application.getCurrentUser().get_email());

            }
        });
        my_layout.addView(b);
		
		ArrayList<Element> elements = new ArrayList<Element>(); 
		elements = Application.getElements();
		
		ViewElements v  = new ViewElements();
		for (int i = 0; i < elements.size(); i++) 
		{
			switch(elements.get(i).type)
			{
				case ITEM: 
					v.viewItem((SimpleItem) elements.get(i), i, my_layout, this);
			        break;
						
				case EVENT:	
					v.viewEvent((SimpleEvent) elements.get(i), i, my_layout, this);

			        break;
			}
			View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
			my_layout.addView(ruler,
			 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
		}
		
		
	}


}
