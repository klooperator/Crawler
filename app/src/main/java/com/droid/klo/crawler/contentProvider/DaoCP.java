package com.droid.klo.crawler.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.droid.klo.crawler.db.ExcludeUsers;
import com.droid.klo.crawler.db.Result;
import com.droid.klo.crawler.db.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prpa on 3/19/17.
 */

public class DaoCP {
    //region globals
    private static final String TAG = "DaoCP";
    //private CP cp;
    private Context context;

    //endregion

    public DaoCP (Context context){
        Log.d(TAG, "Construct");
        Log.d(TAG, context.toString());
        this.context = context;
        ;
        //cp = context.getContentResolver();
    }

    public void open(){
        Log.d(TAG, "open()");


    }

    public void close(){
        Log.d(TAG, "close()");
    }

    public void insertSource(Source s){
        Log.d(TAG, "insertSource()");
        ContentValues values = new ContentValues();
        values.put(s.NAME, s.getName()); // name
        values.put(s.LINK, s.getLink()); // name
        values.put(s.TOP_VALUE, s.getTop_value()); // name
        values.put(s.BOTTOM_VALUE, s.getBottom_value()); // name
        //cp.insert(cp.URI_SOURCE, values);
        Uri uri = context.getContentResolver().insert(CP.URI_SOURCE, values);


    }

    public void insertResult(Result r){
        Log.d(TAG, "insertResult()");
        ContentValues values = new ContentValues();
        values.put(r.SOURCE_ID, r.getSource_id());
        values.put(r.TITLE, r.getTitle());
        values.put(r.CONTENT, r.getContent());
        values.put(r.PHONE_NUMBER, r.getPhone_number());
        values.put(r.PRICE, r.getPrice());
        values.put(r.TIME, r.getTime());
        Uri uri = context.getContentResolver().insert(CP.URI_RESULT, values);

    }

    public List<Source> getSources(){
        Log.d(TAG, "getSources()");
        List<Source> s = new ArrayList<Source>();
        /*Cursor query (Uri uri,
                String[] projection,
                String selection,
                String[] selectionArgs,
                String sortOrder
                */
        Uri mTableName = CP.URI_SOURCE;
        String[] mProjection = Source.sourceColumns;
        String mSelection = null;
        String[] mSelectionArgs = null;
        String mSortOrder = null;

        Cursor c = context.getContentResolver().query(mTableName, mProjection,mSelection,mSelectionArgs,mSortOrder);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Source temp = new Source();
            temp.setId(c.getInt(c.getColumnIndex(Source.ID)));
            temp.setName(c.getString(c.getColumnIndex(Source.NAME)));
            temp.setLink(c.getString(c.getColumnIndex(Source.LINK)));
            temp.setTop_value(c.getInt(c.getColumnIndex(Source.TOP_VALUE)));
            temp.setBottom_value(c.getInt(c.getColumnIndex(Source.BOTTOM_VALUE)));
            temp.setVauvau(c.getInt(c.getColumnIndex(Source.VAU)));
            s.add(temp);
            c.moveToNext();
        }
        c.close();
        return s;
    }

    public List<String> getLastLinks(long sourceID){
        Log.d(TAG, "getLastLinks");
        List<String> s = new ArrayList<String>();

        Uri mTableName = CP.URI_RESULT;
        String[] mProjection = Result.resultColumns;
        String mSelection = Result.SOURCE_ID+"=?";
        String[] mSelctionArgs = new String[]{Long.toString(sourceID)};
        String mSortOrder = Result.ID + " DESC";
        String mLimit = "20";

        Cursor c = context.getContentResolver().query(mTableName, mProjection, mSelection, mSelctionArgs, mSortOrder + " LIMIT 20");
        Log.d(TAG, "LastLinks COUNT="+c.getCount());
        c.moveToFirst();
        while(!c.isAfterLast()){
            s.add(c.getString(c.getColumnIndex(Result.ORIGINAL_LINK)));
            c.moveToNext();
        }
        c.close();

        return s;
    }

    public List<String> getExcludedList(long sourceID){
        Log.d(TAG, "getExcludedList");
        List<String> s = new ArrayList<String>();

        Uri mTableName = CP.URI_EXCLUDE;
        String[] mProjection = ExcludeUsers.excludedUsersColumns;
        String mSelection = null;
        String[] mSelectionArgs =null;
        String mSortOrder = null;
        String mLimit = null;

        Cursor c = context.getContentResolver().query(mTableName, mProjection,mSelection,mSelectionArgs,mSortOrder);
        c.moveToFirst();
        while(!c.isAfterLast()){
            s.add(c.getString(c.getColumnIndex(ExcludeUsers.USER)));
            c.moveToNext();
        }

        c.close();
        return s;
    }

    public void insertResults(List<Result> rList){
        Log.d(TAG, "insertResults");
        ContentValues[] values = new ContentValues[rList.size()];
        int count = 0;
        for(Result r : rList){
            values[count] = new ContentValues();
            values[count].put(Result.CONTENT, r.getContent());
            values[count].put(Result.LINK, r.getLink());
            values[count].put(Result.ORIGINAL_LINK, r.getOriginalLink());
            values[count].put(Result.PHONE_NUMBER, r.getPhone_number());
            values[count].put(Result.SELLER, r.getSeller());
            values[count].put(Result.PRICE,r.getPrice());
            values[count].put(Result.SOURCE_ID,r.getSource_id());
            values[count].put(Result.TITLE, r.getTitle());
            values[count].put(Result.TIME, r.getTime());
            count++;

        }
        if(context==null)Log.d(TAG, "context je null");
        if(values==null)Log.d(TAG, "values su null");
        int rows = context.getContentResolver().bulkInsert(CP.URI_RESULT, values);
        Log.w(TAG, rows + " rows created");

    }

    public boolean isOpen(){
       return false;
    }
}
