package com.mypackage.idietandroid;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DietSetup  extends Activity{
	
	double totalCals = 0.0D;
	double carbs;
	double carbsp;
	double fat;
	double fatp;
	double protein;
	double proteinp;
	double cals;
	int diettype = -1;
	boolean edit = false;
	User u;
	Spinner selectDietSpinner;
	
	EditText totalCaloriesEditText;
	
	EditText carbohydratesEditText;
	EditText carbohydratesPEditText;
	CheckBox carbohydratesCheckBox;
	
	
	EditText proteinsEditText;
	EditText proteinsPEditText;
	CheckBox proteinsCheckBox;
	
	EditText fatsEditText;
	EditText fatsPEditText;
	CheckBox fatsCheckBox;
	
	Spinner selectGoalSpinner;
	TextView caloriesSuggestedTextView;
	
	public DietSetup(/*User u*/)
	{
		/*this.edit = edit;
	    if (u.totalCals == 0.0D)
	       this.totalCals = u.scals;
	    else {*/
	     //  this.totalCals = u.totalCals;
	     /*}*/
	     /*this.cals = u.scals;
	     this.carbs = 0.0D;
	     this.carbsp = 0.0D;
	     this.fat = 0.0D;
	     this.fatp = 0.0D;
	     this.protein = 0.0D;
	     this.proteinp = 0.0D;
	     this.u = u;
	     initComponents();
	
	     switch (this.selectGoalSpinner.getSelectedItemPosition()) {
	     case 0:
	    	 this.caloriesSuggestedTextView.setText(new Double(u.scals).toString() + " - suggested");
	       break;
	     case 1:
	    	 this.caloriesSuggestedTextView.setText(new Double((int)u.scals* 0.9D).toString() + " - suggested");
	       break;
	     case 2:
	    	 this.caloriesSuggestedTextView.setText(new Double((int)u.scals* 1.1D).toString() + " - suggested");
	       
	     }*/
	   }
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_setup);
        this.u = (User) ActivitiesBringe.getObject();
        this.totalCals = u.totalCals;
        this.cals = u.scals;
	     this.carbs = 0.0D;
	     this.carbsp = 0.0D;
	     this.fat = 0.0D;
	     this.fatp = 0.0D;
	     this.protein = 0.0D;
	     this.proteinp = 0.0D;
	     initComponents();
	     
	     this.selectGoalSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	    	    @Override
	    	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	    	    	switch (position) {
	    		     case 0:
	    		    	 caloriesSuggestedTextView.setText(new Double(u.scals).toString() + " - calories suggested");
	    		       break;
	    		     case 1:
	    		    	 caloriesSuggestedTextView.setText(new Double((int)u.scals* 1.1D).toString() + "calories - suggested");
	    		       break;
	    		     case 2:
	    		    	 caloriesSuggestedTextView.setText(new Double((int)u.scals* 0.9D).toString() + " -calories suggested");
	    		     }
	    	    	updateFields();
	    	    }

	    	    @Override
	    	    public void onNothingSelected(AdapterView<?> parentView) {
	    	        // your code here
	    	    }

	    	});
	     
	     this.selectDietSpinner.setOnItemSelectedListener(new  OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				//if (selectDietSpinner.isf){
					carbohydratesCheckBox.setSelected(false);
			    	proteinsCheckBox.setSelected(false);
			        fatsCheckBox.setSelected(false);
			        proteinsEditText.setEnabled(true);
			        carbohydratesEditText.setEnabled(true);
			        fatsEditText.setEnabled(true);
			        carbohydratesPEditText.setEnabled(true);
			        proteinsPEditText.setEnabled(true);
			        fatsPEditText.setEnabled(true);
			        if (position!=0){
			        	carbohydratesCheckBox.setChecked(false);
			        	carbohydratesCheckBox.setEnabled(false);
			        	fatsCheckBox.setChecked(false);
			        	fatsCheckBox.setEnabled(false);
			        	proteinsCheckBox.setChecked(false);
			        	proteinsCheckBox.setEnabled(false);
			        	
			        }
			        else{
			        	carbohydratesCheckBox.setEnabled(true);
			        	fatsCheckBox.setEnabled(true);
			        	proteinsCheckBox.setEnabled(true);
			    
			        }
			        diettype = position;
			        
			        //if (selectedItemView.toString().compareToIgnoreCase("Atkins") == 0) {
			        if (position == 1){	
			        carbs = 40.0D;
			        	carbsp = Utils.round(Utils.npercent(totalCals, carbs * 4.0D), 1);
			        	updateFields();
			        	
			        }
			        else if (position == 2) {
			        	carbsp = 50.0D;
			        	proteinp = 40.0D;
			        	fatp = 10.0D;
			        	protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
			        	carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
			        	fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
			        	updateFields();
			        	
			        } 
			        else if (position == 3 || position == 6) {
			        	proteinp = 22.5D;
			        	carbsp = 55.0D;
			        	fatp = 22.5D;
			        	protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
			        	carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
			        	fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
			            updateFields();
			            
			        } 
			        else if (position == 4) {
			        	fatp = 10.0D;
			        	fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
			        	updateFields();
			        	
			        } 
			        else if (position == 5) {
			        	fatp = 30.0D;
			        	proteinp = 30.0D;
			        	carbsp = 40.0D;
			        	protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
			        	carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
			        	fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
			        	updateFields();
		        	}	
			        
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				updateFields();
			}
		});
	     
	     
	     this.proteinsPEditText.setOnFocusChangeListener(new OnFocusChangeListener() {          

	         public void onFocusChange(View v, boolean hasFocus) {
	             if(!hasFocus){
	            	 try {
	            		 /*  565 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
	            		 /*      */     }
	            		 /*      */     catch (NumberFormatException numberFormatException) {
	            		 /*      */     }
	            		 /*      */     try {
	            		 /*  570 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
	            		 /*      */     }
	            		 /*      */     catch (NumberFormatException numberFormatException) {
	            		 /*      */     }
	            		 /*      */     try {
	            		 /*  575 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
	            		 /*      */     }
	            		 /*      */     catch (NumberFormatException numberFormatException) {
	            		 /*      */     }
	            		 /*      */     try {
	            		 /*  580 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
	            		 /*      */     }
	            		 /*      */     catch (NumberFormatException numberFormatException) {
	            		 /*      */     }
	            		 /*  584 */     if (totalCals != 0.0D) {
	            		 /*  585 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
	            		 /*  586 */         carbohydratesPEditText.setEnabled(false);
	            		 /*      */       }
	            		 /*  588 */       if (carbsp + fatp + proteinp != 100.0D) {
	            		 /*  589 */         if ((carbohydratesPEditText.isEnabled()) && (fatsPEditText.isEnabled())) {
	            		 /*  590 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
	            		 /*  591 */           carbsp = Utils.round(carbsp + diff * 3.0D, 1);
	            		 /*  592 */           fatp = Utils.round(fatp + diff * 1.0D, 1);
	            		 /*  593 */         } else if (carbohydratesPEditText.isEnabled()) {
	            		 /*  594 */           carbsp = (100.0D - proteinp - fatp);
	            		 /*  595 */         } else if (fatsPEditText.isEnabled()) {
	            		 /*  596 */           fatp = (100.0D - proteinp - carbsp);
	            		 /*      */         }
	            		 /*  598 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
	            		 /*  599 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
	            		 /*  600 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
	            		 /*      */       }
	            		 /*      */ 
	            		 /*  603 */       
	            		 /*      */     }
	            		 updateFields();
	             } 
	         }
	     });
	     
	     this.carbohydratesPEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus){
					try {
						/*  786 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  791 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  796 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  801 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
						/*      */     } catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  805 */       protein = Double.parseDouble(proteinsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  810 */       carbs = Double.parseDouble(carbohydratesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  815 */       fat = Double.parseDouble(fatsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						if (totalCals != 0.0D) {
							/*  644 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
							/*  645 */         totalCals = Utils.round(Utils.ppercent(carbs * 4.0D, carbsp), 1);
							/*  646 */         totalCaloriesEditText.setText(new Double(totalCals).toString());
							/*      */       }
							/*  648 */       if (carbsp + fatp + proteinp != 100.0D) {
							/*  649 */         if ((proteinsPEditText.isEnabled()) && (fatsPEditText.isEnabled())) {
							/*  650 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
							/*  651 */           proteinp = Utils.round(proteinp + diff * 3.0D, 1);
							/*  652 */           fatp = Utils.round(fatp + diff * 1.0D, 1);
							/*  653 */         } else if (proteinsPEditText.isEnabled()) {
							/*  654 */           proteinp = (100.0D - carbsp - fatp);
							/*  655 */         } else if (fatsPEditText.isEnabled()) {
							/*  656 */           fatp = (100.0D - proteinp - carbsp);
							/*      */         }
							/*  658 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
							/*  659 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
							/*  660 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
							/*      */       }
							/*  662 */      updateFields();
							/*      */     }
						 
				}
				
			}
		});
	     
	     this.fatsPEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus){
					try {
						/*  786 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  791 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  796 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  801 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
						/*      */     } catch (NumberFormatException numberFormatException) {
						/*      */     }
						if (totalCals != 0.0D) {
							/*  688 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
							/*  689 */         carbohydratesPEditText.setEnabled(false);
							/*      */       }
							/*  691 */       if (carbsp + fatp + proteinp != 100.0D) {
							/*  692 */         if ((carbohydratesPEditText.isEnabled()) && (proteinsPEditText.isEnabled())) {
							/*  693 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
							/*  694 */           carbsp = Utils.round(carbsp + diff * 2.0D, 1);
							/*  695 */           proteinp = Utils.round(proteinp + diff * 2.0D, 1);
							/*  696 */         } else if (carbohydratesPEditText.isEnabled()) {
							/*  697 */           carbsp = (100.0D - proteinp - fatp);
							/*  698 */         } else if (proteinsPEditText.isEnabled()) {
							/*  699 */           proteinp = (100.0D - fatp - carbsp);
							/*      */         }
							/*  701 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
							/*  702 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
							/*  703 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
							/*      */       }
							/*      */ 
							/*  706 */       updateFields();
							}
					
				}
				
			}
		});
	     
	     this.totalCaloriesEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus){
					try {
						totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
					}
					catch (NumberFormatException numberFormatException) {
				    }
				    try {
				    	 proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
			    	 }
				     catch (NumberFormatException numberFormatException)
				     {
				     }
				     try {
				       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
				     }
				     catch (NumberFormatException numberFormatException)
				     {
				     }
				     try {
				       fatp = Double.parseDouble(fatsPEditText.getText().toString());
				     }
				     catch (NumberFormatException numberFormatException)
				     {
				     }
				     if (totalCals != 0.0D)
				     {
				    	 if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
					         carbs = 40.0D;
					         carbsp = Utils.round(Utils.npercent(totalCals, carbs * 4.0D), 1);
				    	 } else {
					         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
				    	 }
				    	 protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
				    	 fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
				    	 updateFields();
				    	 
				     }
				}
			}
		});
	     
	     this.proteinsEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus){
					try {
						/*  786 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  791 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  796 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  801 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
						/*      */     } catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  805 */       protein = Double.parseDouble(proteinsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  810 */       carbs = Double.parseDouble(carbohydratesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  815 */       fat = Double.parseDouble(fatsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*  819 */     if (totalCals != 0.0D) {
						/*  820 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
						/*  821 */         proteinp = Utils.round(Utils.npercent(totalCals, protein * 4.0D), 1);
						/*  822 */         fatp = (100.0D - carbsp - proteinp);
						/*  823 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
						/*  824 */       } else if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("The Pritikin Principle") == 0) {
						/*  825 */         proteinp = Utils.round(Utils.npercent(totalCals, protein * 4.0D), 1);
						/*  826 */         carbs = Utils.round((totalCals - protein * 4.0D - fat * 9.0D) / 4.0D, 1);
						/*  827 */         carbsp = Utils.round(Utils.npercent(totalCals, carbs * 4.0D), 1);
						/*  828 */       } else if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Custom Diet") == 0) {
						/*  829 */         proteinp = Utils.round(Utils.npercent(totalCals, protein * 4.0D), 1);
						/*  830 */         if ((carbohydratesPEditText.isEnabled()) && (fatsPEditText.isEnabled())) {
						/*  831 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
						/*  832 */           carbsp = Utils.round(carbsp + diff * 3.0D, 1);
						/*  833 */           fatp = Utils.round(fatp + diff * 1.0D, 1);
						/*  834 */         } else if (carbohydratesPEditText.isEnabled()) {
						/*  835 */           carbsp = (100.0D - proteinp - fatp);
						/*  836 */         } else if (fatsPEditText.isEnabled()) {
						/*  837 */           fatp = (100.0D - proteinp - carbsp);
						/*      */         }
						/*  839 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
						/*  840 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
						/*      */       } else {
						/*  842 */         totalCals = Utils.round(Utils.ppercent(protein * 4.0D, proteinp), 1);
						/*  843 */         totalCaloriesEditText.setText(new Double(totalCals).toString());
						/*  844 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
						/*  845 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
						/*      */       }
						/*  847 */       updateFields();
						/*      */     }
				}
			}
		});
	     
	     this.carbohydratesEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus){
					try {
						/*  786 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  791 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  796 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  801 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
						/*      */     } catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  805 */       protein = Double.parseDouble(proteinsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  810 */       carbs = Double.parseDouble(carbohydratesEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						/*      */     try {
						/*  815 */       fat = Double.parseDouble(fatsEditText.getText().toString());
						/*      */     }
						/*      */     catch (NumberFormatException numberFormatException) {
						/*      */     }
						if (totalCals != 0.0D) {
							/*  887 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("The Pritikin Principle") == 0) {
							/*  888 */         carbsp = Utils.round(Utils.npercent(totalCals, carbs * 4.0D), 1);
							/*  889 */         protein = Utils.round((totalCals - carbs * 4.0D - fat * 9.0D) / 4.0D, 1);
							/*  890 */         proteinp = Utils.round(Utils.npercent(totalCals, protein * 4.0D), 1);
							/*  891 */       } else if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Custom Diet") == 0) {
							/*  892 */         carbsp = Utils.round(Utils.npercent(totalCals, carbs * 4.0D), 1);
							/*  893 */         if ((proteinsPEditText.isEnabled()) && (fatsPEditText.isEnabled())) {
							/*  894 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
							/*  895 */           proteinp = Utils.round(proteinp + diff * 3.0D, 1);
							/*  896 */           fatp = Utils.round(fatp + diff * 1.0D, 1);
							/*  897 */         } else if (proteinsPEditText.isEnabled()) {
							/*  898 */           proteinp = (100.0D - carbsp - fatp);
							/*  899 */         } else if (fatsPEditText.isEnabled()) {
							/*  900 */           fatp = (100.0D - proteinp - carbsp);
							/*      */         }
							/*  902 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
							/*  903 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
							/*      */       } else {
							/*  905 */         totalCals = Utils.round(Utils.ppercent(carbs * 4.0D, carbsp), 1);
							/*  906 */         totalCaloriesEditText.setText(new Double(totalCals).toString());
							/*  907 */         fat = Utils.round(Utils.percent(totalCals, fatp) / 9.0D, 1);
							/*  908 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
							/*      */       }
							/*  910 */       updateFields();
						}
				}
				
			}
		});
	     
	     this.fatsEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus){
					try{
						
					/*  786 */       totalCals = Double.parseDouble(totalCaloriesEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  791 */       proteinp = Double.parseDouble(proteinsPEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  796 */       carbsp = Double.parseDouble(carbohydratesPEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  801 */       fatp = Double.parseDouble(fatsPEditText.getText().toString());
				/*      */     } catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  805 */       protein = Double.parseDouble(proteinsEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  810 */       carbs = Double.parseDouble(carbohydratesEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				/*      */     try {
				/*  815 */       fat = Double.parseDouble(fatsEditText.getText().toString());
				/*      */     }
				/*      */     catch (NumberFormatException numberFormatException) {
				/*      */     }
				if (totalCals != 0.0D) {
					/*  950 */       if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
					/*  951 */         fatp = Utils.round(Utils.npercent(totalCals, fat * 4.0D), 1);
					/*  952 */         proteinp = (100.0D - carbsp - fatp);
					/*  953 */         protein = Utils.round(Utils.percent(totalCals, protein) / 4.0D, 1);
					/*  954 */       } else if (selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Custom Diet") == 0) {
					/*  955 */         fatp = Utils.round(Utils.npercent(totalCals, fat * 9.0D), 1);
					/*  956 */         if ((carbohydratesPEditText.isEnabled()) && (proteinsPEditText.isEnabled())) {
					/*  957 */           double diff = (100.0D - proteinp - carbsp - fatp) / 4.0D;
					/*  958 */           carbsp = Utils.round(carbsp + diff * 2.0D, 1);
					/*  959 */           proteinp = Utils.round(proteinp + diff * 2.0D, 1);
					/*  960 */         } else if (carbohydratesPEditText.isEnabled()) {
					/*  961 */           carbsp = (100.0D - proteinp - fatp);
					/*  962 */         } else if (proteinsPEditText.isEnabled()) {
					/*  963 */           proteinp = (100.0D - fatp - carbsp);
					/*      */         }
					/*  965 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
					/*  966 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
					/*      */       } else {
					/*  968 */         totalCals = Utils.round(Utils.ppercent(fat * 9.0D, fatp), 1);
					/*  969 */         totalCaloriesEditText.setText(new Double(totalCals).toString());
					/*  970 */         carbs = Utils.round(Utils.percent(totalCals, carbsp) / 4.0D, 1);
					/*  971 */         protein = Utils.round(Utils.percent(totalCals, proteinp) / 4.0D, 1);
					/*      */       }
					/*  973 */       updateFields();
					/*      */     }
					
					
				}
			}
		});
	     
	     this.proteinsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					proteinsPEditText.setEnabled(false);
				}
				else{
					proteinsPEditText.setEnabled(true);
				}
				
			}
		});
	     
	     this.carbohydratesCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked){
						carbohydratesPEditText.setEnabled(false);
					}
					else{
						carbohydratesPEditText.setEnabled(true);
					}
					
				}
			});
	     
	     this.fatsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked){
						fatsPEditText.setEnabled(false);
					}
					else{
						fatsPEditText.setEnabled(true);
					}
					
				}
			});
	     
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	private void initComponents (){
		selectDietSpinner = (Spinner)findViewById(R.id.selectDietSpinner);
		totalCaloriesEditText = (EditText) findViewById(R.id.totalCaloriesEditText);
		carbohydratesEditText = (EditText) findViewById(R.id.carbohydratesGramEditText);
		carbohydratesPEditText = (EditText) findViewById(R.id.carbohydratesPercentEditText);
		carbohydratesCheckBox = (CheckBox) findViewById(R.id.carbohydratesCheckBox);
		proteinsEditText = (EditText) findViewById(R.id.proteinsGramEditText);
		proteinsPEditText = (EditText) findViewById(R.id.proteinsPercentEditText);
		proteinsCheckBox = (CheckBox) findViewById(R.id.proteinsCheckBox);
		fatsEditText = (EditText) findViewById(R.id.fatsGramEditText);
		fatsPEditText = (EditText) findViewById(R.id.fatsPercentEditText);
		fatsCheckBox = (CheckBox) findViewById(R.id.fatsCheckBox);
		selectGoalSpinner = (Spinner) findViewById(R.id.selectGoalSpinner);
		caloriesSuggestedTextView = (TextView) findViewById(R.id.caloriesSuggestedTextView);
		
	}
	
	public void updateFields()
	{
	     if (this.diettype > -1) {
	       this.selectDietSpinner.setSelection(this.diettype);
	    	 //this.selectDietSpinner.setSelectedIndex(this.diettype);
	     }
	     if (this.diettype != 0) {
	    	 this.carbohydratesCheckBox.setSelected(false);
	    	 this.proteinsCheckBox.setSelected(false);
	         this.fatsCheckBox.setSelected(false);
	         this.proteinsEditText.setEnabled(true);
	         this.carbohydratesEditText.setEnabled(true);
	         this.fatsEditText.setEnabled(true);
	         this.carbohydratesPEditText.setEnabled(true);
	         this.proteinsPEditText.setEnabled(true);
	         this.fatsPEditText.setEnabled(true);
	       
	     }
	     this.carbohydratesEditText.setText(new Double(this.carbs).toString());
	     this.fatsEditText.setText(new Double(this.fat).toString());
	     this.proteinsEditText.setText(new Double(this.protein).toString());
	     this.carbohydratesPEditText.setText(new Double(this.carbsp).toString());
	     this.fatsPEditText.setText(new Double(this.fatp).toString());
	     this.proteinsPEditText.setText(new Double(this.proteinp).toString());
	     if (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
	    
	       this.carbohydratesEditText.setEnabled(false);
	     } 
	     else if ((this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Jenny Craig") == 0) || (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Body for Life") == 0) || (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("The Zone") == 0) || (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("USDA") == 0))
	     {
	       this.carbohydratesPEditText.setEnabled(false);
	       this.proteinsPEditText.setEnabled(false);
	       this.fatsPEditText.setEnabled(false);
	     } else if (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("The Pritikin Principle") == 0) {
	       this.fatsPEditText.setEnabled(false);
	       
	     }
	     
	}
	
	
	private void CalFieldActionPerformed() {
		try {
			this.totalCals = Double.parseDouble(this.totalCaloriesEditText.getText().toString());
	       }
	       catch (NumberFormatException numberFormatException) {
	       }
	       try {
	         this.proteinp = Double.parseDouble(this.proteinsPEditText.getText().toString());
	       }
	       catch (NumberFormatException numberFormatException)
	       {
	       }
	       try {
	         this.carbsp = Double.parseDouble(this.carbohydratesPEditText.getText().toString());
	       }
	       catch (NumberFormatException numberFormatException)
	       {
	       }
	       try {
	         this.fatp = Double.parseDouble(this.fatsPEditText.getText().toString());
	       }
	       catch (NumberFormatException numberFormatException)
	       {
	       }
	       if (this.totalCals != 0.0D)
	       {
	         if (this.selectDietSpinner.getSelectedItem().toString().compareToIgnoreCase("Atkins") == 0) {
	           this.carbs = 40.0D;
	           this.carbsp = Utils.round(Utils.npercent(this.totalCals, this.carbs * 4.0D), 1);
	         } else {
	           this.carbs = Utils.round(Utils.percent(this.totalCals, this.carbsp) / 4.0D, 1);
	         }
	         this.protein = Utils.round(Utils.percent(this.totalCals, this.proteinp) / 4.0D, 1);
	         this.fat = Utils.round(Utils.percent(this.totalCals, this.fatp) / 9.0D, 1);
	   
	         updateFields();
		       }
	       }
	
	
	
	
	}
