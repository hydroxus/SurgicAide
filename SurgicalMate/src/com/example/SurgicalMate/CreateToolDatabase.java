package com.example.SurgicalMate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateToolDatabase extends Activity{


	ToolDB db = new ToolDB(this);
// 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_tool_database);
	}
	
	public void SaveItemEntry(View v)
	{
		 Log.d("test", "adding");
		 EditText et1 = (EditText)findViewById(R.id.ItemName);
		 EditText et2 = (EditText)findViewById(R.id.Quantity);
		 
		 
		 
		 if (et1.getText().toString().trim().length()==0 || et2.getText().toString().trim().length()==0){
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
		 }else{
		 db.open();
		 db.createEntry(et1.getText().toString(), Integer.parseInt(et2.getText().toString()), 
				 Integer.parseInt(et2.getText().toString()));
		 db.close();
			 
		 et1.setText("");
		 et2.setText("");
		 Toast.makeText(CreateToolDatabase.this,"Item Added", Toast.LENGTH_LONG).show();
			 }
	}
	
	public void goToDB(View v){
		
			Intent i = new Intent(this, ToolsTableLayout.class);
			startActivity(i);
	}
	
	 
}
