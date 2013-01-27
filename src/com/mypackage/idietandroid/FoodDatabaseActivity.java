package com.mypackage.idietandroid;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class FoodDatabaseActivity  extends Activity implements OnItemSelectedListener{
	Spinner selectFoodGroupSpinner;
	Spinner selectFoodSpinner;
	Spinner selectMealSpinner;
	EditText addAmountEditText;
	EditText carbEditText;
	EditText proteinEditText;
	EditText fatsEditText;
	EditText caloriesEditText;
	List<String> mealSpinnerList;
	DatePicker mealDAtePicker;
	private ArrayAdapter<String> mealSpinnerAdapter;
	User u;
	
	
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
		
		selectMealSpinner = (Spinner) findViewById(R.id.selectMealNameSpinner);
		selectMealSpinner.setOnItemSelectedListener(this);
		
		loadSpinnerData(selectFoodGroupSpinner.getId());
		loadSpinnerData(selectMealSpinner.getId());
		
		addAmountEditText = (EditText) findViewById(R.id.addAmountEditText);
		addAmountEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					loadFoodFactors();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// TODO Auto-generated method stub
				
			}
		});
	/*	addAmountEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus){
					try{
						loadFoodFactors();
					} catch(NumberFormatException numberFormatException) {
           		            }
				}
				
				
			}
		});
*/		
		carbEditText = (EditText) findViewById(R.id.carbEditText);
		proteinEditText = (EditText) findViewById(R.id.protiensEditText);
		fatsEditText = (EditText) findViewById(R.id.fatsEditText);
		caloriesEditText = (EditText) findViewById(R.id.calsEditText);
		
		mealDAtePicker = (DatePicker) findViewById(R.id.selectMealDatePicker);
		
		this.u = (User) ActivitiesBringe.getObject();
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
			this.addAmountEditText.setText("100");
			loadFoodFactors();
			break;
			
		case R.id.selectMealNameSpinner:
			if (selectMealSpinner.getSelectedItem().toString() == "New Meal"){
				LayoutInflater li = LayoutInflater.from(this);
				View promptsView = li.inflate(R.layout.add_meal_popup, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);
				alertDialogBuilder.setView(promptsView);
				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.addMealNameEditTextDialog);
				
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					// get user input and set it to result
					// edit text
				    	//Spinner spinner = (Spinner)findViewById(selectMealSpinner);

				    	mealSpinnerAdapter	= (ArrayAdapter<String>) selectMealSpinner.getAdapter();
				    	mealSpinnerList.add(userInput.getText().toString());
				    	mealSpinnerAdapter.notifyDataSetChanged();
				    	selectMealSpinner.setSelection(mealSpinnerList.size()-1);
				    	/*ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
				    	spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    	spinner.setAdapter(spinnerAdapter);
				    	spinnerAdapter.add("value");
				    	spinnerAdapter.notifyDataSetChanged();*/
				    	//result.setText(userInput.getText());
				    }
				  })
				.setNegativeButton("Cancel",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
			}
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
    	
    	Map<String, String> dictionary;
    	int id = mDbHelper.getFoodId(selectFoodSpinner.getSelectedItem().toString());
    	dictionary = mDbHelper.getAllFoodFactors(id);
    	double updatedValue;
    	double multipLicationFactor =Double.valueOf( addAmountEditText.getText().toString());
    	
    	// result = mDbHelper.getFactorFromFood("protein_factor", selectFoodSpinner.getSelectedItem().toString());
    	if (dictionary.containsKey("Protein")){
    		updatedValue = (Double.valueOf(dictionary.get("Protein")) /100) * multipLicationFactor;
    		
    		proteinEditText.setText(Double.toString(updatedValue));
    	}
    	//result = mDbHelper.getFactorFromFood("fat_factor", selectFoodSpinner.getSelectedItem().toString());
    	
    	if (dictionary.containsKey("Total lipid (fat)")){
    		updatedValue = (Double.valueOf(dictionary.get("Total lipid (fat)")) /100) * multipLicationFactor;
    		fatsEditText.setText(Double.toString(updatedValue));
    	}
    	//result = mDbHelper.getFactorFromFood("calorie_factor", selectFoodSpinner.getSelectedItem().toString());
    	if (dictionary.containsKey("Energy")){
    		updatedValue = (Double.valueOf(dictionary.get("Energy")) /100) * multipLicationFactor;
    		caloriesEditText.setText(Double.toString(updatedValue));
    	}
    	if (dictionary.containsKey("Carbohydrate, by difference")){
    		updatedValue = (Double.valueOf(dictionary.get("Carbohydrate, by difference")) /100) * multipLicationFactor;
    		carbEditText.setText(Double.toString(updatedValue));
    	}
    	mDbHelper.close();
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
    	else if (id == selectFoodSpinner.getId()){
    		foodGroupId = selectFoodGroupSpinner.getSelectedItemPosition()+1 ;
			 foodGroupId *=100;
    		 lables = mDbHelper.getAllLabels("food",foodGroupId);
    	}
    	else {
    		//List<String> temp = null;
    		lables = mDbHelper.getAllLabels("Meals", -2);
    		
    		lables.add("New Meal");
    		mealSpinnerList = lables;
    		/*int length = temp.size();
    		for (int i = length; i>1;i--){
    			
    		}*/
    		
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
        else if (id == selectFoodSpinner.getId()){
        	selectFoodSpinner.setAdapter(dataAdapter);
        }
        else {
        	selectMealSpinner.setAdapter(dataAdapter);
        	
        }
        mDbHelper.close();
    }
	
	public void addMeal(View view) {
		
		
		
		if(   carbEditText.getText().toString().trim().length() == 0
	    		|| proteinEditText.getText().toString().trim().length() == 0
	    		|| fatsEditText.getText().toString().trim().length() == 0
	    		|| caloriesEditText.getText().toString().trim().length() == 0){
	    	Utility.ShowMessageBox(this, "Please fill all the fields");
	    	return;
	    }
		else {
			TestAdapter mDbHelper = new TestAdapter(this);         
	    	mDbHelper.createDatabase();       
	    	mDbHelper.open();
	    	int id = mDbHelper.getUserId(this.u.firstName);
	    	double carb =Double.valueOf( carbEditText.getText().toString());
	    	double fat =Double.valueOf( fatsEditText.getText().toString());
	    	double prot =Double.valueOf( proteinEditText.getText().toString());
	    	double cal =Double.valueOf( caloriesEditText.getText().toString());
	    	int day = mealDAtePicker.getDayOfMonth();
	    	int month = mealDAtePicker.getMonth();
	    	int year = mealDAtePicker.getYear();
	    	String mealName = selectMealSpinner.getSelectedItem().toString();
	    	boolean isNewMealAdded = mDbHelper.saveMeal(id,carb, fat, prot, cal, day, year, month, mealName);
	    	if (isNewMealAdded){
	    		Utility.ShowMessageBox(this, "New Meal added");
	    		
	    		
	    	}
	    	else{
	    		Utility.ShowMessageBox(this, "Try again");
	    	}
	    	long count = mDbHelper.noOfRowsInMealsTable();
	    	
	    	Utility.ShowMessageBox(this,Long.toString(count) );
	    	
		}
	}
	

}
