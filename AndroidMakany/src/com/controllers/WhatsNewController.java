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

import com.androidActivities.WhatsNew;
import com.simpleModels.Element;
import com.simpleModels.FoursquarePlace;
import com.simpleModels.SimpleEvent;
import com.simpleModels.SimpleItem;
import com.simpleModels.SimpleOffer;
import com.simpleModels.SimplePost;
import com.simpleModels.SimpleStore;



public class WhatsNewController 
{


	public void getStaticRecommendation(String usermail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/staticRecommendService",
				usermail ,"getStaticRecommendation");
		
		return;
	}
	
	public void getDynamicRecommendation(String usermail, double latitude ,double logitude) 
	{
		Connection connectionClass = new Connection();
		
		String lat = latitude + "";
		String longit = logitude + "";
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/DynamicRecommendService",
				usermail,lat,longit, "getDynamicRecommendation");
		
		return;
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
			
			
			if (serviceType.equals("getStaticRecommendation"))
				urlParameters = "email="+ params[1] ;
			
			if (serviceType.equals("getDynamicRecommendation"))
				urlParameters = "email="+ params[1] + "&longitude=" + params[3]
						+ "&latitude=" + params[2];
			
			
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
			
			System.out.println(result);

			super.onPostExecute(result);
			
			try 
			{

				if (serviceType.equals("getStaticRecommendation") || serviceType.equals("getDynamicRecommendation") ) 
				{
					
					ArrayList<Element> elements = new ArrayList<Element>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								if (object.getString("type").equals("LoanItem") || object.getString("type").equals("RequestItem"))
								{
									SimpleItem simpleItem = new SimpleItem(object.getString("id"),object.getString("username"), object.getString("name"), 
									object.getString("description"), object.getString("userEmail"), object.getString("district"),
									object.getString("photo"),object.getString("state"), object.getString("categories"),
									object.getString("date"),object.getString("type"));
										
									elements.add(simpleItem);
								}
								else if (object.getString("type").equals("Event"))
								{
									SimpleEvent simpleEvent = new SimpleEvent(object.getString("id"),object.getString("username"),object.getString("name"), 
											object.getString("category"), object.getString("description"), 
											Double.parseDouble(object.getString("latitude")), Double.parseDouble(object.getString("longitude")), 
											object.getString("ownerMail"), object.getString("district"), object.getString("goingMails"), object.getString("postIDs")
											,object.getString("date"),object.getString("from"),object.getString("to") );
									elements.add(simpleEvent);
								}
								else if (object.getString("type").equals("Store"))
								{
									SimpleStore simpleStore = new SimpleStore(object.getString("ID"), object.getString("name"),
											object.getString("email"),object.getString("password"),object.getString("district"),
											object.getString("category"), object.getString("description"),
											object.getString("date"), object.getString("latitude"),
											object.getString("longitude"));
									
									elements.add(simpleStore);
								}
								else if (object.getString("type").equals("Offer"))
								{
									SimpleOffer simpleOffer= new SimpleOffer(object.getString("ID"), object.getString("description"),
											object.getString("storeMail"),object.getString("photo"),object.getString("date"),
													object.getString("numThumbsup"), object.getString("numThumbsDown"),
													object.getString("numViewers"), object.getString("thumbsupMails"),
													object.getString("thumbsdownMails"), object.getString("viewersMails"));
									elements.add(simpleOffer);
								}
								else if (object.getString("type").equals("Post"))
								{
									SimplePost post = new SimplePost(
											object.getString("ID"), object.getString("postType"), 
											object.getString("content"), object.getString("photo"), 
											object.getString("username"), object.getString("userEmail"), 
											object.getString("district"), object.getString("onEventID"), 
											object.getString("score"), object.getString("numApprovals"), 
											object.getString("numDisApprovals"), object.getString("numReports"), 
											object.getString("approvalMails"),object.getString("disapprovalMails"), 
											object.getString("reportMails"), object.getString("categories"));
									elements.add(post);
								}
								
								else if (object.getString("type").equals("Foursquare"))
								{
									FoursquarePlace fs = new FoursquarePlace(object.getString("name"), object.getString("address"), 
											object.getString("rating"), object.getString("phone"),object.getString("distance"), 
											object.getString("latitude"), object.getString("longitude"), object.getString("category"));
									elements.add(fs);
								}
								
								
							}
					
					}
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					
					Intent whatsNew = new Intent(Application.getAppContext(),WhatsNew.class);
	  				Application.setElements(elements);
	  				
	  				whatsNew.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(whatsNew);
					
				}
				
				


			}
			catch(Exception e)
			{}

		}

	}



}
