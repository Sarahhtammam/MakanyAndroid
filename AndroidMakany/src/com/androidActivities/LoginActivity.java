package com.androidActivities;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.controllers.Application;
import com.controllers.EventController;
import com.controllers.SessionController;
import com.controllers.StoreController;
import com.controllers.UserController;
import com.controllers.WhatsNewController;
import com.simpleModels.SimpleUser;

public class LoginActivity extends Activity implements OnClickListener {

	EditText emailEditText;
	EditText passwordEditText;
	Button loginButton;

	Button signUp;
	Button signUpStore;

	String loginMessage = "";
	SimpleUser currentLoggedUser = null;
	ProgressDialog myDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		

		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);

		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(this);

		signUp = (Button) findViewById(R.id.SignupButton);
		signUpStore = (Button) findViewById(R.id.SignupStoreButton);

		signUp.setOnClickListener(this);
		signUpStore.setOnClickListener(this);

		if (!Application.isHavePredefined())
			SessionController.getPredefined();

		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		if (!pref.getString("email", "").equals("")) {
			UserController userController = new UserController();
			userController.getUser(pref.getString("email", ""));

			Application.setUserEmail(pref.getString("email", ""));
			Application.setCurrentUserType(pref.getString("type", ""));
			
			if (Application.getCurrentUserType().equals("User"))
			{
				WhatsNewController whatsNewController = new WhatsNewController();
				whatsNewController.getStaticRecommendation(pref.getString("email",
						""));
			}
			else
			{
				
				StoreController store = new StoreController();
				store.getStoreService(pref.getString("email", ""));
				SessionController.login();	
				
				Fragment F = new ViewMyOffers();
				store.getStoreOffersService(pref.getString("email", ""),(ViewMyOffers)F);
				Intent storeIntent = new Intent(Application.getAppContext(),StoreHomeActivity.class);
				storeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Application.getAppContext().startActivity(storeIntent);
			}
			
		
		}
		

	}

	public void timerDelayRemoveDialog(long time, final Dialog d) {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				d.dismiss();

			}
		}, time);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		if (id == R.id.loginButton) {
			if (emailEditText.getText().toString().trim().equals("")) {
				emailEditText.setError("Email is required!");
			} else if (passwordEditText.getText().toString().trim().equals("")) {
				passwordEditText.setError("Password is required!");
			} else {

				myDialog = ProgressDialog.show(LoginActivity.this,
						"Authenticating", "Please wait ...", true);
				myDialog.getWindow().setGravity(Gravity.CENTER);

				UserController userController = new UserController();
				userController.login(emailEditText.getText().toString(),
						passwordEditText.getText().toString(), myDialog);
				timerDelayRemoveDialog(5000, myDialog);

			}

		}

		if (id == R.id.SignupButton) {
			if (Application.getCategories() == null
					|| Application.getDistricts() == null) {
				Toast.makeText(Application.getAppContext(),
						"Please make sure that the Internet is on",
						Toast.LENGTH_SHORT).show();
				SessionController.getPredefined();
			} else {
				Intent signupIntent = new Intent(getApplicationContext(),
						SignUpActivity.class);
				startActivity(signupIntent);
			}

		}

		if (id == R.id.SignupStoreButton) {

			Intent signUpStore = new Intent(Application.getAppContext(),StoreSignUpActivity.class);
				startActivity(signUpStore);

		}

	}

}