package com.androidActivities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.GPSTracker;
import com.controllers.SessionController;
import com.controllers.UserController;
import com.controllers.WhatsNewController;
import com.simpleModels.Element;
import com.simpleModels.SimpleEvent;
import com.simpleModels.SimpleItem;
import com.simpleModels.SimpleOffer;
import com.simpleModels.SimplePost;
import com.simpleModels.SimpleStore;
import com.simpleModels.ViewElements;

public class WhatsNew extends MyDrawerMenu implements OnRefreshListener {
	
	private SwipeRefreshLayout mSwipeRefreshLayout;
	String currentEmail ="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whats_new);
		super.onCreateDrawer();
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.whatsNewLayout);
		
		
		if (!Application.isHavePredefined())
			SessionController.getPredefined();
	       
	    UserController userController = new UserController();
			
	    currentEmail = Application.getUserEmail();
		if(!Application.loggedIn)
		{
			userController.getUser(currentEmail);
			SessionController.login();		
		}
		
		
		
         
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
		if (elements != null)
		{
			for (int i = 0; i < elements.size(); i++) 
			{
				switch(elements.get(i).type)
				{
					case LOANITEM: 
						v.viewItem((SimpleItem) elements.get(i), i, my_layout, this , true);
				        break;
				        
					case REQUESTITEM: 
						v.viewItem((SimpleItem) elements.get(i), i, my_layout, this , false);
						break;
							
					case EVENT:	
						v.viewEvent((SimpleEvent) elements.get(i), i, my_layout, this);

				        break;
				        
					case POST:
						v.viewPost((SimplePost) elements.get(i), i, my_layout, this);
						break;
					
					case STORE:
						v.viewStore((SimpleStore) elements.get(i), i, my_layout, this);
						break;
						
					case OFFER:
						v.viewOffer((SimpleOffer) elements.get(i), i, my_layout, this);
						break;
				}
				/*View ruler = new View(this); ruler.setBackgroundColor(0xff000000);
				my_layout.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));*/
			}
		}
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		GPSTracker.isStatic = false;
		Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
     	WhatsNewController whatsNewController = new WhatsNewController();
			whatsNewController.getStaticRecommendation(Application.getCurrentUser().get_email());

	}
	
	


}
