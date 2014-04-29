package com.example.SurgicalMate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPassword extends Activity {

	
	private EditText  username=null;
	private EditText  password=null;
	private Button login;
	private Button cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_pass);
		
		username = (EditText)findViewById(R.id.etUsername);
		password = (EditText)findViewById(R.id.etPassword);
		login = (Button)findViewById(R.id.btn_login);
		cancel = (Button)findViewById(R.id.btn_cancel);
	}
	
	public void login(View view){
	      if(username.getText().toString().equals("admin") && 
	      password.getText().toString().equals("edp")){
	      
	    	  Intent i = new Intent(this, Databases.class);
	  		  startActivity(i);
	   }	
	   else{
	      Toast.makeText(getApplicationContext(), "Wrong Credentials",
	      Toast.LENGTH_SHORT).show();
	   	  }
	   }
	
	public void cancel(View v){
		Intent i = new Intent(LoginPassword.this, MainActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_password, menu);
		return true;
	}

}
