package com.example.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSON_Parser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray Jarray=null;
	static String json = "";
	String res = null;
	
	public JSON_Parser()
	{
		
	}
	//JSONArray
	public JSONObject makeHTTPPOST(String url,String method,String param) //with Encrypted Class
	{
		try 
		{	
			
		HttpClient client = new DefaultHttpClient();
		Log.d("Rqe Url ",url);
		Log.d("Req ", param);
		
		HttpPost post = new HttpPost(url);
		//String encryp=Get_Encrypted(param);
		//param="{\""+Constant_value.LimoRequest+"\":\""+encryp.replaceAll("\n", "")+"\"}";
		
		Log.d("Rqe encrypted ",param);
		
		
		StringEntity stringEntity = new StringEntity(param);
	
		stringEntity.setContentType("application/json");
		
		post.setHeader("Content-Type", "application/json");
		
		post.setEntity(stringEntity);

		BasicHttpResponse httpResponse = (BasicHttpResponse) client.execute(post);
		
		
		//res = EntityUtils.toString(httpResponse.getEntity());  
		HttpEntity httpEntity = httpResponse.getEntity();
		is = httpEntity.getContent();
		//is=EntityUtils.To
		//System.out.println("data Prints ok "+res);
		
		} catch (UnsupportedEncodingException e) 
		{			
			e.printStackTrace();
			return Errer_jsonobject();
		} catch (ClientProtocolException e) 
		{
			//Constant_Method.Errer_jsonobject();
			e.printStackTrace();
			return Errer_jsonobject();
		} catch (IOException e)
		{
			//Constant_Method.Errer_jsonobject();
			e.printStackTrace();
			return Errer_jsonobject();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.d("data prints of ", json);
		} catch (Exception e) 
		{
			//Constant_Method.Errer_jsonobject();
			Log.e("Buffer Error", "Error converting result " + e.toString());
			return Errer_jsonobject();
		}
	//	JSONArray Jarray_1=null;
		try {
			//jObj = new JSONObject(res);
			System.out.println("Convert data...");
			jObj = new JSONObject(json);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Gt Error");
			//Constant_Method.Errer_jsonobject();
			e.printStackTrace();
			return Errer_jsonobject();
		}
	return jObj;

	}
	public static JSONObject Errer_jsonobject()
	{
		JSONObject json=null;
		try {
			 json= new JSONObject("{\"Status\":\"Failure\",\"Message\":\"Please Contact your Service Provide Or please Check your internet Connection\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
