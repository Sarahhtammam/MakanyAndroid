package com.controllers;

import java.util.ArrayList;

import SimpleModels.SimpleEvent;
import SimpleModels.SimpleItem;
import SimpleModels.SimplePost;
import SimpleModels.SimpleUser;
import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;
	private static UserController userController;
	public static boolean loggedIn;
	
	private static SimpleUser currentUser;
	private static String userEmail; // no need for this
	private static SimpleEvent currentEvent;
	private static SimpleItem currentItem;
	
	private static ArrayList<SimpleEvent> events;
	private static ArrayList<SimplePost> posts;
	private static ArrayList<SimpleItem> items;
	
	
    public void onCreate(){
        super.onCreate();
        Application.context = getApplicationContext();
        Application.userController = UserController.getInstance();
        loggedIn= false;
    }

    public static Context getAppContext() {
        return Application.context;
    }
    
    public static UserController getUserController(){
    	return Application.userController;
    }
    
 
	public static SimpleUser getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(SimpleUser currentUser) {
		Application.currentUser = currentUser;
	}

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userE) 
	{
		userEmail = userE;
	}

	public static ArrayList<SimpleEvent> getEvents() {
		return events;
	}

	public static void setEvents(ArrayList<SimpleEvent> events) {
		Application.events = events;
	}

	public static ArrayList<SimplePost> getPosts() {
		return posts;
	}

	public static void setPosts(ArrayList<SimplePost> posts) {
		Application.posts = posts;
	}

	public static SimpleEvent getCurrentEvent() {
		return currentEvent;
	}

	public static void setCurrentEvent(SimpleEvent currentEvent) {
		Application.currentEvent = currentEvent;
	}

	public static ArrayList<SimpleItem> getItems() {
		return items;
	}

	public static void setItems(ArrayList<SimpleItem> items) {
		Application.items = items;
	}

	public static String getCurrentEmail() {
		// TODO Auto-generated method stub
		return userEmail;
	}

	public static SimpleItem getCurrentItem() {
		return currentItem;
	}

	public static void setCurrentItem(SimpleItem currentItem) {
		Application.currentItem = currentItem;
	}
    
}
