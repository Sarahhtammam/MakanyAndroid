package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.androidActivities.LoginActivity;
import com.simpleModels.SimpleUser;



public class UserController 
{
	static ProgressDialog myDialog;
	static String emaill;
	static String userType;
	
	public void login(String email, String password,ProgressDialog myDialog) 
	{
		this.myDialog = myDialog;
		Connection connectionClass = new Connection();
		emaill =email;
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/LoginService", email, password, "LoginService");
	}
	
	public void getUser(String email) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getUserService", email, "getUserService");
	}
	
	public void Signup(String name, String email, String password, String birthDate, 
						String district, String description, String gender, 
						String twitter, String foursquare, String interests) 
	{
		String uType="normal";
		userType=uType;
		String category="NONE";
		
		new Connection().execute( "http://makanyapp2.appspot.com/rest/signUpService", uType,
		name, email, password, birthDate, district, category, description, gender, twitter, 
		foursquare, interests, "signUpService");
	}
	
	public void store_Signup(String name, String email, String password, String birthDate, 
			String district, String description, String gender, 
			String twitter, String foursquare, String category) 
	{
		String uType="store";
		userType=uType;
		new Connection().execute( "http://makanyapp2.appspot.com/rest/signUpService", uType,
		name, email, password, birthDate, district, category, description, gender, twitter, 
		foursquare, "", "signUpService");
	}
	

	public void EditProfile(String email, String name, String password, String birthDate, 
			String district, String gender, String twitter, String foursquare, String interests) 
	{
		new Connection().execute( "http://makanyapp2.appspot.com/rest/editProfileService", 
				"normal", name, email, password, "", "", birthDate, district, gender, twitter, 
				foursquare, interests, "editProfileService");
	}

	
	
	
	
	
	static class Connection extends AsyncTask<String, String, String> 
	{
		
		String serviceType;

		@Override
		protected String doInBackground(String... params)
		{
			URL url;
			serviceType = params[params.length - 1];
			String urlParameters="";
			
			if (serviceType.equals("LoginService"))
				urlParameters = "email=" + params[1] + "&password=" + params[2];
			
			
			else if (serviceType.equals("getUserService"))
				urlParameters = "email=" + params[1];
			
			
			else if(serviceType.equals("signUpService"))
				urlParameters ="uType=" + params[1] + "&name=" + params[2] + "&email=" + params[3] + 
							   "&password=" + params[4] + "&birthDate=" + params[5] + 
							   "&district=" + params[6] + "&category=" + params[7] +"&description=" 
							   + params[8] + "&gender=" + params[9] + "&twitter=" + params[10] 
							   + "&foursquare=" + params[11] + "&interests=" + params[12];

			else if(serviceType.equals("editProfileService"))
				urlParameters ="uType=" + params[1] + "&name=" + params[2] + "&email=" + params[3] + 
				   "&password=" + params[4] + "&category=" + params[5] + 
				   "&description=" + params[6] + "&birthDate=" + params[7] +"&district=" 
				   + params[8] + "&gender=" + params[9] + "&twitter=" + params[10] 
				   + "&foursquare=" + params[11] + "&interests=" + params[12];
		
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
				if (serviceType.equals("LoginService")) 
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("error");
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_SHORT).show();
						myDialog.dismiss();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error: Retype your email",
						Toast.LENGTH_SHORT).show();
						myDialog.dismiss();
						return;
					}
					
					if(object.getString("Status").equals("wrongPass"))
					{
						Toast.makeText(Application.getAppContext(), "Wrong Password",
						Toast.LENGTH_SHORT).show();
						myDialog.dismiss();
						return;
					}
					
					//Logged in successfully 
					myDialog.dismiss();
					Application.setUserEmail(emaill);
					
					WhatsNewController whatsNewController = new WhatsNewController();
					whatsNewController.getStaticRecommendation(emaill);
					
					/*Intent homeIntent = new Intent(Application.getAppContext(),HomeActivity.class);
					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(homeIntent);*/
				
				}
			
				
				if (serviceType.equals("getUserService")) 
				{
					System.out.println("result " + result);
					JSONObject object = new JSONObject(result);
					
					if(object== null || !object.has("Status"))
					{
						System.out.println("eroor" );
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_SHORT).show();
						return;
					}
					
					if(object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "error",
						Toast.LENGTH_SHORT).show();
						return;
					}
					
					JSONObject currentUser;
					SimpleUser simpleUser = null;
					
					try {
							currentUser = object;
							
							String interests = currentUser.getString("interests");
							String [] interestsArray = interests.split(";");
							
							Vector<String> interestsVector = new Vector<String>();
							
							for(int i=0; i<interestsArray.length; i++)
							{
								interestsVector.add(interestsArray[i]);
							}
							
							{
								simpleUser = new SimpleUser(currentUser.getString("ID"),
									currentUser.getString("name"),currentUser.getString("email"),
									currentUser.getString("password"),currentUser.getString("birthDate"),
									currentUser.getString("district"),currentUser.getString("gender"),
									currentUser.getString("twitter"),currentUser.getString("foursquare"),
									Integer.parseInt(currentUser.getString("trust")), interestsVector);
							}	
					} 
					
					catch (JSONException e) 
					{
						Toast.makeText(Application.getAppContext(), "error",
								Toast.LENGTH_SHORT).show();
								
					}

					Application.setCurrentUser(simpleUser);
					Application.setCurrentDistrict(simpleUser.getDistrict());
					
					
					
				}
			
					
				else if(serviceType.equals("signUpService"))
				{
					System.out.println("result " + result);
					
					JSONObject object = new JSONObject(result);
					
					if(object== null || !object.has("Status") 
					 ||object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_SHORT).show();
						return;
					}
					
					//Signed Up successfully 
					Toast.makeText(Application.getAppContext(), "Signed Up Successfully",
					Toast.LENGTH_SHORT).show();
					
					
					
					Intent mainIntent = new Intent(Application.getAppContext(),LoginActivity.class);
					mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(mainIntent);
				}
			
				else if(serviceType.equals("editProfileService"))
				{
					
					System.out.println("result" + result);
					
					JSONObject object=new JSONObject (result);
					
					if(object== null || !object.has("Status") 
					  ||object.getString("Status").equals("Failed"))
					{
						Toast.makeText(Application.getAppContext(), "Error occured",
						Toast.LENGTH_SHORT).show();
						return;
					}
							
					//edit profile succeed
					Toast.makeText(Application.getAppContext(), "Success",
					Toast.LENGTH_SHORT).show();
					
					WhatsNewController whatsNewController = new WhatsNewController();
					whatsNewController.getStaticRecommendation(emaill);
				
					
				}


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

	