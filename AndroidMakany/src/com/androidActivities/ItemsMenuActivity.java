package com.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.controllers.ItemController;

public class ItemsMenuActivity extends Activity {
	
	String currentEmail ="";
	Button createItemButton, showAllItems;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
			  super.onCreate(savedInstanceState);
		      setContentView(R.layout.activity_item_menu);
		     createItemButton = (Button) findViewById(R.id.createItemButton);
		      showAllItems = (Button) findViewById(R.id.getAllItemsButton);
		      
		      Intent currentIntent = getIntent();
			  currentEmail = currentIntent.getStringExtra("email");
			
	
			  createItemButton.setOnClickListener(new OnClickListener() 
	          {
	  			
	  			@Override
	  			public void onClick(View arg0) 
	  			{
	  				// TODO Auto-generated method stub
	  				Intent createItemIntent = new Intent(getApplicationContext(),CreateItemActivity.class);
	  				createItemIntent.putExtra("email", currentEmail);
	  				startActivity(createItemIntent);
	  				
	  			}
	  		});
	          
			  showAllItems.setOnClickListener(new OnClickListener() 
	          {
	  			
	  			@Override
	  			public void onClick(View arg0) 
	  			{
	  				ItemController itemController = new ItemController();
	  				itemController.getFilteredLoanItems("", "");
	  				
	  			}
	  		});
	     
	    }

}
