package com.androidActivities;

import SimpleModels.SimpleItem;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.EventController;
import com.controllers.ItemController;

public class SingleItemActivity extends Activity {
	
	final SimpleItem currentItem = Application.getCurrentItem();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_item);
		
		LinearLayout my_layout = (LinearLayout)findViewById(R.id.singleItemLayout);
		
		
		TextView name = new TextView(this);
        name.setText("Item Name: " + currentItem.getName() );
        my_layout.addView(name); 

        
        TextView category = new TextView(this);
        category.setText("Item Category: " + currentItem.getCategories() );
        my_layout.addView(category); 
       
        TextView description = new TextView(this);
        description.setText("Item description: " + currentItem.getDescription() );
        my_layout.addView(description); 

        TextView owner = new TextView(this);
        owner.setText("Item owner: " + currentItem.getUserEmail() );
        my_layout.addView(owner); 
        
        // if current user is owner 
        if (Application.getCurrentUser().get_email().equals(currentItem.getUserEmail()))
        {
        	Button editButton = new Button(this);
            editButton.setText("Edit item");
            editButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            editButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) 
                {
    	         	Intent editItem = new Intent(Application.getAppContext(),EditItem.class);
    	         	editItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      				Application.getAppContext().startActivity(editItem);

                }
            });
            my_layout.addView(editButton);
            
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete item");
            deleteButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
      
            deleteButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) 
                {
                	ItemController itemController = new ItemController();
      				itemController.deleteItem(currentItem.getId(), Application.getCurrentUser().get_email());

                }
            });
            my_layout.addView(deleteButton);
        }
        else // if current user is not owner 
        {
        	Button msgButton = new Button(this);
        	msgButton.setText("send message to owner");
        	msgButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
      
        	msgButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) 
                {
                	// send message 

                }
            });
            my_layout.addView(msgButton);
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
