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
import com.simpleModels.SimpleEvent;
import com.simpleModels.SimpleItem;



public class WhatsNewController 
{


	public void getStaticRecommendation(String usermail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getStaticRecommendationService",
				usermail ,"getStaticRecommendation");
		
		return;
	}
	
	public void getDynamicRecommendation(String usermail, double latitude ,double logitude) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getDynamicRecommendationService",
				usermail, "getDynamicRecommendation");
		
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
			
			
			if (serviceType.equals("getStaticRecommendation")||serviceType.equals("getDynamicRecommendation"))
				urlParameters = "usermail="+ params[1] ;
			
			
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

				if (serviceType.equals("getStaticRecommendation")) 
				{
					
					ArrayList<Element> elements = new ArrayList<Element>();
					
					JSONArray requestArray;
					
					try {
							/*requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								if (object.getString("type").equals("1"))
								{
									SimpleItem simpleItem = new SimpleItem(object.getString("id"),object.getString("name"), 
									object.getString("description"), object.getString("userEmail"), object.getString("district"),
									object.getString("photo"),object.getString("state"), object.getString("categories"));
										
									elements.add(simpleItem);
								}
								else if (object.getString("type").equals("2"))
								{
									SimpleEvent simpleEvent = new SimpleEvent(object.getString("id"),object.getString("name"), 
											object.getString("category"), object.getString("description"), 
											Double.parseDouble(object.getString("latitude")), Double.parseDouble(object.getString("longitude")), 
											object.getString("ownerMail"), object.getString("district"), object.getString("goingMails"), object.getString("postIDs"));
									elements.add(simpleEvent);
								}
								else if (object.getString("type").equals("3"))
								{
									
								}
								else if (object.getString("type").equals("4"))
								{
									
								}
								
								
							}*/
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					SimpleItem simpleItem = new SimpleItem("1","Pen","nice pen","heba.khazbak@gmail.com","Zamalek","","Open","");
					elements.add(simpleItem);
					SimpleEvent event = new SimpleEvent("3", "Run", "sports", "run in zamalek 250 m", 30.0,32.0, "heba.khazbak@gmail.com","Zamalek", "", "");
					elements.add(event);
					simpleItem = new SimpleItem("2","Cat","animals","heba.khazbak@gmail.com","Zamalek","","Open","");
					elements.add(simpleItem);
					
					event = new SimpleEvent("4", "eating", "food", "eat in zamalek fish", 30.0,32.0, "heba.khazbak@gmail.com","Zamalek", "", "");
					elements.add(event);
					event = new SimpleEvent("5", "eat", "food", "eat in zamalek chicken", 30.0,32.0, "heba.khazbak@gmail.com","Zamalek", "", "");
					elements.add(event);
					simpleItem = new SimpleItem("6","Dog","animals","heba.khazbak@gmail.com","Zamalek","","Open","");
					elements.add(simpleItem);
					
					Intent whatsNew = new Intent(Application.getAppContext(),WhatsNew.class);
	  				Application.setElements(elements);
	  				
	  				whatsNew.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(whatsNew);
					
				}
				
				else if (serviceType.equals("getDynamicRecommendation")) 
				{
					
					ArrayList<Element> elements = new ArrayList<Element>();
					
					JSONArray requestArray;
					
					try {
						
				
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					SimpleItem simpleItem = new SimpleItem("10","car","nice car","heba.khazbak@gmail.com","Zamalek","","Open","");
					elements.add(simpleItem);
					SimpleEvent event = new SimpleEvent("30", "caaar", "sports", "run in zamalek 250 m", 30.0,32.0, "heba.khazbak@gmail.com","Zamalek", "", "");
					elements.add(event);
					
					
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
