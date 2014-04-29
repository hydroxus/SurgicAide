package com.example.SurgicalMate;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front_page);
		
		//New Session Button
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			 @Override
			 public void onClick(View v) {
			 Intent i = new Intent(MainActivity.this, NewSessionPage.class);
			 startActivity(i);
			 }
			 });
		
		//Databases Button
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {

			 @Override
			 public void onClick(View v) 
			 {
			 Intent i = new Intent(MainActivity.this, Databases.class);
			 startActivity(i);
			 }
			 });	
		
		//About Button
			Button btn3 = (Button) findViewById(R.id.button3);
			btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) 
			{
			Intent i = new Intent(MainActivity.this, About.class);
			startActivity(i);
			}
			});	
	}
	
}