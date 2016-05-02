package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import SimpleModels.SimpleEvent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.UserManager;
import android.widget.Toast;

import com.androidActivities.EventsMenuActivity;
import com.androidActivities.ShowEventsActivity;


public class EventController 
{
	
	static String userEmail;
	
	public void createEvent(String name, String category, String description, String latitude, String longitude, String ownerMail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/createEventService", name, category, description, 
					latitude, longitude, ownerMail, "createEventService");
		
		return;
	}
	
	public void editEvent(String eventID, String category, String description, String latitude, String longitude) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/editEventService", eventID, category, description, 
					latitude, longitude, "editEventService");
		
		return;
	}
	
	public void joinEvent(String eventID , String userMail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/joinEventService", eventID, userMail, "joinEventService");
		
		return;
	}
	
	public void postOnEvent(String eventID, String postType, String content, String photo, 
			String district, String onEventID, String userEmail, String categories) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/postOnEventService", eventID, postType, content, 
				  photo, district, onEventID, userEmail, categories, "postOnEventService");
		
		return;
	}

	public void reviewEvent(String userMail, String eventID, String review, String rating) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/reviewEventService", userMail, eventID,
				review, rating, "reviewEventService");
		
		return;
	}
	
	public void getEventGoing(String eventID) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getEventGoingService", eventID, "getEventGoingService");
		
		return;
	}
	
	public void getEventByID(String eventID) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getEventByIDService", eventID, "getEventByIDService");
		
		return;
	}
	
	public void getGoingEvents(String userMail, String maxEventID) 
	{
		Connection connectionClass = new Connection();
		
		userEmail = userMail;
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getGoingEventsService", userMail, "","getGoingEventsService");
		
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
			if (serviceType.equals("createEventService"))
				urlParameters = "name="+ params[1] +"&category="+ params[2] +"&description="
						+ params[3] +"&latitude=" + params[4] +"&longitude=" 
						+ params[5] +"&ownerMail="+ params[6];
						
			if (serviceType.equals("editEventService"))
				urlParameters = "eventID="+ params[1] +"&category="+ params[2] +"&description="
						+ params[3] +"&latitude=" + params[4] +"&longitude=" 
						+ params[5];			
						
			if (serviceType.equals("joinEventService"))
			urlParameters = "eventID="+ params[1] +"&userMail="+ params[2];
			
			if (serviceType.equals("postOnEventService"))
				urlParameters = "eventID="+ params[1] +"&postType="+ params[2] +"&content="
						+ params[3] +"&photo=" + params[4] +"&district=" 
						+ params[5] +"&onEventID="+ params[6] +"&userEmail="+ params[7] +"&categories="+ params[8];
			
			if (serviceType.equals("reviewEventService"))
				urlParameters = "userMail="+ params[1] +"&eventID="+ params[2] +"&review="
						+ params[3] +"&rating=" + params[4];
			
			if (serviceType.equals("getEventGoingService") || serviceType.equals("getEventByIDService"))
				urlParameters = "eventID="+ params[1];
			
			if (serviceType.equals("getGoingEventsService"))
				urlParameters = "userEmail="+ params[1] +"&maxEventID="+ params[2];
			
			
			
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
				if (serviceType.equals("createEventService")) 
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
					
					
					
					Toast.makeText(Application.getAppContext(), "Event Created", Toast.LENGTH_LONG).show();
					
					Intent eventsIntent = new Intent(Application.getAppContext(),EventsMenuActivity.class);
					eventsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(eventsIntent);

					
					//return;
			
				}
				
				if (serviceType.equals("editEventService")) 
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

				if (serviceType.equals("joinEventService")) 
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
					
					Toast.makeText(Application.getAppContext(), "OK, Joined",Toast.LENGTH_LONG).show();
							
					//return;
			
				}
				if (serviceType.equals("postOnEventService")) 
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

				if (serviceType.equals("reviewEventService")) 
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
				
				if (serviceType.equals("getEventGoingService")) 
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

				
				if (serviceType.equals("getGoingEventsService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<String> eventsNames = new ArrayList<String>();
					ArrayList<SimpleEvent> events = new ArrayList<SimpleEvent>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleEvent simpleEvent = new SimpleEvent(object.getString("id"),object.getString("name"), 
										object.getString("category"), object.getString("description"), 
										Double.parseDouble(object.getString("latitude")), Double.parseDouble(object.getString("longitude")), 
										object.getString("ownerMail"), object.getString("goingMails"), object.getString("postIDs"));
								String x = object.getString("name");
								eventsNames.add(x);
								events.add(simpleEvent);
								//temp+= x+ ",";
							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					Intent showEvents = new Intent(Application.getAppContext(),ShowEventsActivity.class);
	  				showEvents.putExtra("eventsNames", eventsNames);
	  				Application.setEvents(events);
	  				
	  				showEvents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(showEvents);

					
					
				}
				
				if (serviceType.equals("getEventByIDService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					try
					{
						
						SimpleEvent simpleEvent = new SimpleEvent(object.getString("id"),object.getString("name"), 
								object.getString("category"), object.getString("description"), 
								Double.parseDouble(object.getString("latitude")), Double.parseDouble(object.getString("longitude")), 
								object.getString("ownerMail"), object.getString("goingMails"), object.getString("postIDs"));
						
						Application.setCurrentEvent(simpleEvent);
					
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
			catch(Exception e)
			{}

		}

	}



}
