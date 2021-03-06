package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.androidActivities.PostsMenuActivity;
import com.androidActivities.SinglePostActivity;
import com.simpleModels.SimpleComment;
import com.simpleModels.SimplePost;

public class PostController 
{
	private static  String PostType;
	
	public void addPost(String postType, String content, String photo, String district, String onEventID,
						String userEmail, String categories ) 
	{
		PostType=postType;
		
		new Connection().execute( "http://makanyapp2.appspot.com/rest/addPostService",
				postType, content, photo, district, onEventID, userEmail, categories, "addPostService");
	}
	
	public void deletePost(String postID, String userEmail) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/deletePostService",
		postID, userEmail, "deletePostService");
	}

	public void addCommentOnPost(String content, String postID, String userEmail) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/addCommentService",
		content, postID, userEmail, "addCommentService");
	}
	
	public void approvePost(String postID, String userEmail) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/approvePostService",
		postID, userEmail, "approvePostService");
	}
	public void disapprovePost(String postID, String userEmail) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/disapprovePostService",
		postID, userEmail, "disapprovePostService");
	}
	
	public void reportPost(String reason, String postID, String userEmail) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/reportPostService",
		reason, postID, userEmail, "reportPostService");
	}
	
	public void getPost(String userEmail, String category, String district, String onEventID, String postID, AsyncResponse d) 
	{
		Connection connectionClass = new Connection();
		connectionClass.delegate = d;
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getFilteredPostsService",
				userEmail, category, district, onEventID, postID, "getFilteredPostsService");
	}

	
	public void getComments(String postID, String userEmail, String commentID)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getFilteredCommentsService",
				postID, userEmail, commentID, "getFilteredCommentsService");
	}
	static class Connection extends AsyncTask<String, String, String> 
	{

		String serviceType;
		AsyncResponse delegate;
		
		@Override
		protected String doInBackground(String... params)
		{
			URL url;
			
			/////////////////////////////////
			serviceType = params[params.length - 1];
			String urlParameters="";
			if (serviceType.equals("addPostService"))
				urlParameters = "postType="+ params[1] +"&content="+ params[2] +"&photo="
						+ params[3] +"&district=" + params[4] +"&onEventID" + params[5] + "&userEmail=" 
						+ params[6] +"&categories="+ params[7];
			
			else if (serviceType.equals("deletePostService") 
					|| serviceType.equals("approvePostService")
					|| serviceType.equals("disapprovePostService"))
				urlParameters = "postID="+ params[1] +"&userEmail="+ params[2];
			
			else if (serviceType.equals("getFilteredPostsService"))
				urlParameters = "userEmail=" + params[1] + "&category="+ params[2] +"&district="+ params[3] +"&onEventID="
						+ params[4] + "&postID=" + params[5];
			
			else if(serviceType.equals("addCommentService"))
					urlParameters = "content="+ params[1] +"&postID="+ params[2] +"&userEmail="+ params[3];
			
			else if(serviceType.equals("reportPostService"))
					urlParameters = "reason="+ params[1] +"&postID="+ params[2] +"&userEmail="+ params[3];
		
			else if(serviceType.equals("getFilteredCommentsService"))
				urlParameters = "postID="+ params[1] +"&userEmail="+ params[2] +"&commentID="+ params[3];
	
			

			
			HttpURLConnection connection;
			try {
				url = new URL(params[0]);

				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(60000); // 60 Seconds
				connection.setReadTimeout(60000); // 60 Seconds

				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				System.out.println("urlParameters " + urlParameters);
				System.out.println("URL " + params[0]);
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				
				String line, retJson = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				while ((line = reader.readLine()) != null) {
					retJson += line;
				}
				
				return retJson;

			}
			catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("The Error " + e.getMessage());
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{

			super.onPostExecute(result);
			
			
			
			try 
			{
				if (serviceType.equals("addPostService")) 
				{
					System.out.println("result " + result);
					Toast.makeText(Application.getAppContext(), "Result " + result,
							Toast.LENGTH_LONG).show();
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "add post - Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: post was not added",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					//Post added successfully 
					Toast.makeText(Application.getAppContext(), "SUCCESS", Toast.LENGTH_LONG).show();
					
					if(PostType.equals("normal"))
					{
						Intent postIntent = new Intent(Application.getAppContext(),PostsMenuActivity.class);
						postIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						Application.getAppContext().startActivity(postIntent);
					}
					
					
				}
			
				if (serviceType.equals("deletePostService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "delete post - Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: not able to delete post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("notYourPost"))
					{
						Toast.makeText(Application.getAppContext(), "error: You are not allowed to delete "+
								"this post\n It is not your post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					
					//Post deleted successfully 
					Toast.makeText(Application.getAppContext(), "post deleted!",
					Toast.LENGTH_LONG).show();
					Intent postIntent = new Intent(Application.getAppContext(),PostsMenuActivity.class);
					postIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(postIntent);
					
				}
				
				if (serviceType.equals("addCommentService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "add cooment - Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: not able to add comment",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					//Post added successfully 
					Toast.makeText(Application.getAppContext(), "Comment added",
					Toast.LENGTH_LONG).show();
					/*Intent postIntent = new Intent(Application.getAppContext(),SinglePostActivity.class);
					postIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(postIntent);
					*/
					// go to view post  ...  with new comments
				}
				
				if (serviceType.equals("reportPostService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "report post - Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: not able to report post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					//Post added successfully 
					Toast.makeText(Application.getAppContext(), "reported!",
					Toast.LENGTH_LONG).show();
				}
				
				if (serviceType.equals("approvePostService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: not able to approve post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("alreadyApproved"))
					{
						Toast.makeText(Application.getAppContext(), "error: You are not allowed to approve "+
								"this post\nyou have already approved this post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					
					//Post added successfully 
					Toast.makeText(Application.getAppContext(), "post approved!",
					Toast.LENGTH_LONG).show();
					/*Intent homeIntent = new Intent(Application.getAppContext(),PostsMenuActivity.class);
					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(homeIntent);
					// go to view post with new number of approves
*/				
					}
				
				if (serviceType.equals("disapprovePostService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: not able to disapprove post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("alreadyApproved"))
					{
						Toast.makeText(Application.getAppContext(), "error: You are not allowed to delete"+
								"this post\nyou have already disapproved this post",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					
					/*//Post added successfully 
					Toast.makeText(Application.getAppContext(), "post disapproved!",
					Toast.LENGTH_LONG).show();
					Intent homeIntent = new Intent(Application.getAppContext(),PostsMenuActivity.class);
					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(homeIntent);
					// go to view post with new number of disapproves
*/				
					}
				
				if (serviceType.equals("getFilteredPostsService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimplePost> posts = new ArrayList<SimplePost>();
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimplePost post = new SimplePost(
										object.getString("ID"), object.getString("postType"), 
										object.getString("content"), object.getString("photo"), 
										object.getString("username"), object.getString("userEmail"), 
										object.getString("district"), object.getString("onEventID"), 
										object.getString("score"), object.getString("numApprovals"), 
										object.getString("numDisApprovals"), object.getString("numReports"), 
										object.getString("approvalMails"),object.getString("disapprovalMails"), 
										object.getString("reportMails"), object.getString("categories"));
									posts.add(post);
							}
						} 
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					//Post added successfully 
					Application.setPosts(posts);
					delegate.processFinish("Posts");
					
					//Toast.makeText(Application.getAppContext(), "SUCCESS\nPOST= " + posts.get(0).getContent(),
					//Toast.LENGTH_LONG).show();
					
					/*Intent showPostActivity = new Intent(Application.getAppContext(),ShowPostsActivity.class);
					showPostActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(showPostActivity);*/
			    	
				}

				if (serviceType.equals("getFilteredCommentsService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimpleComment> comments = new ArrayList<SimpleComment>();
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);

							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleComment comment= new SimpleComment(object.getString("ID"), object.getString("userEmail"), 
										object.getString("username"), object.getString("content"), 
										object.getString("date"), "");
									comments.add(comment);
							}
						} 
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					//Post added successfully 
					Application.setComments(comments);
					//delegate.processFinish("Posts");
					
					Intent showPostActivity = new Intent(Application.getAppContext(),SinglePostActivity.class);
					showPostActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(showPostActivity);
			    	
				}

				//Do the same for other services
				//else if(serviceType.equals(""))
				//{}

			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{}

		}

	}


}
