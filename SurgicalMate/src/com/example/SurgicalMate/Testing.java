package com.example.SurgicalMate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Testing extends Activity {

		
	public static final String SCAN_RESULT = "MyPreferencesFile";
	
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testing);
	}
	
	/*
	public void bla(){
		
		 ToolDB db = new ToolDB(this);
		 String c1 = db.MissingItemSearch();
		 TextView t3 = (TextView) findViewById(R.id.slot4);
		 if(c1 == null){
			 t3.setText("not working!");
		 }else{
		 
		 t3.setText(c1);
		 }
		 String[] c1 = db.MissingItemSearch();
		 
		 
	     TextView t3 = (TextView) findViewById(R.id.slot4);
	     for(int x = 0; x <= c1.length; x++){
	     String old=t3.getText().toString();
	     String old2 = c1[x];
	     String yay= old + old2;

	     t3.setText(yay);
	     }	   
	}*/

	public String QRcodeReturn(Context context){
		
		SharedPreferences codeHack = context.getSharedPreferences(SCAN_RESULT,0);
	    if (codeHack != null) {
	        String QRcode = codeHack.getString("entry", "unregistered");
	        return QRcode;
	    } else {
	        return "";
	    }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.testing, menu);
		return true;
	}

}
