package com.mypackage.idietandroid;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Spinner loadFNameSpinner;
	EditText loginUserPasswordEditText;

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
		this.loginUserPasswordEditText = (EditText) findViewById(R.id.editText1);
	}

    public void onClick(View view) {
    	startActivity(new Intent("net.learn2develop.UserSetup"));
		
	}
    
    public void loginPressed(View view){
    	if (loginUserPasswordEditText.getText().toString().trim().length() == 0){
    		Utility.ShowMessageBox(this, "please enter password");
    	} else{
    		TestAdapter mDbHelper = new TestAdapter(this);         
        	mDbHelper.createDatabase();       
        	mDbHelper.open();
        	//mDbHelper.isUserAlreadyExist(loadFNameSpinner.getSelectedItem().toString());
        	boolean isPasswordCorrect = mDbHelper.loginUser(loadFNameSpinner.getSelectedItem().toString(),loginUserPasswordEditText.getText().toString() );
        	if (isPasswordCorrect){
        		Intent intent = new Intent(this, DailyActivity.class);
        		User user = mDbHelper.getUser(loadFNameSpinner.getSelectedItem().toString());
        		
        		ActivitiesBringe.setObject(user);
        		startActivity(intent);
        	} else{
        		Utility.ShowMessageBox(this, "please enter correct password");
        	}
    	}
    	
    	
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
