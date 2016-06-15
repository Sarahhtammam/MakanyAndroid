package com.androidActivities;

import java.util.ArrayList;

import com.controllers.Application;
import com.controllers.GPSTracker;
import com.controllers.WhatsNewController;
import com.simpleModels.Element;
import com.simpleModels.SimpleEvent;
import com.simpleModels.SimpleItem;
import com.simpleModels.ViewElements;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class WhatsNew extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whats_new);
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.whatsNewLayout);
		
		 Button b = new Button(this);
         b.setText("Refresh");
         b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
         b.setOnClickListener(new OnClickListener() {
             public void onClick(View v) 
             {
            	GPSTracker.isStatic = false;
             	WhatsNewController whatsNewController = new WhatsNewController();
 				whatsNewController.getDynamicRecommendation(Application.getCurrentUser().get_email());

             }
         });
         my_layout.addView(b);
         
		GPSTracker gps;
		
		 // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled     
        if(gps.canGetLocation()){
        	GPSTracker.isStatic = false;
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
             
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 
            viewElement(my_layout);
        }else if (!GPSTracker.isStatic){
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
           // my_layout.removeAllViewsInLayout();
        }
        else
        {
        	viewElement(my_layout);
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
