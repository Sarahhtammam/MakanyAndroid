package com.androidActivities;

import SimpleModels.SimpleUser;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.controllers.UserController;


public class LoginActivity extends Activity implements OnClickListener{

	EditText emailEditText;
	EditText passwordEditText;
	Button loginButton;
	
	String loginMessage = "";
	SimpleUser currentLoggedUser = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	
		emailEditText = (EditText) findViewById(R.id.email);
		passwordEditText = (EditText) findViewById(R.id.password);
		
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v)
	{
		
		
		if (emailEditText.getText().toString().trim().equals(""))
		{
			emailEditText.setError( "Email is required!" );
		}
		else if (passwordEditText.getText().toString().trim().equals(""))
		{
			passwordEditText.setError( "Password is required!" );
		}
		else
		{
			UserController userController = new UserController();
			userController.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
			
		}
		
		
		
	}

}