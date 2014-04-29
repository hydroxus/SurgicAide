package com.example.SurgicalMate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class DatabaseTableLayout extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.tablelayout1);


	        TextView t1 = (TextView) findViewById(R.id.slot1);
	        TextView t2 = (TextView) findViewById(R.id.slot2);
	        TextView t3 = (TextView) findViewById(R.id.slot3);
	        TextView t4 = (TextView) findViewById(R.id.slot4);
	        TextView t5 = (TextView) findViewById(R.id.slot5);

	        DBAdapter info = new DBAdapter(this);
			info.open();
			String c1 = info.getRowID();
			String c2 = info.getPatientID();
			String c3 = info.getORoom();
			String c4 = info.getSurgeon();
			String c5 = info.getProcedure();
			info.close();

	        t1.setGravity(Gravity.CENTER_HORIZONTAL);
	        t2.setGravity(Gravity.CENTER_HORIZONTAL);
	        t3.setGravity(Gravity.CENTER_HORIZONTAL);
	        t4.setGravity(Gravity.CENTER_HORIZONTAL);
	        t5.setGravity(Gravity.CENTER_HORIZONTAL);

	        t1.setText(c1);
			t2.setText(c2);
			t3.setText(c3);
			t4.setText(c4);
			t5.setText(c5);
	    }
	
		public void goToMainMenu(View v)
		 {
			Intent i = new Intent(this, Databases.class);
			startActivity(i);
		 }
	
	}
