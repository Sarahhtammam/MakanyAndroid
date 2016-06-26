package com.androidActivities;

import com.controllers.Application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;


public class CheckConnectivity extends BroadcastReceiver{

@Override
public void onReceive(Context context, Intent arg1) {

    boolean isConnected = arg1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
    if(isConnected){
        Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
        Intent msgIntent = new Intent(Application.getAppContext(),InternetMsgActivity.class);
        msgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Application.getAppContext().startActivity(msgIntent);
        
    }
    else{
        Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
    }
   }



 }