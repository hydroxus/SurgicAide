package com.example.SurgicalMate;

 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

 public class DBAdapter {
 public static final String KEY_ROWID = "id";
 public static final String KEY_PATIENTID = "title";
 public static final String KEY_OROOM = "duedate";
 public static final String KEY_SURGEON = "course";
 public static final String KEY_PROCEDURE = "notes";
 private static final String TAG = "DBAdapter";

 public static final String DATABASE_NAME = "AssignmentsDB.sqlite";
 private static final String DATABASE_TABLE = "assignments";
 private static final int DATABASE_VERSION = 2;

 private static final String DATABASE_CREATE =
 "create table if not exists assignments (id integer primary key autoincrement, "
 + "title VARCHAR not null, duedate date, course VARCHAR, notes VARCHAR);";

 private final Context context;

 private DatabaseHelper DBHelper;
 private SQLiteDatabase db;

 public DBAdapter(Context ctx)
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

 //---opens the database---
 public DBAdapter open() throws SQLException
 {
 db = DBHelper.getWritableDatabase();
 return this;
 }

 //---closes the database---
 public void close()
 {
 DBHelper.close();
 }

 //---insert a record into the database---
 public long insertRecord(String patient_id, String or_number, String surgeon, String procedure)
 {
 ContentValues initialValues = new ContentValues();
 initialValues.put(KEY_PATIENTID, patient_id);
 initialValues.put(KEY_OROOM, or_number);
 initialValues.put(KEY_SURGEON, surgeon);
 initialValues.put(KEY_PROCEDURE, procedure);
 return db.insert(DATABASE_TABLE, null, initialValues);
 }

 //---deletes a particular record---
 public boolean deleteContact(long rowId)
 {
 return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
 }

 //---retrieves all the records---
 public Cursor getAllRecords()
 {
 return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_PATIENTID,
 KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE}, null, null, null, null, null);
 }


 public String getRowID() {

		String[] columns = new String[]{KEY_ROWID,
				 KEY_PATIENTID, KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE};
		Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
 
	int xRow = mCursor.getColumnIndex(KEY_ROWID);
 
 for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
		result = result + mCursor.getString(xRow) +"\n";
	}
 return result;
 }

 public String getPatientID() {

		String[] columns = new String[]{KEY_ROWID,
				 KEY_PATIENTID, KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE};
		Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

		int xPatientID = mCursor.getColumnIndex(KEY_PATIENTID);

for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
		result = result + mCursor.getString(xPatientID) +"\n";
	}
return result;
}
 
 
 public String getORoom() {

		String[] columns = new String[]{KEY_ROWID,
				 KEY_PATIENTID, KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE};
		Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

	int xORoom = mCursor.getColumnIndex(KEY_OROOM);

for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
		result = result + mCursor.getString(xORoom) +"\n";
	}
return result;
}
 
 public String getSurgeon() {

		String[] columns = new String[]{KEY_ROWID,
				 KEY_PATIENTID, KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE};
		Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

	int xSurgeon = mCursor.getColumnIndex(KEY_SURGEON);

for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
		result = result + mCursor.getString(xSurgeon) +"\n";
	}
return result;
}
 
 public String getProcedure() {

		String[] columns = new String[]{KEY_ROWID,
				 KEY_PATIENTID, KEY_OROOM, KEY_SURGEON, KEY_PROCEDURE};
		Cursor mCursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";

	int xProcedure = mCursor.getColumnIndex(KEY_PROCEDURE);

for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
		result = result + mCursor.getString(xProcedure) +"\n";
	}
return result;
}
 
 
 
 //---updates a record---
 public boolean updateRecord(long rowId, String patient_id, String or_number, String surgeon, String procedure)
  {
  ContentValues args = new ContentValues();
  args.put(KEY_PATIENTID, patient_id);
  args.put(KEY_OROOM, or_number);
  args.put(KEY_SURGEON, surgeon);
  args.put(KEY_PROCEDURE, procedure);
 return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
 }
}
