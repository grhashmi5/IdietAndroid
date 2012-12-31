package com.mypackage.idietandroid;


import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class UserSetup extends Activity {
	private String bpSpinnerArray[]; 
	User u;
	
	EditText fNameEditText;
	EditText lNameEditText;
	EditText passWordEditText;
	EditText repeatPassWordEditText;
	EditText heightEditText;
	EditText weighEditText;
	RadioGroup sexRadioGroup;
	RadioGroup unitRadioGroup;
	RadioButton metricRadioButton;
	RadioButton americanRadioButton;
	RadioButton maleRadioButton;
	RadioButton femalRadioButton;
	Spinner bpSpinner;
	Spinner sugarSpinner;
	Spinner metabolismSpinner;
	Spinner activityLevelSpinner;
	Button cancelButton;
	Button doneButton;
	DatePicker bdayDatePicker;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.user_setup);
	initComponents();
	
	 
	/* int units = 0;
	 int gender = 1;
	 boolean edit = false;
	 int oage = 0;
	 double oweight = 0.0D;
	 double oheight = 0.0D;*/

	
	bpSpinnerArray = new String[3];
	bpSpinnerArray[0] = "low";
	bpSpinnerArray[1] = "medium";
	bpSpinnerArray[2] ="high";
	  
	}
	
	public void setUser(User u){
		u = this.u;
	}
	
	public User getUser(){
		return this.u;
	}
	private void initComponents(){
		
		fNameEditText = (EditText) findViewById(R.id.firstNameEditText);
		//fNameEditText.setEnabled(false);
		lNameEditText = (EditText) findViewById(R.id.lastNameEditText);
		passWordEditText = (EditText) findViewById(R.id.passwordEditText);
		repeatPassWordEditText = (EditText) findViewById(R.id.repeatPasswordEditText);
		heightEditText = (EditText) findViewById(R.id.heightEditText);
		weighEditText = (EditText) findViewById(R.id.weightEditText);
		metricRadioButton = (RadioButton) findViewById(R.id.metricRadio);
		americanRadioButton = (RadioButton) findViewById(R.id.americanRadio);
		maleRadioButton = (RadioButton) findViewById(R.id.maleRadio);
		femalRadioButton = (RadioButton) findViewById(R.id.femaleRadio);
		bpSpinner =  (Spinner) findViewById(R.id.bpSpinner);
		sugarSpinner = (Spinner) findViewById(R.id.sugarSpinner);
		metabolismSpinner = (Spinner) findViewById(R.id.metabolismSpinner);
		activityLevelSpinner = (Spinner) findViewById(R.id.activityLevelSpinner);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		doneButton = (Button) findViewById(R.id.doneButton);
		sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
		unitRadioGroup = (RadioGroup) findViewById(R.id.unitRadioGroup);
		bdayDatePicker = (DatePicker) findViewById(R.id.datePicker1);
	}
	
	
	
	public void onClickDoneButton(View view){
		
		double weightD = 0.0D;
		double heightD = 0.0D;
		this.u = new User();
		this.u.firstName = "asd";
		this.u.lastName = "aaa";
		this.u.height = 100;
		this.u.weight = 100;
		this.u.bPressure = 1;
		this.u.bSugar = 1;
		this.u.metabolism = 1;
		this.u.activity =1;
		this.u.bday = 1;
		this.u.bmonth = 1;
		this.u.byear = 2012;
		this.u.gender = 0;
		this.u.units = 0;
		/*if (passWordEditText.getText().toString().equalsIgnoreCase(repeatPassWordEditText.getText().toString())){
			this.u.pwd = passWordEditText.getText().toString();
		}
		else{
			Utility.ShowMessageBox(this, "Password and repeat password do not match");
			return;
		}
		
		if(fNameEditText.getText().toString().trim().length() == 0  
	    		|| heightEditText.getText().toString().trim().length() == 0
	    		|| weighEditText.getText().toString().trim().length() == 0
	    		|| lNameEditText.getText().toString().trim().length() == 0
	    		
				){
	    	Utility.ShowMessageBox(this, "Please fill all the fields");
	    	return;
	    }
		
		
		this.u.firstName = fNameEditText.getText().toString();
        this.u.lastName = lNameEditText.getText().toString();
        this.u.height = Double.valueOf(heightEditText.getText().toString());
        this.u.weight = Double.valueOf(weighEditText.getText().toString());
        this.u.bPressure = bpSpinner.getSelectedItemPosition();
        this.u.bSugar = sugarSpinner.getSelectedItemPosition();
        this.u.metabolism = metabolismSpinner.getSelectedItemPosition();
        this.u.activity = activityLevelSpinner.getSelectedItemPosition();
        this.u.bday = bdayDatePicker.getDayOfMonth();
        this.u.bmonth = bdayDatePicker.getMonth();
        this.u.byear = bdayDatePicker.getYear();
        
        if (maleRadioButton.isChecked()){
        	this.u.gender = 0;//0 for male
        }
        else{
        	this.u.gender = 1;
        }
        
        if (americanRadioButton.isSelected()){
        	this.u.units = 0;
        }
        else{
        	this.u.units = 1;
        }
        
        weightD = this.u.weight;
        if (americanRadioButton.isSelected()){
        	weightD = Utils.round(weightD * 0.45359237D, 1);
        }
        else{
        	weightD = Utils.round(Utils.round(weightD / 0.45359237D, 1) * 0.45359237D, 1);
        }
		heightD = this.u.height;
		if (americanRadioButton.isSelected()){
			heightD = Utils.round(heightD * 2.54D, 1);
		}
		else{
			heightD = Utils.round(Utils.round(heightD / 2.54D, 1) * 2.54D, 1);
		}
		
		 Calendar now = Calendar.getInstance();
		 Calendar bd = Calendar.getInstance();
		 bd.set(this.u.byear, this.u.bmonth, this.u.bday);
		 int age = now.get(1) - bd.get(1);
		 bd.add(1, age);
		 if (now.before(bd)) {
			 age--;
			 
		 }
		 int cals = 0;
		 if (this.u.gender == 0)
		 cals = (int)(66.472999999999999D + 13.750999999999999D * weightD + 5.0033D * heightD - 6.755D * age);
		 else {
			 cals = (int)(655.09550000000002D + 9.462999999999999D * weightD + 1.8496D * heightD - 4.6756D * age);
			 
		 }
		 
		 switch (this.activityLevelSpinner.getSelectedItemPosition()) {
		 	case 0:
		 		cals = (int)(cals * 1.2D);
		    break;
		    case 1:
		    	cals = (int)(cals * 1.5D);
		    break;
		    case 2:
		    	cals = (int)(cals * 1.8D);
		    
		 }
		 cals = (int)(cals * 1.5D);
		 this.u.height = heightD;
		 this.u.weight = weightD;
		 this.u.scals = cals;
		 
		 TestAdapter mDbHelper = new TestAdapter(this);         
	    	mDbHelper.createDatabase();       
	    	mDbHelper.open();
	    
	    	
	    	
	    	boolean isNewUserInserted = mDbHelper.saveUser(this.u.firstName, this.u.lastName, this.u.pwd, this.u.height,
	    			this.u.weight, this.u.bPressure, this.u.bSugar, this.u.metabolism, this.u.activity,
	    			this.u.byear, this.u.bday, this.u.bmonth, this.u.gender, this.u.units); 
	    	if (isNewUserInserted){
	    		Utility.ShowMessageBox(this, "New User added");
	    		
	    	}
	    	else{
	    		Utility.ShowMessageBox(this, "Try again");
	    	}
	    	
	    	long count = mDbHelper.noOfRowsInTable();
	    	
	    	Utility.ShowMessageBox(this,Long.toString(count) );
	    	mDbHelper.close();*/
	    	
	    	
	    	Intent intent = new Intent(this, DietSetup.class);
	    	ActivitiesBringe.setObject(this.u);
	    	startActivity(intent);
	    	
	    	
	    	/*Intent intent = new Intent(getBaseContext(), DietSetup.class);
	    	intent.putExtra("User", this.u);*/
	    	//startActivity(new Intent("net.learn2develop.DietSetup"));
	}
	
}
