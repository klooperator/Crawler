package com.droid.klo.crawler.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prpa on 3/15/17.
 */

public class Dao {

    //region globals
    private static final String TAG = "DAO";
    private SQLiteDatabase db;
    private DBHelper myHelper;

    //endregion

    public Dao (Context context){
        Log.d(TAG, "Construct");
        myHelper = new DBHelper(context);
    }

    public void open(){
        Log.d(TAG, "open()");
        db = myHelper.getWritableDatabase();
    }

    public void close(){
        Log.d(TAG, "close()");
        myHelper.close();
    }

    public long insertSource(Source s){
        ContentValues values = new ContentValues();
        values.put(s.NAME, s.getName()); // name
        values.put(s.LINK, s.getLink()); // name
        values.put(s.TOP_VALUE, s.getTop_value()); // name
        values.put(s.BOTTOM_VALUE, s.getBottom_value()); // name


        // Inserting Row
        return db.insert(DBHelper.TABLE_SOURCE_NAME, null, values);
    }

    public long insertResult(Result r){
        ContentValues values = new ContentValues();
        values.put(r.SOURCE_ID, r.getSource_id());
        values.put(r.TITLE, r.getTitle());
        values.put(r.CONTENT, r.getContent());
        values.put(r.PHONE_NUMBER, r.getPhone_number());
        values.put(r.PRICE, r.getPrice());
        values.put(r.TIME, r.getTime());

        return db.insert(DBHelper.TABLE_RESULTS_NAME,null,values);
    }

    public List<Source> getSources(){
        List<Source> s = new ArrayList<Source>();

        Cursor c = db.query(DBHelper.TABLE_SOURCE_NAME, Source.sourceColumns, null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Source temp = new Source();
            temp.setId(c.getLong(c.getColumnIndex(Source.ID)));
            temp.setName(c.getString(c.getColumnIndex(Source.NAME)));
            temp.setLink(c.getString(c.getColumnIndex(Source.LINK)));
            temp.setTop_value(c.getInt(c.getColumnIndex(Source.TOP_VALUE)));
            temp.setBottom_value(c.getInt(c.getColumnIndex(Source.BOTTOM_VALUE)));
            s.add(temp);
            c.moveToNext();
        }
        c.close();
        return s;
    }
}
