package com.androidActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.controllers.Application;
import com.controllers.MessageController;
import com.controllers.SessionController;
import com.controllers.WhatsNewController;
import com.simpleModels.DrawerItemCustomAdapter;
import com.simpleModels.ObjectDrawerItem;

public class MyDrawerMenu extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	ActionBarDrawerToggle mDrawerToggle;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_drawer_menu);
		mContext = this;

	}

	protected void onCreateDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[8];

		drawerItem[0] = new ObjectDrawerItem(R.drawable.home_icon,
				"Home");
		drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp,
				"Posts");
		drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp,
				"Events");
		drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp,
				"Neighbours Aid");
		drawerItem[4] = new ObjectDrawerItem(R.drawable.store_icon,
				"Stores");
		drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp,
				"My Messages");
		drawerItem[6] = new ObjectDrawerItem(R.drawable.updateprofile_icon,
				"Update Profile");
		drawerItem[7] = new ObjectDrawerItem(R.drawable.ic_grade_black_24dp,
				"Signout");

		DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this,
				R.layout.listview_item_row, drawerItem);
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				// ().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// getActionBar().setTitle(mDrawerTitle);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		onCreateDrawer();
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {

			case 0: // Home
				WhatsNewController whatsNewController = new WhatsNewController();
				whatsNewController.getStaticRecommendation(Application.getUserEmail());

				break;

			case 1: // post
				Intent postMenuIntent = new Intent(getApplicationContext(),
						PostsMenuActivity.class);
				startActivity(postMenuIntent);

				break;
			case 2: // event
				Intent eventMenuIntent = new Intent(getApplicationContext(),
						EventsMenuActivity.class);
				startActivity(eventMenuIntent);
				break;
			case 3: // aid
				Intent itemMenuIntent = new Intent(getApplicationContext(),
						ItemsMenuActivity.class);
				startActivity(itemMenuIntent);
				break;
			case 4: // store
				break;
				
			case 5: // messages
				MessageController msgController = new MessageController();
				msgController.getMsgNames(Application.getCurrentUser()
						.get_email());
				break;

			case 6: //  update profile
				Intent updateprofile = new Intent(getApplicationContext(), UpdateMyProfileActivity.class);
				startActivity(updateprofile);
		
				break;

			case 7: // signout
				//SessionController.signout();
				SessionController.showSignoutDialog(mContext);
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
