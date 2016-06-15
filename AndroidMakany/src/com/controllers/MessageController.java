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

import SimpleModels.SimpleMessage;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import com.androidActivities.MyMessages;
import com.androidActivities.SingleMessage;




public class MessageController 
{


	public void getChatMessages(String usermail , String otherMail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getChatMessagesService",
				usermail,otherMail ,"getChatMessagesService");
		
		return;
	}
	
	public void getMsgNames(String useremail) 
	{
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/getMsgNamesService",
				useremail ,"getMsgNamesService");
		
		return;
	}
	
	public void sendMessage(String sender , String reciver,String content) 
	{
		
		Toast.makeText(Application.getAppContext(), "msg is " + content,
				Toast.LENGTH_LONG).show();
		
		Connection connectionClass = new Connection();
		
		connectionClass.execute( "http://makanyapp2.appspot.com/rest/sendMessageService",
				sender,reciver,content ,"sendMessageService");
		
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
			
			
			if (serviceType.equals("getChatMessagesService"))
				urlParameters = "usermail="+ params[1] + "&otherMail=" + params[2] ;
			
			if (serviceType.equals("getMsgNamesService"))
				urlParameters = "usermail="+ params[1] ;
			
			if (serviceType.equals("sendMessageService"))
				urlParameters = "sender="+ params[1] + "&reciver=" + params[2] 
						+ "&content=" + params[3] ;
			
			
			
			
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

				if (serviceType.equals("getChatMessagesService")) 
				{
					
					ArrayList<SimpleMessage> messages = new ArrayList<SimpleMessage>();
					
					JSONArray requestArray;
					
					try {
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleMessage simpleMsg  = new SimpleMessage(object.getString("senderMail"),object.getString("reciverMail"), 
								object.getString("content"), object.getString("senderName"), object.getString("reciverName") );
										
								messages.add(simpleMsg);

							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					/*SimpleMessage simpleMsg = new SimpleMessage("heba.khazbak@gmail.com","sarah@gmail.com","Hiii sarah", "Heba", "Sarah");
					messages.add(simpleMsg);
					
					simpleMsg = new SimpleMessage("sarah@gmail.com","heba.khazbak@gmail.com","Hiii ya heba", "Sarah", "Heba");
					messages.add(simpleMsg);
					
					simpleMsg = new SimpleMessage("sarah@gmail.com","heba.khazbak@gmail.com","hello ya gama3a", "Sarah", "Heba");
					messages.add(simpleMsg);*/
					
					Application.setMessgaes(messages);
					
					Intent msgIntent = new Intent(Application.getAppContext(),SingleMessage.class);

					msgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(msgIntent);
					
				}
				else if (serviceType.equals("getMsgNamesService")) 
				{
					ArrayList<SimpleMessage> names = new ArrayList<SimpleMessage>();

					JSONArray requestArray;
					
					try {
							
							requestArray = new JSONArray(result);
							for(int i=0;i<requestArray.length();i++)
							{
								JSONObject object=new JSONObject();
								object = (JSONObject)requestArray.get(i);
								
								SimpleMessage simpleMsg  = new SimpleMessage(object.getString("username"),object.getString("usermail"));
										
								names.add(simpleMsg);

							}
					
					}
					
					
					catch (JSONException e) 
					{
						e.printStackTrace();
					}
					
					/*SimpleMessage m = new SimpleMessage("Sarah", "sarah@gmail.com");
					names.add(m);
					m = new SimpleMessage("Ziad", "ziad@gmail.com");
					names.add(m);*/
					System.out.println("okk");
					Application.setMsgNames(names);
					Intent msgIntent = new Intent(Application.getAppContext(),MyMessages.class);
					msgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(msgIntent);
					
				}
				else if (serviceType.equals("sendMessageService")) 
				{
					
					Toast.makeText(Application.getAppContext(), "Message sent",
							Toast.LENGTH_LONG).show();
					
					/*Intent msgIntent = new Intent(Application.getAppContext(),MyMessages.class);
					msgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Application.getAppContext().startActivity(msgIntent);*/
					
				}


			}
			catch(Exception e)
			{}

		}

	}



}
