package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.controllers.GPSTracker;
import com.controllers.WhatsNewController;

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
import android.widget.Toast;

public class WhatsNew_Dynamic extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whats_new__dynamic);
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.whatsNewDynamicLayout);
		GPSTracker gps;
		
		 // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled     
        if(gps.canGetLocation()){
             
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
             
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 
            viewElement(my_layout);
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            my_layout.removeAllViewsInLayout();
            
            Button b = new Button(this);
            b.setText("Go to static");
            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            b.setOnClickListener(new OnClickListener() {
                public void onClick(View v) 
                {
                	WhatsNewController whatsNewController = new WhatsNewController();
    				whatsNewController.getStaticRecommendation(Application.getCurrentUser().get_email());

                }
            });
            my_layout.addView(b);
            
            Button b2 = new Button(this);
            b2.setText("Refresh");
            b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            b2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) 
                {
                	WhatsNewController whatsNewController = new WhatsNewController();
    				whatsNewController.getDynamicRecommendation(Application.getCurrentUser().get_email());

                }
            });
            my_layout.addView(b2);
        }
		
		
		
		
	}
	
	void viewElement(LinearLayout my_layout)
	{
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
