package com.mypackage.idietandroid;

import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
 
public class TestAdapter  
{ 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
 
    public TestAdapter(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 
 
    public TestAdapter createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public TestAdapter open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close()  
    { 
        mDbHelper.close(); 
    } 
    
   /* public void deleteAllData(){
    	String sql ="Delete* FROM User;"; 
    	 
        Cursor mCur = mDb.rawQuery(sql, null); 
    	
    }*/
 
     public Cursor getTestData() 
     { 
         try 
         { 
             String sql ="SELECT id,year FROM User;"; 
 
             Cursor mCur = mDb.rawQuery(sql, null); 
             if (mCur!=null) 
             { 
                mCur.moveToNext(); 
             }
             int i=1;
             while(mCur.isAfterLast() == false){
            	 String name = Utility.GetColumnValue(mCur, "id");
     	    	String email = Utility.GetColumnValue(mCur, "year");
     	    	
     	    	Log.e("row", email);
             }
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
        	 //Log.e("dbAdapter", mSQLException.getMessage().toString());
             Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }
     
     
     public boolean saveUser(String fName, String lName, String pword, double height, double weight, int bPres, int sugar,
    		 int metabolism, int activity, int year, int bday, int month, int gender, int units) {
    	 try{
    		 ContentValues cv = new ContentValues();
        	 cv.put("fName", fName);
        	 cv.put("lName", lName);
        	 cv.put("password", pword);
        	 cv.put("height", height);
        	 cv.put("weight", weight);
        	 cv.put("bp", bPres);
        	 cv.put("sugar", sugar);
        	 cv.put("metabolism", metabolism);
        	 cv.put("activity", activity);
        	 cv.put("year", year);
        	 cv.put("day", bday);
        	 cv.put("month", month);
        	 cv.put("gender", gender);
        	 cv.put("units", units);
        	 mDb.insert("User", null, cv);

  			Log.d("saveUser", "informationsaved");
  			return true;
    		 
    	 }
    	 catch(Exception ex)
  		{
  			Log.d("SaveUser", ex.toString());
  			return false;
  		}
    	 
     }
     
     long noOfRowsInTable(){
    	 /*long count;*/
    	 return DatabaseUtils.queryNumEntries(mDb, "User");
     }

 	public boolean SaveEmployee(String name, String email) 
 	{
 		try
 		{
 			ContentValues cv = new ContentValues();
 			cv.put("Name", name);
 			cv.put("Email", email);
 			
 			mDb.insert("Employees", null, cv);

 			Log.d("SaveEmployee", "informationsaved");
 			return true;
 			
 		}
 		catch(Exception ex)
 		{
 			Log.d("SaveEmployee", ex.toString());
 			return false;
 		}
 	}
 	
} 
