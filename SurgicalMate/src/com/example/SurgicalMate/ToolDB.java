package com.example.SurgicalMate;

 import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



 	public class ToolDB {
 				
 		public static final String KEY_ROWID = "id";
 		public static final String KEY_NAME = "name";
 		public static final String KEY_QUANTITY = "quantity";
 		public static final String KEY_CURRENT = "current";
 		public static final String KEY_RESULT = "result";
 		private static final String TAG = "DBAdapter";

 		private static final String DATABASE_NAME = "Medicine_Database";
 		private static final String DATABASE_TABLE = "meds_db";
 		private static final int DATABASE_VERSION = 2;

 		private static final String DATABASE_CREATE =
 				"create table if not exists meds_db (id integer primary key autoincrement, "
 				 + "name VARCHAR not null, quantity VARCHAR, current VARCHAR , result VARCHAR)";

 		public static final String SCAN_RESULT = "MyPreferencesFile";
 		
 		private final Context context;

 		private DatabaseHelper DBHelper;
 		private SQLiteDatabase db;

 		public ToolDB(Context ctx)
 		{
 			this.context = ctx;
 			DBHelper = new DatabaseHelper(context);
 		}

 		private static class DatabaseHelper extends SQLiteOpenHelper
 		{
 			DatabaseHelper(Context context)
 			{
 				super(context, DATABASE_NAME, null, DATABASE_VERSION);
 			}
 	 		
 			@Override
 			public void onCreate(SQLiteDatabase db)
 				{
 				try {
 					db.execSQL(DATABASE_CREATE);
 				} catch (SQLException e) {
 					e.printStackTrace();
 				}
 				}

 			@Override
 			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
 			{
 				Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
 						+ newVersion + ", which will destroy all old data");
 				db.execSQL("DROP TABLE IF EXISTS contacts");
 				onCreate(db);
 			}
 		}
 		 		
 		
 		public String OriginalCount(Context context){
 			
 			Testing x = new Testing();
 			String code = x.QRcodeReturn(context);
 			 			
 			String quantity=null;
 			String selectQuery = "SELECT " + KEY_QUANTITY + " FROM " + DATABASE_TABLE + " WHERE " 
 		 			+ KEY_ROWID + " = " + code;
 			Cursor c = db.rawQuery(selectQuery, null);
 			
 			if(null != c && c.moveToFirst()){
 				quantity  = c.getString(c.getColumnIndex(KEY_QUANTITY));
 			}
			return quantity;
 		}
 		
 		public String QuantReturn(Context context){
 			
 			Testing x = new Testing();
 			String code = x.QRcodeReturn(context);
 			 			
 			String quantity=null;
 			String selectQuery = "SELECT " + KEY_CURRENT + " FROM " + DATABASE_TABLE + " WHERE " 
 		 			+ KEY_ROWID + " = " + code;
 			Cursor c = db.rawQuery(selectQuery, null);
 			
 			if(null != c && c.moveToFirst()){
 				quantity  = c.getString(c.getColumnIndex(KEY_CURRENT));
 			}
			return quantity;
 		}
 		
 			public String Result(Context context){
 			
 			Testing x = new Testing();
 			String code = x.QRcodeReturn(context);
 			 			
 			String quantity=null;
 			String selectQuery = "SELECT " + KEY_RESULT + " FROM " + DATABASE_TABLE + " WHERE " 
 		 			+ KEY_ROWID + " = " + code;
 			Cursor c = db.rawQuery(selectQuery, null);
 			
 			if(null != c && c.moveToFirst()){
 				quantity  = c.getString(c.getColumnIndex(KEY_RESULT));
 			}
			return quantity;
 		}
 		
 		
 		public int RowIdReturn(Context context){
 			
 			
 			UserInput x = new UserInput();
 			String code = x.QRcodeReturn(context);
 			
 			int rowid=0;
 			String selectQuery = "SELECT " + KEY_ROWID + " FROM " + DATABASE_TABLE + " WHERE " 
 			+ KEY_ROWID + " = " + code;
 			Cursor c = db.rawQuery(selectQuery, null);
 			
 			if(null != c && c.moveToFirst()){
 				rowid  = c.getInt(c.getColumnIndex(KEY_ROWID));
 			}
			return rowid;
 		}
 		
 		public String NameReturn(Context context){
 			
 			Testing x = new Testing();
 			String code = x.QRcodeReturn(context);
 			 			
 			String name=null;
 			String selectQuery = "SELECT " + KEY_NAME + " FROM " + DATABASE_TABLE + " WHERE " 
 		 			+ KEY_ROWID + " = " + code;
 			Cursor c = db.rawQuery(selectQuery, null);
 			
 			if(null != c && c.moveToFirst()){
 				name  = c.getString(c.getColumnIndex(KEY_NAME));
 			}
			return name;
 		}
 		
 		
 		
 		
 		//---opens the database---
 		public ToolDB open() throws SQLException
 		{
 			db = DBHelper.getWritableDatabase();
 			return this;
 		}

 		//---closes the database---
 		public void close()
 		{
 			DBHelper.close();
 		}


		 //---deletes a particular record---
		 public boolean deleteContact(long rowId)
		 {
		 return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
		 }
		
		 //---retrieves all the records---
		 public Cursor getAllRecords()
		 {
		 return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT ,KEY_RESULT}, null, null, null, null, null);
		 }
		
		
		 public String getRowID() {
		
				String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT,KEY_RESULT};
				Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
				String result = "";
		 
			int xRow = mCursor.getColumnIndex(KEY_ROWID);
		 
			for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
				result = result + mCursor.getString(xRow) +"\n";
			}
		 return result;
		 }
		
		 public String getName() {
		
				String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT,KEY_RESULT
						};
				Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
				String result = "";
		
				int xMedName = mCursor.getColumnIndex(KEY_NAME);
		
		for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
				result = result + mCursor.getString(xMedName) +"\n";
			}
		return result;
		}
		 
		 
		 public String getQuantity() 
		 {
			 	String result = "";
				String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT,KEY_RESULT};
				Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
				
		
			int xQuant = mCursor.getColumnIndex(KEY_QUANTITY);
		
		for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext())
		{
				
				result = result + mCursor.getInt(xQuant)+"\n";
			}
		return result;
		}
	
 
		 public String getCurrent() {
		
				String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT, KEY_RESULT};
				Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
				String result = "";
		
			int xCurr = mCursor.getColumnIndex(KEY_CURRENT);
		
		for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
				result = result + mCursor.getInt(xCurr)+"\n";
			}
		return result;
		}
 
		 public String getResult() {
		
				String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_CURRENT,KEY_RESULT};
				Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
				String result = "";
		
			int xCurr = mCursor.getColumnIndex(KEY_RESULT);
		
		for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
				result = result + mCursor.getInt(xCurr)+"\n";
			}
		return result;
		}
 
 
		 //---updates a record---
		 public boolean updateRecord(long rowId, int current, int result)
		  {
		  ContentValues args = new ContentValues();
		  args.put(KEY_CURRENT, current);
		  args.put(KEY_RESULT,result);				  
				  
		 return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
		 }
		 
 
 
 //---Inserts a new record into database---
 	public long createEntry(String name, int quantity, int current) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_QUANTITY, quantity);
		cv.put(KEY_CURRENT, current);
		cv.put(KEY_RESULT, quantity - current);
		Log.e("item insert", "name"+name);
		long l =db.insert(DATABASE_TABLE, null, cv);
		return l;
		
 		}
 	 
 	public void update_result(int id)
 	{
// 		ContentValues con= new ContentValues();
 		String qury = "UPDATE  meds_db set result =quantity- current where id="+id;
 		db.execSQL(qury);
 	}
 	
 	
 	  
 	/*
 	public String[] MissingItemSearch(){
 			
        
			String selectQuery = "SELECT " +  KEY_RESULT + " FROM " + DATABASE_TABLE;
			Cursor c = db.rawQuery(selectQuery, null);
			int ItemCount = c.getColumnIndex(KEY_RESULT);
			int x = 0;
			String names[] = {};
			
			if(c != null  && c.moveToFirst()){
				do{
						if(ItemCount != -1){
							names[x] = (c.getString(c.getColumnIndex(KEY_NAME)));
							x +=1;
						} 
					}
				while (c.moveToNext());
				}
				c.close();
				
		return names;
		}
 	*/
 	}