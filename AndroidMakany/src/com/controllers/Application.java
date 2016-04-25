package com.controllers;

import java.util.ArrayList;

import SimpleModels.SimpleEvent;
import SimpleModels.SimplePost;
import SimpleModels.SimpleUser;
import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;
	private static UserController userController;
	private static SimpleUser currentUser;
	private static String userName;
	private static SimpleEvent currentEvent;
	
	private static ArrayList<SimpleEvent> events;
	private static ArrayList<SimplePost> posts;
	
	
    public void onCreate(){
        super.onCreate();
        Application.context = getApplicationContext();
        Application.userController = UserController.getInstance();
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

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Application.userName = userName;
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
    
}
