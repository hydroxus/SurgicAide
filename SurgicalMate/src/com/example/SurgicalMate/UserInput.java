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

public class UserInput extends Activity {

	public static final String SCAN_RESULT = "MyPreferencesFile";
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_input);
	
		   ToolDB db = new ToolDB(UserInput.this);
		   db.open();
		   String ItemName = db.NameReturn(UserInput.this);
		   db.close();
		
			
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
		  Toast.makeText(UserInput.this,"Error: Enter quantity greater than zero.", Toast.LENGTH_LONG).show();
	  }else{
		  
		   ToolDB db = new ToolDB(UserInput.this);
		   db.open();
		   String xOrigin = db.OriginalCount(UserInput.this);
		   int xOriginInt = Integer.parseInt(xOrigin);
		   String xCurrent = db.QuantReturn(UserInput.this);
		   int xCurrentInt = Integer.parseInt(xCurrent);
		   String xResult = db.Result(UserInput.this);
		   int xResultInt = Integer.parseInt(xResult);
		   db.close();
				
		  if(value > xCurrentInt){
			  Toast.makeText(UserInput.this,"Error: Value exceeds current quantity.", Toast.LENGTH_LONG).show();
		  }else{
			  db.open();
			  int xRowID = db.RowIdReturn(UserInput.this);
			  xCurrentInt = xCurrentInt - value;
			  xResultInt = xOriginInt - xCurrentInt;
			  db.updateRecord(xRowID, xCurrentInt, xResultInt);
			  db.close();
		  }
	  }
	
	  Intent a = new Intent(UserInput.this, MainMenu.class);
	  startActivity(a);
	}
	});

	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int whichButton) {
		  
		  Intent a = new Intent(UserInput.this, MainMenu.class);
			startActivity(a);
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
