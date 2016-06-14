package com.androidActivities;

import SimpleModels.DrawerItemCustomAdapter;
import SimpleModels.ObjectDrawerItem;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class MyDrawerMenu extends Activity {
	
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	ActionBarDrawerToggle mDrawerToggle;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_drawer_menu);

	}
	
	protected void onCreateDrawer()
	{
		mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[6];
		 
		drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Post");
		drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Event");
		drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Aid");
		drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Store");
		drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Update Profile");
		drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp, "Signout");
		
		DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
		mDrawerList.setAdapter(adapter);
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(
		        this,
		        mDrawerLayout,
		        R.drawable.ic_drawer, 
		        R.string.drawer_open, 
		        R.string.drawer_close 
		        ) {
		    
		    /** Called when a drawer has settled in a completely closed state. */
		    public void onDrawerClosed(View view) {
		        super.onDrawerClosed(view);
		       //().setTitle(mTitle);
		    }
		 
		    /** Called when a drawer has settled in a completely open state. */
		    public void onDrawerOpened(View drawerView) {
		        super.onDrawerOpened(drawerView);
		        //getActionBar().setTitle(mDrawerTitle);
		    }
		};
		 
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		 
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		 
	}
	
	@Override 
	public void setContentView(@LayoutRes int layoutResID) 
	{ 
	    super.setContentView(layoutResID); 
	    onCreateDrawer(); 
	}
	
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	 switch (position) {
			    case 0:
			     
			        break;
			    case 1:
			    	Intent testIntent = new Intent(getApplicationContext(),TestActivity.class);
					startActivity(testIntent);
			        break;
			    case 2:
			    	testIntent = new Intent(getApplicationContext(),ItemsMenuActivity.class);
					startActivity(testIntent);
			    	
			        break;
			    case 3:
			        
			        break;
			    case 4:
			    	break;
			    	
			    case 5:
			    	SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
					Editor editor = pref.edit();
					editor.remove("email");
					editor.commit();
					
					Intent login = new Intent(getApplicationContext(),LoginActivity.class);
					startActivity(login);
			    	break;
			 
			    default:
			        break;
			    }
	    }
	    
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	   if (mDrawerToggle.onOptionsItemSelected(item)) {
	       return true;
	   }
	   
	   return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    mDrawerToggle.syncState();
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
}
