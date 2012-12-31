package com.mypackage.idietandroid;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Spinner loadFNameSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       // loadFNameSpinnerData();
        
        
    }
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadFNameSpinnerData();
	}
    
    public void init() {
		this.loadFNameSpinner = (Spinner) findViewById(R.id.loadFNameSpinner);
	}

    public void onClick(View view) {
    	startActivity(new Intent("net.learn2develop.UserSetup"));
		
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void loadFNameSpinnerData() {
        // database handler
		TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open();
    	
    	List<String> lables;
        // Spinner Drop down elements
    	
    		 lables = mDbHelper.getAllLabels("User",0);
    	// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        if (lables.size()!= 0){
        	loadFNameSpinner.setAdapter(dataAdapter);
        }
        	
        
        mDbHelper.close();
    }
    
}
