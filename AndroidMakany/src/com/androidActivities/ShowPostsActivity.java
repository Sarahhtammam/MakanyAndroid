package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidActivities.R.color;
import com.controllers.Application;
import com.controllers.AsyncResponse;
import com.controllers.PostController;
import com.simpleModels.SimplePost;

public class ShowPostsActivity extends Fragment implements  AsyncResponse {
	
	View rootView;
	ArrayList<SimplePost> posts;
	LinearLayout my_layout_big;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	
	public ShowPostsActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_show_posts,
				container, false);
		
		
		final ShowPostsActivity me = this;
		
		posts = new ArrayList<SimplePost>(); 
		        
		my_layout_big = (LinearLayout)rootView.findViewById(R.id.userPostsLayout);
		
		posts = Application.getPosts();
		Application.setComments(null);
		return rootView;
	}
	

	@Override
	public void processFinish(String str) {
		// TODO Auto-generated method stub
		posts = Application.getPosts();
		if (posts!=null)
		{
			my_layout_big.removeAllViews();
			
	
	        //loop of generation of posts 
			for (int i = 0; i < posts.size(); i++) 
			{
				final SimplePost temp = posts.get(i);
				
				//to get normal posts only, no event posts
				if(!temp.getPostType().equals("normal"))
					continue;
				
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.setMargins(0, 5, 0, 5);

				LinearLayout my_layout = new LinearLayout(getActivity());
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

				TextView date = new TextView(getActivity());
				date.setText("  " + temp.getDate());
				my_layout.addView(date);

				LinearLayout lin_hor = new LinearLayout(getActivity());
				lin_hor.setOrientation(0);

				TextView owner = new TextView(getActivity());
				owner.setText("  " + temp.getUserName());
				owner.setTypeface(null, Typeface.BOLD);
				owner.setTextColor(color.darkpurple);
				lin_hor.addView(owner);
				TextView owner2 = new TextView(getActivity());
				owner2.setText(" added a post");
				lin_hor.addView(owner2);

				my_layout.addView(lin_hor);

				TextView Content = new TextView(getActivity());
				Content.setText("  " + temp.getContent());
				Content.setTypeface(null, Typeface.BOLD);
				Content.setTextAppearance(getActivity(),
						android.R.style.TextAppearance_Medium);
				my_layout.addView(Content);

				if (!temp.getPhoto().equals("")) {
					TextView photo = new TextView(getActivity());
					photo.setText("  " + temp.getPhoto());
					Linkify.addLinks(photo, Linkify.ALL);
					my_layout.addView(photo);
				}

				LinearLayout lin_hor2 = new LinearLayout(getActivity());
				lin_hor2.setOrientation(0);

				TextView district = new TextView(getActivity());
				district.setText("  " + temp.getDistrict());
				lin_hor2.addView(district);

				TextView category = new TextView(getActivity());
				category.setText(" - " + temp.getCategories());
				lin_hor2.addView(category);

				LinearLayout lin_hor3 = new LinearLayout(getActivity());
				lin_hor3.setOrientation(0);
				
				ImageView image = new ImageView(getActivity()); 
				image.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.score_post));
				lin_hor3.addView(image);

				TextView score = new TextView(getActivity());
				score.setText("  " + temp.getScore()+ "  ");
				lin_hor3.addView(score);
				
				ImageView image2 = new ImageView(getActivity()); 
				image2.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.thumbs_up));
				lin_hor3.addView(image2);
				
				TextView approval = new TextView(getActivity());
				approval.setText("  " + temp.getNumApprovals() + "  ");
				lin_hor3.addView(approval);
				
				ImageView image3 = new ImageView(getActivity()); 
				image3.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.thumbs_down));
				lin_hor3.addView(image3);
				
				TextView disapproval = new TextView(getActivity());
				disapproval.setText("  " + temp.getNumDisApprovals() + "  ");
				lin_hor3.addView(disapproval);

				Button b2 = new Button(getActivity());
				b2.setText("Show More");
				b2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				b2.setId(i + 1);
				b2.setTag(temp.getID());
				b2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						PostController postController = new PostController();
		            	postController.getComments(temp.getID(), "", "");
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
			
		}
			
		
	}

	
	
}
