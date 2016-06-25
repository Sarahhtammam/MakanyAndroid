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
import android.widget.Toast;

import com.simpleModels.SimpleOffer;
import com.simpleModels.SimpleReview;
import com.simpleModels.SimpleStore;


public class StoreController {
	

	public void reviewStoreService(String userMail,String storeMail,String review, String rating) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/reviewStoreService",
		userMail,storeMail,review,rating,"reviewStoreService");
	}
	
	public void getFilteredStoresService(String category, String district, String maxStoreID, AsyncResponse d) 
	{
		Connection connectionClass = new Connection();
		connectionClass.delegate = d;
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getFilteredStoresService",
		category, district, maxStoreID,"getFilteredStoresService");
	}
	
	public void getFilteredOffersService(String storeIDs, String offerID, String district, String category, AsyncResponse d) 
	{
		Connection connectionClass = new Connection();
		connectionClass.delegate = d;
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getFilteredOffersService",
				storeIDs, offerID, district, category ,"getFilteredOffersService");
	}
	

	public void getStoreOffersService(String storeMail, AsyncResponse d) 
	{
		Connection connectionClass = new Connection();
		connectionClass.delegate = d;
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getStoreOffersService",
		storeMail,"getStoreOffersService");
	}
	
	public void getStoreReviewsService(String storeMail) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/getStoreReviewsService",
		storeMail,"getStoreReviewsService");
	}
	
	public void getStoreService (String storeMail)
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/getStoreService",
				storeMail,"getStoreService");
	}


	
	
	
	static class Connection extends AsyncTask<String, String, String> 
	{
		String serviceType;
		AsyncResponse delegate;


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			
			serviceType = params[params.length - 1];
			String urlParameters="";
			if (serviceType.equals("reviewStoreService"))
				urlParameters = "userMail="+ params[1] +"&storeMail="+ params[2] +"&review="
						+ params[3] +"&rating=" + params[4]; 
			
			else if (serviceType.equals("getFilteredStoresService"))
				urlParameters = "category="+ params[1] +"&district="+ params[2] +"&maxStoreID="
						+ params[3];
			
			else if (serviceType.equals("getFilteredOffersService"))
				urlParameters = "storeIDs="+ params[1] +"&offerID="+ params[2] +"&district="
						+ params[3]+"&category=" + params[4];
			
			
			else if (serviceType.equals("getStoreOfferService") || serviceType.equals("getStoreReviewsService"))
				urlParameters = "storeMail="+ params[1];
			
			else if (serviceType.equals("getStoreService"))
				urlParameters = "storeMail="+ params[1];
			
			else if (serviceType.equals("getStoreOffersService")) 
				urlParameters = "storeMail="+ params[1];
				 
			
			
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
					
					ArrayList<SimpleStore> stores = new ArrayList<SimpleStore>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleStore simpleStore = new SimpleStore (object.getString("ID"),
										object.getString("name"),object.getString("email"),object.getString("password"),
										object.getString("district"), object.getString("category"),object.getString("description"),
										object.getString("date"), object.getString("latitude"), object.getString("longitude"));
								
								stores.add(simpleStore);
							}
					

							Application.setStores(stores);
							delegate.processFinish("Filtered Stores");

					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
				}
				
				
				if (serviceType.equals("getStoreOffersService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimpleOffer> offers = new ArrayList<SimpleOffer>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleOffer simpleOffer= new SimpleOffer(object.getString("ID"), object.getString("description"),
										"",object.getString("photo"),object.getString("date"),
												object.getString("numThumbsup"), object.getString("numThumbsDown"),
												object.getString("numViewers"), object.getString("thumbsupMails"),
												object.getString("thumbsdownMails"), object.getString("viewersMails"));
								offers.add(simpleOffer);
							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					
					Application.setOffers(offers);
					delegate.processFinish("");

				}
				
				if (serviceType.equals("getFilteredOffersService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimpleOffer> offers = new ArrayList<SimpleOffer>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleOffer simpleOffer= new SimpleOffer(object.getString("ID"), object.getString("description"),
										"",object.getString("photo"),object.getString("date"),
												object.getString("numThumbsup"), object.getString("numThumbsDown"),
												object.getString("numViewers"), object.getString("thumbsupMails"),
												object.getString("thumbsdownMails"), object.getString("viewersMails"));
								offers.add(simpleOffer);
							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					
					Application.setOffers(offers);
					delegate.processFinish("");

				}

				
				if (serviceType.equals("getStoreReviewsService")) 
				{
					System.out.println("result " + result);
					
					ArrayList<SimpleReview> reviews = new ArrayList<SimpleReview>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleReview simpleReview= new SimpleReview(object.getString("ID"),
										object.getString("reviewerMail"),object.getString(""), object.getString("review"),
										object.getString("date"), object.getString("rating"));
										
										
								reviews.add(simpleReview);
							}
					
							Application.getCurrentStore().setReviews(reviews);
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}

				}

				if (serviceType.equals("getStoreService"))
				{
					JSONObject object = new JSONObject(result);
					try
					{
						
						SimpleStore simpleStore = new SimpleStore(object.getString("ID"), object.getString("name"),
								object.getString("email"),object.getString("password"),object.getString("district"),
								object.getString("category"), object.getString("description"),
								object.getString("date"), object.getString("latitude"),
								object.getString("longitude"));
						
						
						System.out.println("Store name " + simpleStore.getStoreName());
						
						Application.setCurrentStore(simpleStore);
					
					}
			
			
					catch (JSONException e) 
					{
						System.out.println("error" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_LONG).show();
				
					}
				}
					
			
			}

			catch(Exception e) {}
		}
	}
}
		
		
		
	

	

