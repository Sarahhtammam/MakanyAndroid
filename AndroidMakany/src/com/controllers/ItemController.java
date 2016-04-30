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

import SimpleModels.SimpleItem;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.androidActivities.ItemsMenuActivity;


public class ItemController 
{
	
	
	public void createLoanItem(String name, String description, String userEmail, String district, String photo, String state ,String strCategories) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/loanItemService", name,description,
				userEmail,district,photo, state, strCategories, "createLoanItemService");
		
		return;
	}
	
	public void editItem(String itemID,String name, String description, String userEmail, String district, String photo, String state ,String strCategories)
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/editItemService",itemID, name,description,
				userEmail,district,photo, state, strCategories, "editItemService");
		
		return;
	}
	
	public void deleteItem(String itemID , String userEmail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/deleteItemService", itemID, userEmail, "deleteItemService");
		
		return;
	}
	
	public void viewItem(String itemID) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/viewItemService", itemID,"viewItemService");
		
		return;
	}

	public void getFilteredLoanItems(String district, String state) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getFilteredLoanItemsService",
				district, state, "getFilteredLoanItemsService");
		
		return;
	}
	
	
		
	static class Connection extends AsyncTask<String, String, String> 
	{

		String serviceType;
		
		/*private static ArrayList<String> interestsList = new ArrayList<String>();
		
		public ArrayList<String> get_InterestList() 
		{
			return interestsList;
		}
		*/
		
		@Override
		protected String doInBackground(String... params)
		{
			URL url;
			
			/////////////////////////////////
			serviceType = params[params.length - 1];
			
			String urlParameters="";
			if (serviceType.equals("createLoanItemService"))
				urlParameters = "name="+ params[1] +"&description="+ params[2] +"&userEmail="
						+ params[3] +"&district=" + params[4] +"&photo=" 
						+ params[5] +"&state="+ params[6] + "&categories=" + params[7];
						
			if (serviceType.equals("editItemService"))
				urlParameters = "itemID" + params[1] + "&name="+ params[2] +"&description="+ params[3]
						+"&userEmail="+ params[4] +"&district=" + params[5] +"&photo=" 
						+ params[6] +"&state="+ params[7] + "&categories=" + params[8];			
						
			if (serviceType.equals("deleteItemService"))
			urlParameters = "itemID="+ params[1] +"&userEmail="+ params[2];
			
			if (serviceType.equals("viewItemService"))
				urlParameters = "itemID="+ params[1] ;
			
			if (serviceType.equals("getFilteredLoanItemsService"))
				urlParameters = "district="+ params[1] +"&state="+ params[2];
			
			
			
			
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
				if (serviceType.equals("createLoanItemService")) 
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
					else if (object.get("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "Failed to create Item",
								Toast.LENGTH_LONG).show();
					}
					
					
					
					Toast.makeText(Application.getAppContext(), "Item Created", Toast.LENGTH_LONG).show();
					
					Intent itemIntent = new Intent(Application.getAppContext(),ItemsMenuActivity.class);
					itemIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(itemIntent);

					
					//return;
			
				}
				
				if (serviceType.equals("editItemService")) 
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
					else if (object.get("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "Failed to edit Item!",
								Toast.LENGTH_LONG).show();
					}
					
					
					
					Toast.makeText(Application.getAppContext(), "Item has been edited successfully", Toast.LENGTH_LONG).show();
					
					/*Intent eventsIntent = new Intent(Application.getAppContext(),EventsMenuActivity.class);
					eventsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(eventsIntent);*/
					
					
					
					//return;
			
				}

				if (serviceType.equals("deleteItemService")) 
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
					else if (object.get("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "Failed to delete Item!",
								Toast.LENGTH_LONG).show();
					}
					else if (object.get("Status").equals("notYourItem"))
					{
						Toast.makeText(Application.getAppContext(), "It's not your Item!",
								Toast.LENGTH_LONG).show();
					}
					
					
					Toast.makeText(Application.getAppContext(), "Item has been deleted successfully", Toast.LENGTH_LONG).show();
					
					/*Intent eventsIntent = new Intent(Application.getAppContext(),EventsMenuActivity.class);
					eventsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(eventsIntent);*/
					
					
					//return;
			
				}
				if (serviceType.equals("viewItemService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("id"))
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_LONG).show();
						return;
					}
					
					SimpleItem simpleItem = new SimpleItem(object.getString("id"),object.getString("name"), 
							  object.getString("description"), object.getString("userEmail"), object.getString("district"),
							  object.getString("photo"),object.getString("state"), object.getString("categories"));
						
					/*Intent showEvents = new Intent(Application.getAppContext(),ShowEventsActivity.class);
	  				Application.setItems(items);
	  				
	  				showEvents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(showEvents);*/
					
				}


				if (serviceType.equals("getFilteredLoanItemsService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimpleItem> items = new ArrayList<SimpleItem>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleItem simpleItem = new SimpleItem(object.getString("id"),object.getString("name"), 
									  object.getString("description"), object.getString("userEmail"), object.getString("district"),
									  object.getString("photo"),object.getString("state"), object.getString("categories"));
								
								items.add(simpleItem);
								
							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					/*Intent showEvents = new Intent(Application.getAppContext(),ShowEventsActivity.class);
	  				Application.setItems(items);
	  				
	  				showEvents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(showEvents);*/

					
					
				}


			}
			catch(Exception e)
			{}

		}

	}



}
