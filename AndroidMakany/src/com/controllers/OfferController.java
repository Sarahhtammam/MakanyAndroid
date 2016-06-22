package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Toast;

import com.simpleModels.SimpleOffer;


public class OfferController 
{
	public void addOffer(String storeMail, String description, String photo)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/addOfferService",
				storeMail, description, photo, "addOfferService");
	}
	
	public void removeOffer(String offerID)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/removeOfferService",
				offerID, "removeOfferService");
	}
	
	public void viewOffer(String offerID, String userEmail)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/viewOfferService",
				offerID, userEmail, "viewOfferService");
	}
	
	public void thumbsUp(String offerID, String userEmail)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/thumbsUpOfferService",
				offerID, userEmail, "thumbsUpOfferService");
	}
	
	public void thumbsDown(String offerID, String userEmail)
	{
		Connection connectionClass = new Connection();
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/thumbsDownOfferService",
				offerID, userEmail, "thumbsDownOfferService");
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
			if (serviceType.equals("addOfferService"))
				urlParameters = "storeMail="+ params[1] +"&description="+ params[2] +"&photo=" + params[3];
			
			else if (serviceType.equals("viewOfferService")
					|| serviceType.equals("thumbsUpOfferService")
					|| serviceType.equals("thumbsDownOfferService"))
				urlParameters = "offerID="+ params[1] +"&userEmail="+ params[2];
		
			else if(serviceType.equals("removeOfferService"))
				urlParameters = "offerID="+ params[1];	
			
			
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
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{

			super.onPostExecute(result);

			
			try 
			{
				if (serviceType.equals("addOfferService")) 
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
						Toast.makeText(Application.getAppContext(), "error: offer was not added",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					//Post added successfully 
					Toast.makeText(Application.getAppContext(), "SUCCESS", Toast.LENGTH_LONG).show();
					
				}
				
				if (serviceType.equals("removeOfferService")) 
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
						Toast.makeText(Application.getAppContext(), "error: not able to remove offer",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					
					//Post deleted successfully 
					Toast.makeText(Application.getAppContext(), "offer removed!",
					Toast.LENGTH_LONG).show();
					
				}
				
				if (serviceType.equals("editOfferService")) 
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
						Toast.makeText(Application.getAppContext(), "error: not able to edit offer",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					Toast.makeText(Application.getAppContext(), "Successfully edited your offer!",
					Toast.LENGTH_LONG).show();
					
				}
				
				if (serviceType.equals("thumbsUpOfferService")) 
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
						Toast.makeText(Application.getAppContext(), "error: not able to add the thumbs up",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("ThumbsDownBefore"))
					{
						Toast.makeText(Application.getAppContext(), "You have already did this",
						Toast.LENGTH_LONG).show();
						return;
					}
					Toast.makeText(Application.getAppContext(), "thumbs Up!",
					Toast.LENGTH_LONG).show();
					
				}
				
				if (serviceType.equals("thumbsDownOfferService")) 
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
						Toast.makeText(Application.getAppContext(), "error: not able to add the thumbs down",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					if(object.getString("Status").equals("ThumbsDownBefore"))
					{
						Toast.makeText(Application.getAppContext(), "You have already did this",
						Toast.LENGTH_LONG).show();
						return;
					}
					Toast.makeText(Application.getAppContext(), "thumbs Down!",
					Toast.LENGTH_LONG).show();
					
				}

				if (serviceType.equals("viewOfferService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					try
					{
						
						SimpleOffer simpleOffer= new SimpleOffer(object.getString("ID"), object.getString("description"),
								object.getString("storeMail"),object.getString("photo"),object.getString("date"),
										object.getString("numThumbsup"), object.getString("numThumbsDown"),
										object.getString("numViewers"), object.getString("thumbsupMails"),
										object.getString("thumbsdownMails"), object.getString("viewersMails"));
						
						Application.setCurrentOffer(simpleOffer);
					
					}
			
					
			
					catch (JSONException e) 
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_LONG).show();
				
					}
					
					return;
				
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
