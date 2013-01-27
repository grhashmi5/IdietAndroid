package com.mypackage.idietandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
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
    
    public String getFactorFromFood (String factorName, String foodName){
    	String selectQuery;
    	String result;
    	selectQuery = "SELECT " +factorName+" FROM food WHERE long_desc = "+"'"+foodName+"'";
    	 Cursor cursor = mDb.rawQuery(selectQuery, null);
    	 if (cursor.getCount()>0){
    		 cursor.moveToFirst();
    		 result = cursor.getString(0);
    	 }
    	 else{
    		 result = null;
    	 }
    	 
    	 return result;
    }
    
    public int getFoodId(String long_desc){
    	String selectQuery = "SELECT id FROM food WHERE long_desc = "+"'"+long_desc+"'";
    	Cursor cursor = mDb.rawQuery(selectQuery, null);
    	cursor.moveToFirst();
    	int id = cursor.getInt(0);
    	return id;
    }
    
    public int getUserId(String firstName) {
    	String selectQuery = "SELECT  id FROM USER";
    	Cursor cursor = mDb.rawQuery(selectQuery, null);
    	cursor.moveToFirst();
    	int id = cursor.getInt(0);
    	return id;
	}
    
    public Map<String, String> getAllFoodFactors(int id){
    	Map<String, String> dictionary = new HashMap<String, String>();
    	List<String> labels = new ArrayList<String>();
    	String stringId = Integer.toString(id);
    	String columnString;
    	String selectQuery = "SELECT name, units, amount FROM nutrition JOIN nutrient JOIN common_nutrient ON nutrition.food_id = "+"'"+stringId+"'"+"AND nutrition.nutrient_id = nutrient.id AND nutrient.id = common_nutrient.id ORDER BY name" ;
    	Cursor cursor = mDb.rawQuery(selectQuery, null);
    	if (cursor.moveToFirst()) {
            do {
            	columnString = cursor.getString(0);
            	if (columnString.equalsIgnoreCase("Protein") || columnString.equalsIgnoreCase("Carbohydrate, by difference") || columnString.equalsIgnoreCase("Energy") || columnString.equalsIgnoreCase("Total lipid (fat)")){
            			
            			dictionary.put(columnString, cursor.getString(2));
            			
            	}
            	
            } while (cursor.moveToNext());
        }
    	java.util.Collections.sort(labels);
    	cursor.close();
    	return dictionary;
    }
    
    public List<String> getAllLabels(String tableName, int id){
        List<String> labels = new ArrayList<String>();
        String selectQuery;
        // Select All Query
        if (tableName == "food_group"){
        	 selectQuery = "SELECT  name FROM " + tableName;
        }
        else if(tableName == "User"){
        	selectQuery = "SELECT fName FROM " + tableName;
        }
        else if (tableName == "food"){
        	selectQuery = "SELECT  long_desc FROM " + tableName + " WHERE food_group_id = "+"'"+id+"'";
        } else{
        	selectQuery = "SELECT  name FROM " + tableName;
        }
        
        Cursor cursor = mDb.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
            	labels.add(cursor.getString(0));
            	
            } while (cursor.moveToNext());
        }
        
        if (tableName == "food"){
        	java.util.Collections.sort(labels);
        }
 
        // closing connection
        cursor.close();
       // mDb.close();
 
        // returning lables
        
        return labels;
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
     
     public boolean saveDiet( int userId, double carb, double protiens, double fats, double calories, 
    		 String goal) {
    	 try{
    		 ContentValues cv = new ContentValues();
        	 /*cv.put("diet_id", dietId);*/
        	 cv.put("user_id_fk", userId);
        	 cv.put("carbohydrates", carb);
        	 cv.put("fats", fats);
        	 cv.put("protiens", goal);
        	 mDb.insert("Diet", null, cv);

  			Log.d("SaveDiet", "informationsaved");
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

     
     long noOfRowsInMealsTable(){
    	 /*long count;*/
    	 return DatabaseUtils.queryNumEntries(mDb, "Meals");
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
 	
 	public boolean saveMeal(int userId,double carb, double fat,double prot, double cal,int day, int year, int month, String mealName) {
 		try
 		{
 			ContentValues cv = new ContentValues();
 			cv.put("user_id_fk", userId);
 			cv.put("Carb", carb);
 			cv.put("Prot", prot);
 			cv.put("Fat", fat);
 			cv.put("Cal", cal);
 			cv.put("Year", year);
 			cv.put("Day", day);
 			cv.put("Month", month);
 			cv.put("name",mealName);
 			
 			
 			
 			mDb.insert("Meals", null, cv);

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

