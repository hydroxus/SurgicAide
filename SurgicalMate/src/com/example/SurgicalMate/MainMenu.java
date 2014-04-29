package com.example.SurgicalMate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.json.JSON_Parser;


public class MainMenu extends Activity implements OnClickListener
{
	
	public boolean isScanOut;
	public static final String SCAN_RESULT = "MyPreferencesFile";
	ProgressDialog pdilog;
	Button ScanOut;
	public static final String CONNECTION_URL="10.16.112.66:89";// helllo  
	// Change your IP ADDRESS  , and Hosted webservice Port number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        ScanOut=(Button)findViewById(R.id.scanout);
        ScanOut.setOnClickListener(this);
        
        Button ScanIn=(Button)findViewById(R.id.scanin);
        ScanIn.setOnClickListener(this);
        
        Button EndSession = (Button) findViewById(R.id.endsession);
        EndSession.setOnClickListener(this);
    }
       
   
        	@Override
        	public void onClick(View v) {
        		
        		if(v.getId()==R.id.scanout){
        			
        			isScanOut = true;
        			IntentIntegrator.initiateScan(this);
        		}
        		else if(v.getId()==R.id.scanin){
        			
        			isScanOut = false;
        			IntentIntegrator.initiateScan(this);
        		}
        		else if(v.getId()==R.id.endsession)
        		{
        			
        			new Asyn_upload_backup().execute();
        			//Intent endsessionintent = new Intent(MainMenu.this, MainActivity.class);
//        			startActivity(endsessionintent);
        		}	
        	}
        	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    	switch(requestCode) {
		    	case IntentIntegrator.REQUEST_CODE: 
		    	{
		    		if (resultCode != RESULT_CANCELED) {
		    			IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		    	
		    					if (scanResult != null) {
		    					String qrCode = scanResult.getContents();
		    	 
		    					SharedPreferences codeHack = getSharedPreferences(SCAN_RESULT,0);
		    					SharedPreferences.Editor editor = codeHack.edit();
		    					editor.putString("entry", qrCode);
		    					editor.commit();
		    					
		    						if(isScanOut==true){
		    						
		    							Intent i = new Intent(getApplicationContext(), UserInput.class);
		    							startActivity(i);
		    						}
		    						else if(isScanOut==false){
		    						
		    							Intent i = new Intent(getApplicationContext(), ScanItemIn.class);
		    							startActivity(i);
		    						}
		    				   }
		    		}
		    	}
		    	}
			}
		
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   /* public static void BackupDatabase(String name) throws IOException 
	{
		boolean success = true;
		File file = null;
		file = new File(Environment.getExternalStorageDirectory()+ "/Qr_Code_files");

		if (file.exists()) {
			success = true;
		} else {
			success = file.mkdir();
		}
		if (success) {
			String inFileName = "/data/data/"+name+"/databases/"+DBAdapter.DATABASE_NAME;
			File dbFile = new File(inFileName);
			FileInputStream fis = new FileInputStream(dbFile);
			String outFileName = Environment.getExternalStorageDirectory()+ "/Qr_Code_files/"+DBAdapter.DATABASE_NAME;
			OutputStream output = new FileOutputStream(outFileName);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0)
			{
				output.write(buffer, 0, length);
			}

			output.flush();
			output.close();
			fis.close();
		}
	}*/
    public String image_to_string(String name) throws FileNotFoundException, IOException
    {
    	//  here we get the file from Application 
    	File f = new File("/data/data/"+name+"/databases/"+DBAdapter.DATABASE_NAME);//(Environment.getExternalStorageDirectory()+ "/Qr_Code_files/"+DBAdapter.DATABASE_NAME);
    	if(!f.exists())
    	{
    		return "";
    	}
    	FileInputStream fis = new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) 
            {
                bos.write(buf, 0, readNum); //no doubt here is 0
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
    	String file_str =null;
        try
        {
        	// Convert file To BASE64 String array
        	file_str=Base64.encodeToString(bytes, 0);
        }
        catch(Exception e)
        {
        	System.out.println("at here get error in image converter"+e);
        	return file_str;
        }
        //at here i get Return string to calle function 
    	return file_str;
    }
    
    
  public class Asyn_upload_backup extends AsyncTask<String, String, String>
  {
	  @Override
	protected void onPreExecute() 
	 {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pdilog= new ProgressDialog(MainMenu.this);
		pdilog.setMessage("Please wait database backup taking ...");
		pdilog.setIndeterminate(false);
		pdilog.setCancelable(true);
		pdilog.show();
		/*String name = getApplicationContext().getPackageName();
		try 
		{
			BackupDatabase(name);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	@Override
	protected String doInBackground(String... params) 
	{
		// TODO Auto-generated method stub
		JSON_Parser json = new JSON_Parser();
		String param = null;
		try 
		{
			//  get pageckage name 
			String name = getApplicationContext().getPackageName();
			// here we send pageckage name of application and get BASE64 String
			// and make JSON Array for send to server
			param = "{\"file\":\""+image_to_string(name)+"\"}";
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Test KD/"+param.replace("\n", ""));
		//this is server call 1 > URL , 2 > Methods name (POST) 3 > JSON ARRAY
		JSONObject _Jobj=json.makeHTTPPOST("http://"+CONNECTION_URL+"/Service.svc/SaveFile", "POST", param.replace("\n", ""));
		try {
			final String mess = _Jobj.getString("Message");
			if (_Jobj.getString("Status").equals("Success"))
			{
				ScanOut.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						pdilog.dismiss();
						Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG).show();
						Intent endsessionintent = new Intent(MainMenu.this, MainActivity.class);
	        			startActivity(endsessionintent);
					}
				});
			}
			else
			{
				ScanOut.post(new Runnable() {
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG).show();
						Intent endsessionintent = new Intent(MainMenu.this, MainActivity.class);
	        			startActivity(endsessionintent);
					}
				});
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	  @Override
	protected void onPostExecute(String result)
	  {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pdilog.dismiss();
	}
  }
    
    
}
