package com.droid.klo.crawler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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


	private ActionBar actionBar;
	private ICrawlAIDE mAidlStub;

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



		initOptions();
        chekcPref();


        getFragmentManager().beginTransaction().replace(R.id.placeholder,new CrawledLists(),"cl").commit();
		startService(new Intent(this, CrawlerService.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
		bindToService();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
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



    public void chekcPref(){
        SharedPreferences pref = getSharedPreferences("test",this.MODE_PRIVATE);
        Log.v(TAG,"pref: "+pref.getInt(CRAWL_MIN_RATE,0));
    }
	//endregion

	//region AIDL
	private void bindToService(){
		Log.d(TAG,"bindToService");
		//Log.d(TAG,ICrawlAIDE.class.getName());
		//Log.d(TAG,getPackageName());
		//Log.d(TAG,CrawlerService.class.getPackage().toString());
		//Intent i = new Intent(this, CrawlerService.class);
		Intent i = new Intent(this, CrawlerService.class);
		i.setAction("klo.aidl.service");
		//i.setPackage(CrawlerService.class.getPackage().getName());
		bindService(i,mConnection, Context.BIND_AUTO_CREATE);
	}
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG,"onServiceConnected");
			mAidlStub = ICrawlAIDE.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG,"onServiceDisconnected");
		}
	};
	public void updateService(){
		Log.d(TAG,"updateService");
		//bindToService();
		if(mAidlStub != null){
			try {
				mAidlStub.updateServer("something something tralala...");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else{
			//bindToService();
			//updateService();
			Log.d(TAG,"updateService/mAidlStub == null");
		}
	}
	//endregion
}
