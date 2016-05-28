package com.controllers;

import com.controllers.StoreController.Connection;

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

import SimpleModels.FilteredPost;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.androidActivities.HomeActivity;

public class StoreController {
	

	public void reviewStoreService(String uMail,String storeMail,String review,String rating ) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/addOfferService",
		uMail,storeMail,review,rating,"reviewStoreService");
	}
	
	public void getFilteredStoresService(String category,String district,String maxStoreID ) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/addOfferService",
		category,district,maxStoreID,"getFilteredStoresService");
	}
	
	public void getStoreOfferService(String storeMail ) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/addOfferService",
		storeMail,"getStoreOfferService");
	}
	
	public void getStoreReviewsService(String storeMail ) 
	{
	new Connection().execute( "http://makanyapp2.appspot.com/rest/addOfferService",
		storeMail,"getStoreReviewsService");
	}


	
	
	static class Connection extends AsyncTask<String, String, String> 
	{
		String serviceType;


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			
			serviceType = params[params.length - 1];
			String urlParameters="";
			if (serviceType.equals("reviewStoreService"))
				urlParameters = "uMail"+ params[1] +"&storMail="+ params[2] +"&review="
						+ params[3] +"&rating=" + params[4]; 
			
			else if (serviceType.equals("getFilteredStoresService"))
				urlParameters = "category"+ params[1] +"&district="+ params[2] +"&maxStoreID="
						+ params[3];
			
			else if (serviceType.equals("getStoreOfferService") || serviceType.equals("getStoreReviewsService"))
				urlParameters = "storeMail"+ params[1];
				 
			
			
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

		
		
		// not done yet
		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			
			try 
			{
				
				if (serviceType.equals("reviewStoreService")) 
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
					
					if(object.get("Status").equals("Failed"))
					{
						System.out.println("Failed to add Review" );
						Toast.makeText(Application.getAppContext(), "Failed to add Review",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					Toast.makeText(Application.getAppContext(), "Review Added Successfully",
						Toast.LENGTH_LONG).show();
			
				}
				
			
				if (serviceType.equals("getFilteredStoresService")) 
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
							
					
				}
				
				if (serviceType.equals("getStoreOfferService")) 
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
							
					//return;
				}
				
				if (serviceType.equals("getStoreReviewsService")) 
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
						
				
				}
			}
				catch(Exception e)
				{}



				
				
				

			}
					
		
			

			
		}

		
		
		
		

		}
		
		
		
	

	

