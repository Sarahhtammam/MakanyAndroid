package com.androidActivities;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.controllers.Application;
import com.controllers.ItemController;
import com.controllers.MessageController;
import com.simpleModels.SimpleItem;

public class SingleItemActivity extends MyDrawerMenu {
	
	final SimpleItem currentItem = Application.getCurrentItem();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_item);
		super.onCreateDrawer();
		
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
                	Application.setMsgTo(currentItem.getUserEmail());
                	MessageController msgcontroller = new MessageController();
	            	msgcontroller.getChatMessages(Application.getCurrentUser().get_email(), currentItem.getUserEmail());
	            	


                }
            });
            my_layout.addView(msgButton);
        }
        
	}

	
}
