package com.androidActivities;

import java.util.ArrayList;

import SimpleModels.SimpleItem;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;


public class ShowItemsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_items);
		
		ArrayList<SimpleItem> items = new ArrayList<SimpleItem>(); 
		        
		
		items = Application.getItems();
		
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.itemsLayout);
		
		
		//loop of generation of items 
		for (int i = 0; i < items.size(); i++) 
		{
			final SimpleItem temp = items.get(i);
			
			TextView name = new TextView(this);
	        name.setText("Item Name: " + temp.getName() );
	        my_layout.addView(name); 

	        
	        TextView description = new TextView(this);
	        description.setText("Item Description: " + temp.getDescription() );
	        my_layout.addView(description); 
	        
	        TextView category = new TextView(this);
	        category.setText("Item Category: " + temp.getCategories() );
	        my_layout.addView(category); 
	       

	        Button b = new Button(this);
	        b.setText("View item details");
	        b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        b.setId(i+1);
	        b.setTag(temp.getId());
	        b.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) 
	            {
    	         	Intent selectedItem = new Intent(Application.getAppContext(),SingleItemActivity.class);
    	         	selectedItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  				Application.setCurrentItem(temp);
	  				Application.getAppContext().startActivity(selectedItem);

	            }
	        });
	        my_layout.addView(b);
		}
	}


}
