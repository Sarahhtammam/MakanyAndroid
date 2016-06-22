package com.simpleModels;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.R.color;
import com.androidActivities.SingleEventActivity;
import com.androidActivities.SingleItemActivity;
import com.androidActivities.SinglePostActivity;
import com.controllers.Application;
import com.controllers.EventController;
import com.controllers.PostController;

public class ViewElements {

	public void viewItem(final SimpleItem temp, int i, LinearLayout my_layout_big,
			Context context , boolean isLoan) {
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}

		TextView date = new TextView(context);
		date.setText("  " + temp.getDate());
		my_layout.addView(date);

		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getUserName());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
		
		if (isLoan)
			owner2.setText(" is loaning an item");
		else
			owner2.setText(" is requesting an item");
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);

		TextView name = new TextView(context);
		name.setText("  " + temp.getName());
		name.setTypeface(null, Typeface.BOLD);
		name.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		my_layout.addView(name);
		
		if (!temp.getPhoto().equals(""))
		{
			TextView photo = new TextView(context);
			photo.setText("  " + temp.getPhoto());
			my_layout.addView(photo);
		}

		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);
		
		TextView district = new TextView(context);
		district.setText("  " + temp.getDistrict());
		lin_hor2.addView(district);

		TextView category = new TextView(context);
		category.setText(" - " + temp.getCategories());
		lin_hor2.addView(category);

		Button b2 = new Button(context);
		b2.setText("Show More");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setTag(temp.getId());
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent selectedItem = new Intent(Application.getAppContext(),
						SingleItemActivity.class);
				selectedItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Application.setCurrentItem(temp);
				Application.getAppContext().startActivity(selectedItem);

			}
		});
		lin_hor2.addView(b2);
		my_layout.addView(lin_hor2);

		my_layout_big.addView(my_layout);
		
	}

	public void viewEvent(final SimpleEvent temp, int i,
			LinearLayout my_layout_big, Context context) {

		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}

		TextView date = new TextView(context);
		date.setText("  " + temp.getDate());
		my_layout.addView(date);

		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getOwnerName());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
		owner2.setText(" organized an event");
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);

		TextView name = new TextView(context);
		name.setText("  " + temp.getName());
		name.setTypeface(null, Typeface.BOLD);
		name.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		my_layout.addView(name);

		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);

		TextView district = new TextView(context);
		district.setText("  " + temp.getDistrict());
		lin_hor2.addView(district);

		TextView category = new TextView(context);
		category.setText(" - " + temp.getCategory());
		lin_hor2.addView(category);

		Button b2 = new Button(context);
		b2.setText("Show More");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setTag(temp.getID());
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent selectedEvent = new Intent(Application.getAppContext(),
						SingleEventActivity.class);
				selectedEvent.putExtra("eventID", temp.getID());
				selectedEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				EventController eventController = new EventController();
				eventController.getEventByID(temp.getID());
				Application.setCurrentEvent(temp);
				Application.getAppContext().startActivity(selectedEvent);

			}
		});
		lin_hor2.addView(b2);
		my_layout.addView(lin_hor2);

		my_layout_big.addView(my_layout);
	}
	
	
	public void FoursquarePlace (final FoursquarePlace temp, int i, LinearLayout my_layout_big,
			Context context) {
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}


		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getName());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
	
		owner2.setText(" is nearby with " + temp.getDistance() + " meters");
	
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);

		TextView address = new TextView(context);
		address.setText("  " + temp.getAddress());
		my_layout.addView(address);
		
		TextView phone = new TextView(context);
		phone.setText("  To call: " + temp.getPhone());
		my_layout.addView(phone);

		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);
		
		TextView category = new TextView(context);
		category.setText("  " + temp.getCategory());
		lin_hor2.addView(category);

		TextView rating = new TextView(context);
		rating.setText(" - " + temp.getRating());
		lin_hor2.addView(rating);

		Button b2 = new Button(context);
		b2.setText("Show On Maps");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				String latitude = temp.getLatitude();
				String longitude = temp.getLongitude();
				String label = temp.getName();
				String uriBegin = "geo:" + latitude + "," + longitude;
				String query = latitude + "," + longitude + "(" + label + ")";
				String encodedQuery = Uri.encode(query);
				String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
				Uri uri = Uri.parse(uriString);
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
				Application.getAppContext().startActivity(intent);

			}
		});
		lin_hor2.addView(b2);
		my_layout.addView(lin_hor2);

		my_layout_big.addView(my_layout);
		
	}
	
	public void viewPost(final SimplePost temp, int i,
			LinearLayout my_layout_big, Context context) {

		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}

		TextView date = new TextView(context);
		date.setText("  " + temp.getDate());
		my_layout.addView(date);

		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getUserName());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
		owner2.setText(" added a post");
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);

		TextView Content = new TextView(context);
		Content.setText("  " + temp.getContent());
		Content.setTypeface(null, Typeface.BOLD);
		Content.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		my_layout.addView(Content);
		
		if (!temp.getPhoto().equals(""))
		{
			TextView photo = new TextView(context);
			photo.setText("  " + temp.getPhoto());
			my_layout.addView(photo);
		}
		

		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);

		TextView district = new TextView(context);
		district.setText("  " + temp.getDistrict());
		lin_hor2.addView(district);

		TextView category = new TextView(context);
		category.setText(" - " + temp.getCategories());
		lin_hor2.addView(category);
		
		LinearLayout lin_hor3 = new LinearLayout(context);
		lin_hor3.setOrientation(0);

		TextView score = new TextView(context);
		score.setText("  " + temp.getScore());
		lin_hor3.addView(score);

		TextView approval = new TextView(context);
		approval.setText(" - " + temp.getNumApprovals());
		lin_hor3.addView(approval);
		
		TextView disapproval = new TextView(context);
		disapproval.setText(" - " + temp.getNumDisApprovals());
		lin_hor3.addView(disapproval);

		Button b2 = new Button(context);
		b2.setText("Show More");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setTag(temp.getID());
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				PostController postController = new PostController();
            	postController.getComments(temp.getID(), Application.getCurrentUser().get_email(), "");
            	Intent selectedPost = new Intent(Application.getAppContext(),SinglePostActivity.class);
            	selectedPost.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  				Application.setCurrentPost(temp);
  				Application.getAppContext().startActivity(selectedPost);

			}
		});
		lin_hor3.addView(b2);
		my_layout.addView(lin_hor3);

		my_layout_big.addView(my_layout);
	}
	
	public void viewStore(final SimpleStore temp, int i,
			LinearLayout my_layout_big, Context context) {

		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}

		TextView date = new TextView(context);
		date.setText("  " + temp.getDate());
		my_layout.addView(date);

		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getStoreName());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
		owner2.setText(" store");
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);


		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);

		TextView district = new TextView(context);
		district.setText("  " + temp.getDistrict());
		lin_hor2.addView(district);

		TextView category = new TextView(context);
		category.setText(" - " + temp.getCategory());
		lin_hor2.addView(category);

		Button b2 = new Button(context);
		b2.setText("Show More");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setTag(temp.getId());
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				

			}
		});
		lin_hor2.addView(b2);
		my_layout.addView(lin_hor2);

		my_layout_big.addView(my_layout);
	}
	
	public void viewOffer(final SimpleOffer temp, int i, LinearLayout my_layout_big,
			Context context ) {
		LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 5, 0, 5);

		LinearLayout my_layout = new LinearLayout(context);
		my_layout.setLayoutParams(params);

		my_layout.setOrientation(LinearLayout.VERTICAL);
		

		GradientDrawable border = new GradientDrawable();

		border.setColor(0xFFFFFFFF); // white background
		border.setStroke(1, 0xFF000000); // black border with full opacity
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			my_layout.setBackgroundDrawable(border);
		} else {
			my_layout.setBackground(border);
		}

		TextView date = new TextView(context);
		date.setText("  " + temp.getDate());
		my_layout.addView(date);

		LinearLayout lin_hor = new LinearLayout(context);
		lin_hor.setOrientation(0);
		
		TextView owner = new TextView(context);
		owner.setText("  " + temp.getStoreMail());
		owner.setTypeface(null, Typeface.BOLD);
		owner.setTextColor(color.darkpurple);
		lin_hor.addView(owner);
		TextView owner2 = new TextView(context);
		
		owner2.setText(" store added an offer");
		
		lin_hor.addView(owner2);

		my_layout.addView(lin_hor);

		TextView description = new TextView(context);
		description.setText("  " + temp.getDescription());
		description.setTypeface(null, Typeface.BOLD);
		description.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		my_layout.addView(description);
		
		if (!temp.getPhoto().equals(""))
		{
			TextView photo = new TextView(context);
			photo.setText("  " + temp.getPhoto());
			my_layout.addView(photo);
		}

		LinearLayout lin_hor2 = new LinearLayout(context);
		lin_hor2.setOrientation(0);
		
		TextView district = new TextView(context);
		district.setText("  " + temp.getDistrict());
		lin_hor2.addView(district);

		TextView category = new TextView(context);
		category.setText(" - " + temp.getCategory());
		lin_hor2.addView(category);

		Button b2 = new Button(context);
		b2.setText("Show More");
		b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b2.setId(i + 1);
		b2.setTag(temp.getID());
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				

			}
		});
		lin_hor2.addView(b2);
		my_layout.addView(lin_hor2);

		my_layout_big.addView(my_layout);
		
	}
}
