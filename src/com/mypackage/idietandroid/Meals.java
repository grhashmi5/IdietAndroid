package com.mypackage.idietandroid;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class Meals extends Activity {
	DatePicker mealDatePicker;
	Calendar calender =Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.meal);
        init();
	}
	
	private void init() {
		mealDatePicker = (DatePicker) findViewById(R.id.mealDatePicker);
		mealDatePicker.init(calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Log.e("asd", "asd");
			}
		});
	}
}
