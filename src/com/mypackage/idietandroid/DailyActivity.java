package com.mypackage.idietandroid;




import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;



public class DailyActivity extends TabActivity {
	private TabHost mTabHost;
	
	/*private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}
*/
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily);
		
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost();
		
		// Android tab
		Intent intentFDB = new Intent().setClass(this, FoodDatabaseActivity.class);
		TabSpec tabFDB = tabHost
			.newTabSpec("FoodDataBase")
			.setIndicator("Food Database")
			.setContent(intentFDB);
		
		
		Intent intentMeals = new Intent().setClass(this, Meals.class);
		TabSpec tabMeal = tabHost
			.newTabSpec("Meals")
			.setIndicator("Meals ")
			.setContent(intentMeals);
		
		tabHost.addTab(tabFDB);
		tabHost.addTab(tabMeal);
		tabHost.setCurrentTab(1);
		
		
		/*setupTabHost();
		mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		setupTab(new TextView(this), "Meals");
		setupTab(new TextView(this), "Food Database");
		
		
		Intent intentAndroid = new Intent().setClass(this, FoodDatabaseActivity.class);
		TabSpec tabSpecAndroid = mTabHost.newTabSpec("Android").setContent(intentAndroid);
		mTabHost.addTab(tabSpecAndroid);
		mTabHost.setCurrentTab(0);*/
		  /*.newTabSpec("Android")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_android_config))
		  .setContent(intentAndroid);*/
	}
	
	
/*	private void setupTab(final View view, final String tag) {
		
			    View tabview = createTabView(mTabHost.getContext(), tag);
			        TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new TabContentFactory() {
			        public View createTabContent(String tag) {return view;}
			    });
			        mTabHost.addTab(setContent);
			}
	
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.food_database, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}*/

}
