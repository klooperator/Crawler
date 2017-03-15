package com.droid.klo.crawler.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by prpa on 3/15/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    //region class variables
    private static final String TAG = "DBHelper";

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "klo_crawler_db";



    //TABLE_SOURCE columns
    private static final String CS_ID = "_id";
    private static final String CS_NAME = "source_name"; //text
    private static final String CS_LINK = "source_link"; //text
    private static final String CS_TOP_VALUE = "source_top_value"; //int
    private static final String CS_BOTTOM_VALUE = "source_bottom_value"; //int

    //TABLE_RESULTS columns
    private static final String CR_ID = "_id"; //int autoincrement
    private static final String CR_SOURCE_ID = "_source_id"; //int foreign key
    private static final String CR_PHONE_NUMBER = "result_phone_number"; // text
    private static final String CR_TITLE = "result_title";//text
    private static final String CR_CONTENT = "result_content"; //text
    private static final String CR_PRICE = "result_price"; //int
    private static final String CR_TIME = "result_time"; //text



    public DBHelper(Context context) {
        super(context, DB_NAME, null,  DB_VERSION);
    }
    //endregion

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate()");
        try {
            db.execSQL(Source.CREATE_TABLE_SOURCE);
            db.execSQL(Result.CREATE_TABLE_RESULTS);
        }catch (SQLException e){
            Log.e(TAG,"error: "+ e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "onUpgrade DB");
    }


}
