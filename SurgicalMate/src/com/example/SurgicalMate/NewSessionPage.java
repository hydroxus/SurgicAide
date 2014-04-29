package com.example.SurgicalMate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewSessionPage extends Activity{


DBAdapter db = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
	}
	
	
	public void goToMainMenu(View v) {
			
			
		 Log.d("test", "adding");
		 EditText et1 = (EditText)findViewById(R.id.EditTextPatientID);
		 Spinner sp1 = (Spinner)findViewById(R.id.SpinnerORnumber);
		 Spinner sp2 = (Spinner)findViewById(R.id.SpinnerSurgeon);
		 Spinner sp3 = (Spinner)findViewById(R.id.ProcedureType);
		 
		 
		if (et1.getText().toString().trim().length()==0 || sp1.getSelectedItemPosition()==0 || 
				sp2.getSelectedItemPosition()==0 || sp3.getSelectedItemPosition()==0){
			//warning
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("Error");
			builder1.setMessage("Please fill out all Queries.");
			builder1.setCancelable(false);
			builder1.setNeutralButton(android.R.string.ok,
			        new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
			        dialog.cancel();
			    }
			});
			AlertDialog alert1 = builder1.create();
			alert1.show();
		 }
		 else{
		 db.open();
		 db.insertRecord(et1.getText().toString(), sp1.getSelectedItem().toString(), sp2.getSelectedItem().toString(), sp3.getSelectedItem().toString());
		 db.close();
		 		 
		 Toast.makeText(NewSessionPage.this,"Patient Added", Toast.LENGTH_LONG).show();
		 
		 Intent i = new Intent(this, MainMenu.class);
		 startActivity(i);
			}
		
		}
	
	 public void goToFrontPage(View v)
	 {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	 }



}
