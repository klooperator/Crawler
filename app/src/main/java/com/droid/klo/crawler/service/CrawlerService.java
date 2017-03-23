package com.droid.klo.crawler.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.droid.klo.crawler.ICrawlAIDE;
import com.droid.klo.crawler.contentProvider.DaoCP;
import com.droid.klo.crawler.db.Source;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by prpa on 3/16/17.
 */

public class CrawlerService extends Service {

    //region variables
    public static final String TAG = "CrawlerService";

    private Handler handler;
    private int runCounter;
    private Runnable runnable;

    private List<String> excluded;
    private List<Source> sourceList;
    private DaoCP dao;
    private int crawlRate;
    private SharedPreferences pref;

    //options
    private static final String FIRST_RUN = "initialized";//Bool, special
    private static final String SERVICE_SHOULD_RUN = "serviceShouldRun"; //bool, DEFAULT=true
    private static final String ONLY_WIFI = "only_wifi_mode"; //bool, DEFAULT=false
    private static final String CRAWL_MIN_RATE = "rate_of_crawl";//int, minutes, DEFAULT=5
    private static final String CLEAN_DATA_TIME = "cleaning_time_of_day";//int, start hour, mode = 24 hours, DEFAULT=3
    private static final String SAVE_DATA_FOR_NUMBER_OF_DAYS = "save_for_days";//int, days, DEFAULT=1


    //endregion

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        runCounter = 0;
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        pref = getApplicationContext().getSharedPreferences("test",getApplicationContext().MODE_PRIVATE);

        DaoCP dao = new DaoCP(this);
        List<Source> sources = dao.getSources();
        for(Iterator<Source> i = sources.iterator(); i.hasNext();){
            Source s = i.next();
            Log.v(TAG, s.getName());
        }


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "run n. " + runCounter);
                runCounter++;
                JSoupMain js = new JSoupMain("http://www.njuskalo.hr/iphone-7-plus",getApplicationContext());
                try {
                    js.parse();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(runCounter<2)handler.postDelayed(this, 10000);
            }

        };
        handler.post(runnable);

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void updateServis(String s){
        Log.d(TAG, "AIDL = "+ s);
        runCounter=0;
        handler.post(runnable);
    }

    private final ICrawlAIDE.Stub mBinder = new ICrawlAIDE.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void updateServer(String s) throws RemoteException {
            Log.d("ICrawlAIDE/updateServer", "received: " + s);
            updateServis(s);
        }
    };


}
