package com.mypackage.idietandroid;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;

public class Meals extends Activity implements OnItemSelectedListener {
	DatePicker mealDatePicker;
	Calendar calender =Calendar.getInstance();
	Spinner selectMealSpinner;
	EditText carbEditText;
	EditText proteinEditText;
	EditText fatsEditText;
	EditText caloriesEditText;
	User u;
	List<String> mealSpinnerList;
	private ArrayAdapter<String> mealSpinnerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meal);
		init();
	}

	private void init() {
		carbEditText = (EditText) findViewById(R.id.carbEditText);
		carbEditText.setEnabled(false);
		
		proteinEditText = (EditText) findViewById(R.id.protiensEditText);
		proteinEditText.setEnabled(false);
		
		fatsEditText = (EditText) findViewById(R.id.fatsEditText);
		fatsEditText.setEnabled(false);
		
		caloriesEditText = (EditText) findViewById(R.id.calsEditText);
		caloriesEditText.setEnabled(false);
		
		selectMealSpinner = (Spinner) findViewById(R.id.selectMealNameSpinner);
		selectMealSpinner.setOnItemSelectedListener(this);
		this.u = (User) ActivitiesBringe.getObject();
		ActivitiesBringe.setObject(u);
		loadSpinnerData();

		
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
		
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();
		int userId = mDbHelper.getUserId(this.u.firstName);
		String mealName = selectMealSpinner.getSelectedItem().toString();
		List mealDetails = mDbHelper.getMealDetails(userId,mealName);
		carbEditText.setText(mealDetails.get(0).toString());
		proteinEditText.setText(mealDetails.get(1).toString());
		fatsEditText.setText(mealDetails.get(2).toString());
		caloriesEditText.setText(mealDetails.get(3).toString());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


	private void loadSpinnerData() {
		// database handler
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		List<String> lables;

		int userId = mDbHelper.getUserId(this.u.firstName);

		lables = mDbHelper.getAllLabels("Meals", userId);
		if (lables == null){
			lables = new ArrayList<String>();
		}
		mealSpinnerList = lables;


		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner

		selectMealSpinner.setAdapter(dataAdapter);


		mDbHelper.close();

	}
}
