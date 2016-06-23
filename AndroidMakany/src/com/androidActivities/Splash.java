package com.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.controllers.Application;
import com.controllers.SessionController;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /** Called when the activity is first created. */
    
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        if (!Application.isHavePredefined())
			SessionController.getPredefined();

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() 
            {
            	
            	final View view = findViewById(R.id.fadeOutLayer);
        		Animation fadeOut = new AlphaAnimation(0f, 1f);
        		fadeOut.setDuration(3000);
        		fadeOut.setAnimationListener(new AnimationListener() {
        			
        			@Override
        			public void onAnimationStart(Animation animation) 
        			{
        			}
        			
        			@Override
        			public void onAnimationRepeat(Animation animation) {}
        			
        			@Override
        			public void onAnimationEnd(Animation animation) {
        			//	view.setVisibility(View.VISIBLE);
        				
        			}
        		});
        		
        		view.startAnimation(fadeOut);
            	
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,LoginActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}