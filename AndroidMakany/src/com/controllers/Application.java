package com.controllers;

import java.util.ArrayList;

import SimpleModels.Element;
import SimpleModels.SimpleEvent;
import SimpleModels.SimpleItem;
import SimpleModels.SimpleMessage;
import SimpleModels.SimplePost;
import SimpleModels.SimpleUser;
import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;
	public static boolean loggedIn;
	
	public static String currentDistrict;
	
	private static SimpleUser currentUser;
	private static String userEmail; 
	private static SimpleEvent currentEvent;
	private static SimpleItem currentItem;
	
	private static ArrayList<SimpleEvent> events;
	private static ArrayList<SimplePost> posts;
	private static ArrayList<SimpleItem> items;
	private static ArrayList<Element> Elements;
	private static ArrayList<SimpleMessage> msgNames;
	private static ArrayList<SimpleMessage> messgaes;
	public static String msgTo;
	
	
    public void onCreate(){
        super.onCreate();
        Application.context = getApplicationContext();
        loggedIn= false;
    }

    public static Context getAppContext() {
        return Application.context;
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

	public static ArrayList<Element> getElements() {
		return Elements;
	}

	public static void setElements(ArrayList<Element> elements) {
		Elements = elements;
	}

	public static ArrayList<SimpleMessage> getMessgaes() {
		return messgaes;
	}

	public static void setMessgaes(ArrayList<SimpleMessage> messgaes) {
		Application.messgaes = messgaes;
	}

	public static ArrayList<SimpleMessage> getMsgNames() {
		return msgNames;
	}

	public static void setMsgNames(ArrayList<SimpleMessage> msgNames) {
		Application.msgNames = msgNames;
	}

	public static String getMsgTo() {
		return msgTo;
	}

	public static void setMsgTo(String msgTo) {
		Application.msgTo = msgTo;
	}
    
}
