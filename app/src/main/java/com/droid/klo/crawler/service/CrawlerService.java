package com.droid.klo.crawler.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.droid.klo.crawler.db.Dao;
import com.droid.klo.crawler.db.Source;

import java.util.List;

/**
 * Created by prpa on 3/16/17.
 */

public class CrawlerService extends Service {

    //region variables
    public static final String TAG = "CrawlerService";
    private int count;

    Handler handler;

    private List<String> excluded;
    private List<Source> sourceList;
    private Dao dao;
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
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count = 0;
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "run n. " + pref.getInt(CRAWL_MIN_RATE,0));
                handler.postDelayed(this, 10000);
            }

        };
        handler.post(runnable);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
