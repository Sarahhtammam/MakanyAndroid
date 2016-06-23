package com.controllers;

import java.util.ArrayList;

import com.simpleModels.Element;
import com.simpleModels.SimpleComment;
import com.simpleModels.SimpleEvent;
import com.simpleModels.SimpleItem;
import com.simpleModels.SimpleMessage;
import com.simpleModels.SimpleOffer;
import com.simpleModels.SimplePost;
import com.simpleModels.SimpleStore;
import com.simpleModels.SimpleUser;

import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;
	public static boolean loggedIn;
	
	public static boolean havePredefined;
	
	public static String currentDistrict;
	
	private static SimpleUser currentUser;
	private static SimpleStore currentStore;
	private static String currentUserType; 
	private static String userEmail; 
	private static SimpleEvent currentEvent;
	private static SimpleItem currentItem;
	private static SimplePost currentPost;
	private static SimpleOffer currentOffer;
	
	private static ArrayList<String> districts;
	private static ArrayList<String> categories;
	private static ArrayList<SimpleEvent> events;
	private static ArrayList<SimplePost> posts;
	private static ArrayList<SimpleOffer> offers;
	private static ArrayList<SimpleComment> Comments;
	private static ArrayList<SimpleItem> items;
	private static ArrayList<Element> Elements;
	private static ArrayList<SimpleMessage> msgNames;
	private static ArrayList<SimpleMessage> messgaes;
	public static String msgTo;
	
	
    public void onCreate(){
        super.onCreate();
        Application.context = getApplicationContext();
        loggedIn= false;
        havePredefined = false;
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


	public static ArrayList<String> getDistricts() 
	{
		return districts;
	}

	public static void setDistricts(ArrayList<String> districts) {
		Application.districts = districts;
	}
	
	
	public static ArrayList<String> getCategories() 
	{
		return categories;
	}

	public static void setCategories(ArrayList<String> categories) {
		Application.categories = categories;
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
	
	public static void addLocalMessage(SimpleMessage msg)
	{
		Application.messgaes.add(msg);
	}

	public static String getCurrentDistrict() {
		return currentDistrict;
	}

	public static void setCurrentDistrict(String currentDistrict) {
		Application.currentDistrict = currentDistrict;
	}

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userE) 
	{
		userEmail = userE;
	}


	public static boolean isHavePredefined() {
		return havePredefined;
	}

	public static void setHavePredefined(boolean havePredefined) {
		Application.havePredefined = havePredefined;
	}

	public static SimplePost getCurrentPost() {
		return currentPost;
	}

	public static void setCurrentPost(SimplePost currentPost) {
		Application.currentPost = currentPost;
	}

	public static ArrayList<SimpleComment> getComments() {
		return Comments;
	}

	public static void setComments(ArrayList<SimpleComment> comments) {
		Comments = comments;
	}

	public static SimpleOffer getCurrentOffer() {
		return currentOffer;
	}

	public static void setCurrentOffer(SimpleOffer currentOffer) {
		Application.currentOffer = currentOffer;
	}

	public static SimpleStore getCurrentStore() {
		return currentStore;
	}

	public static void setCurrentStore(SimpleStore currentStore) {
		Application.currentStore = currentStore;
	}

	public static String getCurrentUserType() {
		return currentUserType;
	}

	public static void setCurrentUserType(String currentUserType) {
		Application.currentUserType = currentUserType;
	}

	public static ArrayList<SimpleOffer> getOffers() {
		return offers;
	}

	public static void setOffers(ArrayList<SimpleOffer> offers) {
		Application.offers = offers;
	}

	
    
}
