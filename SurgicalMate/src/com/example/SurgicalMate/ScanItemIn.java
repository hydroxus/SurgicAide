package com.example.SurgicalMate;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class ScanItemIn extends Activity {

	public static final String SCAN_RESULT = "MyPreferencesFile";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_item_in);
		
		ToolDB db = new ToolDB(ScanItemIn.this);
		db.open();
		String ItemName = db.NameReturn(ScanItemIn.this);
		db.close();
		
	//Creating dialog box for entering quantity	
	final AlertDialog.Builder alert = new AlertDialog.Builder(this);
	
	alert.setTitle(ItemName);
	alert.setMessage("Enter Quantity:");
	alert.setCancelable(false);
	final EditText input = new EditText(this);
	alert.setView(input);
	input.setFilters(new InputFilter[] {
		    // Maximum 3 characters.
		    new InputFilter.LengthFilter(3),
		    // Digits only.
		    DigitsKeyListener.getInstance(),
		});
		// Digits only & use numeric soft-keyboard.
		input.setKeyListener(DigitsKeyListener.getInstance());

	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		
	public void onClick(DialogInterface dialog, int whichButton) {
	  int value = Integer.parseInt(input.getText().toString());	
	  
		   if(value<=0){		  
		   Toast.makeText(ScanItemIn.this,"Error: Enter quantity above zero.", Toast.LENGTH_LONG).show(); 
		   }else{
		   ToolDB db = new ToolDB(ScanItemIn.this);
		   db.open();
		   String xOrigin = db.OriginalCount(ScanItemIn.this);
		   int xOriginInt = Integer.parseInt(xOrigin);
		   
		   String xCurrent = db.QuantReturn(ScanItemIn.this);
		   int xCurrentInt = Integer.parseInt(xCurrent);
		   
		   String xResult = db.Result(ScanItemIn.this);
		   int xResultInt = Integer.parseInt(xResult);
		   db.close();
				
		  if((value+xCurrentInt) > xOriginInt){
			  Toast.makeText(ScanItemIn.this,"Error: Quantity cannot exceed original amount.", 
					  Toast.LENGTH_LONG).show();
		  }else{
			  db.open();
			  int xRowID = db.RowIdReturn(ScanItemIn.this);
			  xCurrentInt = xCurrentInt + value;
			  xResultInt = xOriginInt - xCurrentInt;
			  db.updateRecord(xRowID, xCurrentInt, xResultInt);
			  db.close();
		  }
	  }
	
	  Intent i = new Intent(ScanItemIn.this, MainMenu.class);
	  startActivity(i);
	}
	});

	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int whichButton) {
		  
		  Intent i = new Intent(ScanItemIn.this, MainMenu.class);
		  startActivity(i);
	  }
	});

	alert.show();
	
}
	
	public String QRcodeReturn(Context context){
		
		SharedPreferences codeHack = context.getSharedPreferences(SCAN_RESULT,0);
		String QRcode = codeHack.getString("entry", "unregistered");
		
		return QRcode;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_input, menu);
		return true;
	}

}
