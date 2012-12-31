package com.mypackage.idietandroid;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FoodDatabaseActivity  extends Activity implements OnItemSelectedListener{
	Spinner selectFoodGroupSpinner;
	Spinner selectFoodSpinner;
	EditText addAmountEditText;
	EditText carbEditText;
	EditText proteinEditText;
	EditText fatsEditText;
	EditText caloriesEditText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_database);
		init();
		
	}
	
	private void init() {
		selectFoodGroupSpinner = (Spinner) findViewById(R.id.selectFoodGroupSpinner);
		selectFoodGroupSpinner.setOnItemSelectedListener(this);
		selectFoodSpinner = (Spinner) findViewById(R.id.selectFoodSpinner);
		selectFoodSpinner.setOnItemSelectedListener(this);
		loadSpinnerData(selectFoodGroupSpinner.getId());
		addAmountEditText = (EditText) findViewById(R.id.addAmountEditText);
		
		carbEditText = (EditText) findViewById(R.id.carbEditText);
		proteinEditText = (EditText) findViewById(R.id.protiensEditText);
		fatsEditText = (EditText) findViewById(R.id.fatsEditText);
		caloriesEditText = (EditText) findViewById(R.id.calsEditText);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
		int id = parent.getId();
		
		switch (id)
		{
		case R.id.selectFoodGroupSpinner:
			 
			 loadSpinnerData(R.id.selectFoodSpinner);
			break;
		
		case R.id.selectFoodSpinner:
			loadFoodFactors();
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadFoodFactors(){
		TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open();
    	String result = mDbHelper.getFactorFromFood("protein_factor", selectFoodSpinner.getSelectedItem().toString());
    	proteinEditText.setText(result);
    	result = mDbHelper.getFactorFromFood("fat_factor", selectFoodSpinner.getSelectedItem().toString());
    	fatsEditText.setText(result);
    	result = mDbHelper.getFactorFromFood("calorie_factor", selectFoodSpinner.getSelectedItem().toString());
    	caloriesEditText.setText(result);
    	//carbEditText.setText(10);
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
