package com.droid.klo.crawler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.droid.klo.crawler.db.Dao;
import com.droid.klo.crawler.service.CrawlerService;


public class MainActivity extends AppCompatActivity {

	//region variables
	private static final String TAG = "MainActivity";

	//options
	private static final String FIRST_RUN = "initialized";//Bool, special
	private static final String SERVICE_SHOULD_RUN = "serviceShouldRun"; //bool, DEFAULT=true
	private static final String ONLY_WIFI = "only_wifi_mode"; //bool, DEFAULT=false
	private static final String CRAWL_MIN_RATE = "rate_of_crawl";//int, minutes, DEFAULT=5
	private static final String CLEAN_DATA_TIME = "cleaning_time_of_day";//int, start hour, mode = 24 hours, DEFAULT=3
	private static final String SAVE_DATA_FOR_NUMBER_OF_DAYS = "save_for_days";//int, days, DEFAULT=1


	ActionBar actionBar;
	Dao dao;
	//endregion

	//region Overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
		actionBar=getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);


		dao = new Dao(this);
		//dao.open();

		initOptions();
        chekcPref();


        getFragmentManager().beginTransaction().replace(R.id.placeholder,new CrawledLists(),"cl").commit();
		startService(new Intent(this, CrawlerService.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected");
		if(item.getItemId() == android.R.id.home){
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}
	//endregion

	//region custom methods

	private boolean initOptions(){
		SharedPreferences pref = getSharedPreferences("test",this.MODE_PRIVATE);
		if(pref.getBoolean(FIRST_RUN,false) == false){
			SharedPreferences.Editor editor = pref.edit();
			editor.putBoolean(SERVICE_SHOULD_RUN, true);
			editor.putBoolean(ONLY_WIFI,false);
			editor.putInt(CRAWL_MIN_RATE,5);
			editor.putInt(CLEAN_DATA_TIME,3);
			editor.putInt(SAVE_DATA_FOR_NUMBER_OF_DAYS,1);
			editor.putBoolean(FIRST_RUN,true);
            editor.commit();
			return true;
		}else return true;

	}
	public void addBackButton(){
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	public void dissableToolbarBack(){
		//actionBar.setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
	public Dao getDao(){
		return this.dao;
	}


    public void chekcPref(){
        SharedPreferences pref = getSharedPreferences("test",this.MODE_PRIVATE);
        Log.v(TAG,"pref: "+pref.getInt(CRAWL_MIN_RATE,0));
    }
	//endregion
}
