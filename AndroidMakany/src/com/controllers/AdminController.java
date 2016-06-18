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

import android.os.AsyncTask;



public class AdminController 
{
	
	
	public void getDistricts() 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/ShowAllDistrictsService",
		"ShowAllDistrictsService");

		//return;
	}
	
	public void getCategories() 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/ShowAllCategoryService",
		"ShowAllCategoryService");
		
		//return;
	}	
	
		
	static class Connection extends AsyncTask<String, String, String> 
	{
		String serviceType;
		
		
		@Override
		protected String doInBackground(String... params)
		{
			URL url;
			
			/////////////////////////////////
			serviceType = params[params.length - 1];
			
			String urlParameters="";
						

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
				
				if (serviceType.equals("ShowAllDistrictsService")) 
				{
					System.out.println("result " + result);
					//delegate.processFinish2(result);
					
					ArrayList<String> districtsList = new ArrayList<String>();
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								String x = object.getString("DistrictName");
								districtsList.add(x);
							}
							
							Application.setDistricts(districtsList);
							
							/*if(Application.loggedIn==false)
							{
								Intent signUpIntent = new Intent(Application.getAppContext(),SignUpActivity.class);
								signUpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								Application.getAppContext().startActivity(signUpIntent);
							}*/
					} 
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
				}
				
				if (serviceType.equals("ShowAllCategoryService")) 
				{
					System.out.println("Categories result " + result);
					
					ArrayList<String> categoriesList = new ArrayList<String>();
					JSONArray requestArray;
					
					try 
					{
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								String x = object.getString("categoryValue");
								categoriesList.add(x);
							}
					
							Application.setCategories(categoriesList);

				/*		 	Intent createPostIntent = new Intent(Application.getAppContext(),CreatePostActivity.class);
						 	createPostIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							Application.getAppContext().startActivity(createPostIntent);
				*/	   
							
					}	 
				
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
				}
				
				//Do the same for other services
				//else if(serviceType.equals(""))
				//{}

			}
			catch(Exception e)
			{}

		}
		

	}

}

