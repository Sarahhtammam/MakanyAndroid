package com.androidActivities;


import com.controllers.AdminController;
import com.controllers.Application;
import com.controllers.PostController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PostsMenuActivity extends Activity implements OnClickListener
{
	Button addPostButton, showMyPosts;
	// , filterPostsByCategoryButton, filterPostsByDistrictButton ;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
		  super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_posts_menu);
	      
	      
	      addPostButton = (Button) findViewById(R.id.addPostButton);
	      showMyPosts = (Button) findViewById(R.id.viewMyPostsButton);
	      //filterPostsByCategoryButton = (Button) findViewById(R.id.filterPostsCButton);
	      //filterPostsByDistrictButton = (Button) findViewById(R.id.filterPostsDButton);
  	      
	    
	      addPostButton.setOnClickListener(this);
	      showMyPosts.setOnClickListener(this);
	      //filterPostsByCategoryButton.setOnClickListener(this);
	      //filterPostsByDistrictButton.setOnClickListener(this);
          
    }


	@Override
	public void onClick(View v) 
	{
	

		switch (v.getId()) 
		{

		    case R.id.addPostButton:
		    {
		    	/*AdminController adminController = new AdminController();
				adminController.getCategories();
				*/
			 	Intent createPostIntent = new Intent(getApplicationContext(),CreatePostActivity.class);
				startActivity(createPostIntent);
	
		    	break;
		    }
		
		    case R.id.viewMyPostsButton:
		    {
		    	PostController postController = new PostController();
				postController.getPost("", "", Application.currentDistrict, "", "");
				
				break;
		    }
		   	
		 /*   case R.id.filterEventsCButton:
		    {
		    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

		    	alert.setTitle("Filter by category");
		    	alert.setMessage("write category");

		    	
		    	LinearLayout layout = new LinearLayout(this);
		    	layout.setOrientation(LinearLayout.VERTICAL);

		    	final EditText categoryEditText= new EditText(this);
		    	categoryEditText.setHint("category");
		    	layout.addView(categoryEditText);

		    	alert.setView(layout);
		 
		    	alert.setPositiveButton("Select", new DialogInterface.OnClickListener() {
			    	public void onClick(DialogInterface dialog, int whichButton) 
			    	{
			    		String categoryText = categoryEditText.getText().toString() ;
			    		Toast.makeText(Application.getAppContext(), categoryText, Toast.LENGTH_LONG).show();
			    		
			    		EventController eventController = new EventController();
		  				eventController.getFilteredEvents(categoryText, "");
			    	}
		    	});

		    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    	  public void onClick(DialogInterface dialog, int whichButton) 
		    	  {
		    			Toast.makeText(Application.getAppContext(),"cancelled", Toast.LENGTH_LONG).show();
				   }
		    	});

		    	alert.show();
		    	
		    	
		    	break;
		    }
		    
		    case R.id.filterEventsDButton:
		    {

		    	final String[] items = {"maadi", "dokki"};
		    	final ArrayList<Integer> seletedItems=new ArrayList<Integer>();

		    	AlertDialog dialog = new AlertDialog.Builder(this)
		    	.setTitle("Select The district")
		    	.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
		    	    @Override
		    	    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
		    	        if (isChecked) {
		    	            // If the user checked the item, add it to the selected items
		    	            seletedItems.add(indexSelected);
		    	        } else if (seletedItems.contains(indexSelected)) {
		    	            // Else, if the item is already in the array, remove it
		    	            seletedItems.remove(Integer.valueOf(indexSelected));
		    	        }
		    	    }
		    	}).setPositiveButton("Select", new DialogInterface.OnClickListener() {
		    	    @Override
		    	    public void onClick(DialogInterface dialog, int id) 
		    	    {
		    	    	EventController eventController = new EventController();
		  				eventController.getFilteredEvents("", items[seletedItems.get(0)]);
			        }
		    	}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    	    @Override
		    	    public void onClick(DialogInterface dialog, int id) {
		    	        //  Your code when user clicked on Cancel
		    	    }
		    	}).create();
		    	dialog.show();
		    	
		    	
		    	
		    	
		    	break;
		    }
*/			
		    default:
		    {    
		    	break;
		    }
	    
		}

	}

}
