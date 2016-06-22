package com.androidActivities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.PostController;
import com.simpleModels.SimpleComment;
import com.simpleModels.SimplePost;

public class SinglePostActivity extends MyDrawerMenu implements OnClickListener {

	final SimplePost currentPost = Application.getCurrentPost();
	PostController postController = new PostController();
	LinearLayout comments_layout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_post);
		super.onCreateDrawer();

		//Toast.makeText(Application.getAppContext(),"ID=" + currentPost.getID(),Toast.LENGTH_SHORT).show();
		//Toast.makeText(Application.getAppContext(),currentPost.getContent(),Toast.LENGTH_SHORT).show();
		
		LinearLayout my_layout = (LinearLayout) findViewById(R.id.singlePostLayout);


		TextView userName = new TextView(this);
		userName.setText("Post Owner: " + currentPost.getUserName());
		my_layout.addView(userName);

		TextView content = new TextView(this);
		content.setText("Content: " + currentPost.getContent());
		my_layout.addView(content);

		//for loop for comments
		
		
		TextView score = new TextView(this);
        score.setText("Score: " + (Integer.toString(currentPost.getScore())) );
        my_layout.addView(score); 
        
        TextView line = new TextView(this);
        line.setText("---------------------------");
        my_layout.addView(line); 
        
        
        TextView approvals = new TextView(this);
        approvals.setText("Approvals: " + (Integer.toString(currentPost.getNumApprovals())) );
        my_layout.addView(approvals); 
       
         TextView disApprovals = new TextView(this);
        disApprovals.setText("disAapprovals: " + (Integer.toString(currentPost.getNumDisApprovals())) );
        my_layout.addView(disApprovals); 
        
        TextView reports= new TextView(this);
        reports.setText("Reports: " + (Integer.toString(currentPost.getNumReports())) );
        my_layout.addView(reports); 
       

		Button comment = (Button) findViewById(R.id.addCommentOnPost);
		Button cancel = (Button) findViewById(R.id.goBack);
		Button approve = (Button) findViewById(R.id.approvePost);
		Button disApprove = (Button) findViewById(R.id.DisApprovePost);
		Button reportPost = (Button) findViewById(R.id.reportPost);
		Button delete = (Button) findViewById(R.id.deletePost);

		comment.setOnClickListener(this);
		cancel.setOnClickListener(this);
		approve.setOnClickListener(this);
		disApprove.setOnClickListener(this);
		reportPost.setOnClickListener(this);
		delete.setOnClickListener(this);
		

		ArrayList<SimpleComment> comments = Application.getComments();
		comments_layout = (LinearLayout)findViewById(R.id.postComments);
		
		
		if(comments !=null)
		{
			TextView CommentsSectionTitle = new TextView(this);
			CommentsSectionTitle.setText("Comments");
			CommentsSectionTitle.setAllCaps(true);
			CommentsSectionTitle.setTextSize(20);
	        comments_layout.addView(CommentsSectionTitle); 

			
			for (int i = 0; i < comments.size(); i++) 
			{
				final SimpleComment temp = comments.get(i);
				
				TextView owner = new TextView(this);
		        owner.setText("Comment Owner: " + temp.getUserName());
		        comments_layout.addView(owner); 
		        
		        TextView commentContent = new TextView(this);
		        commentContent.setText(temp.getContent());
		        comments_layout.addView(commentContent); 
		            
			}
		}
		
	}

	public void onClick(View v) {

		int id = v.getId();
		
		
		if (id == R.id.reportPost) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Report Post");
			alert.setMessage("write your reason");

			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);

			final EditText userReport = new EditText(this);
			userReport.setHint("reason");
			layout.addView(userReport);

			alert.setView(layout);

			alert.setPositiveButton("Confirm Report",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String reportText = userReport.getText()
									.toString();
							postController.reportPost(reportText, currentPost.getID(), 
									Application.getCurrentUser().get_email());
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(Application.getAppContext(),
									"Your review is cancelled",
									Toast.LENGTH_LONG).show();
						}
					});

			alert.show();

		}

		if (id == R.id.addCommentOnPost) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Add Comment");
			alert.setMessage("write your comment");

			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);

			final EditText commentText = new EditText(this);
			commentText.setHint("comment");
			layout.addView(commentText);
			alert.setView(layout);

			alert.setPositiveButton("Add Comment",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String CommentTextt = commentText.getText().toString();
							postController.addCommentOnPost(CommentTextt, currentPost.getID(), 
									Application.getCurrentUser().get_email());
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(Application.getAppContext(),
									"Your comment is cancelled", Toast.LENGTH_LONG)
									.show();
						}
					});

			alert.show();

		}

		if (id == R.id.goBack) {
			Intent postIntent = new Intent(getApplicationContext(),
					PostsMenuActivity.class);
			startActivity(postIntent);

		}

		if (id == R.id.approvePost) {
			PostController postController = new PostController();
			postController.approvePost(currentPost.getID(), Application.getCurrentUser().get_email());

		}

		if (id == R.id.DisApprovePost) {
			PostController postController = new PostController();
			postController.disapprovePost(currentPost.getID(), Application.getCurrentUser().get_email());

		}
		
		if (id == R.id.deletePost) {
			PostController postController = new PostController();
			postController.deletePost(currentPost.getID(), Application.getCurrentUser().get_email());

		}

	}


}
