package com.example.SurgicalMate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Databases extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.databases_menu);
	}

		
	
	public void goToDB(View v){
		
		Intent i = new Intent(this, CreateToolDatabase.class);
		startActivity(i);
	}	
	
	public void ViewToolDB(View v){
		Intent i = new Intent(this, ToolsTableLayout.class);
		startActivity(i);
	}
	
	public void ViewPatDB(View v){
		Intent i = new Intent(this, DatabaseTableLayout.class);
		startActivity(i);
	}
	
	public void BackHome(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.databases, menu);
		return true;
	}

}
