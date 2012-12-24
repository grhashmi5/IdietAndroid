package com.mypackage.idietandroid;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FoodDatabaseActivity  extends Activity implements OnItemSelectedListener{
	Spinner selectFoodGroupSpinner;
	Spinner selectFoodSpinner;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_database);
		selectFoodGroupSpinner = (Spinner) findViewById(R.id.selectFoodGroupSpinner);
		selectFoodGroupSpinner.setOnItemSelectedListener(this);
		selectFoodSpinner = (Spinner) findViewById(R.id.selectFoodSpinner);
		selectFoodSpinner.setOnItemSelectedListener(this);
		loadSpinnerData(selectFoodGroupSpinner.getId());
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
		int id = parent.getId();
		int foodGroupId; 
		switch (id)
		{
		case R.id.selectFoodGroupSpinner:
			 
			 loadSpinnerData(R.id.selectFoodSpinner);
			break;
		
		case R.id.selectFoodSpinner:
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadSpinnerData(int id) {
        // database handler
		TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open();
    	int foodGroupId;
    	List<String> lables;
        // Spinner Drop down elements
    	if (id == selectFoodGroupSpinner.getId()){
    		 lables = mDbHelper.getAllLabels("food_group",-1);
    	}
    	else{
    		foodGroupId = selectFoodGroupSpinner.getSelectedItemPosition()+1 ;
			 foodGroupId *=100;
    		 lables = mDbHelper.getAllLabels("food",foodGroupId);
    	}
        
 
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        if (id == selectFoodGroupSpinner.getId()){
        	selectFoodGroupSpinner.setAdapter(dataAdapter);
        }
        else{
        	selectFoodSpinner.setAdapter(dataAdapter);
        }
        mDbHelper.close();
    }
	

}
