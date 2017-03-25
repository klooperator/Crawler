package com.droid.klo.crawler.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.droid.klo.crawler.db.DBHelper;
import com.droid.klo.crawler.db.ExcludeUsers;
import com.droid.klo.crawler.db.Result;
import com.droid.klo.crawler.db.Source;

/**
 * Created by prpa on 3/17/17.
 */

public class CP extends ContentProvider {

    //region variable
    public static final String TAG = "CP";

    private SQLiteDatabase db;
    private DBHelper myHelper;
    //URIs
    public static final String AUTHORITY ="com.droid.klo.crawler.provider.CP";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri URI_RESULT = Uri.parse("content://" + AUTHORITY + "/" + Result.TABLE_RESULTS_NAME);
    public static final Uri URI_SOURCE = Uri.parse("content://" + AUTHORITY + "/" + Source.TABLE_SOURCE_NAME);
    public static final Uri URI_EXCLUDE = Uri.parse("content://" + AUTHORITY + "/" + ExcludeUsers.TABLE_EXCLUDE_NAME);

    private static final int RESULT_TABLE = 1;
    private static final int RESULT_TABLE_ID = 10;
    private static final int SOURCE_TABLE = 2;
    private static final int SOURCE_TABLE_ID = 20;
    private static final int EXCLUDE_TABLE = 3;
    private static final int EXCLUDE_TABLE_ID = 30;

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Result.TABLE_RESULTS_NAME, RESULT_TABLE);
        uriMatcher.addURI(AUTHORITY, Source.TABLE_SOURCE_NAME, SOURCE_TABLE);
        uriMatcher.addURI(AUTHORITY, ExcludeUsers.TABLE_EXCLUDE_NAME, EXCLUDE_TABLE);
        uriMatcher.addURI(AUTHORITY, Result.TABLE_RESULTS_NAME + "/#", RESULT_TABLE_ID);
        uriMatcher.addURI(AUTHORITY, Source.TABLE_SOURCE_NAME + "/#", SOURCE_TABLE_ID);
        uriMatcher.addURI(AUTHORITY, ExcludeUsers.TABLE_EXCLUDE_NAME + "/#", EXCLUDE_TABLE_ID);
        return uriMatcher;
    }

    //endregion

    //region overrides
    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        myHelper = new DBHelper(getContext());
        Log.d(TAG, "onCreate/myHelper= "+myHelper.toString());
        db = myHelper.getWritableDatabase();
        Log.d(TAG, "onCreate/db= "+db.toString());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query");
        switch (getUriMatcher().match(uri)){
            case RESULT_TABLE:
                try{
                    return db.query(Result.TABLE_RESULTS_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }catch (Exception e){
                    Log.e(TAG, e.getLocalizedMessage(),e);
                }
                break;
            case SOURCE_TABLE:
                try{
                    return db.query(Source.TABLE_SOURCE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }catch (Exception e){
                    Log.e(TAG, e.getLocalizedMessage(),e);
                }
                break;
            case EXCLUDE_TABLE:
                try{
                    return db.query(Source.TABLE_SOURCE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                }catch (Exception e){
                    Log.e(TAG, e.getLocalizedMessage(),e);
                }
                break;
            case RESULT_TABLE_ID:

                break;
            case SOURCE_TABLE_ID:

                break;
            case EXCLUDE_TABLE_ID:

                break;
        }
        return null;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert");
        switch (getUriMatcher().match(uri)){
            case RESULT_TABLE:
                Log.d(TAG, "insert/(case)RESULT_TABLE");
                try{
                    long _id = db.insert(Result.TABLE_RESULTS_NAME, null, values);
                    return ContentUris.withAppendedId(URI_SOURCE, _id);
                }catch (Exception e){
                    Log.e(TAG, e.getLocalizedMessage(),e);
                }
                break;
            //***********************************
            case RESULT_TABLE_ID:
                Log.d(TAG, "insert/(case)RESULT_TABLE_ID");
                break;
            //***********************************
            case SOURCE_TABLE:
                Log.d(TAG, "insert/(case)SOURCE_TABLE");
                try{
                    long _id = db.insert(Source.TABLE_SOURCE_NAME, null, values);
                    return ContentUris.withAppendedId(URI_SOURCE, _id);
                }catch (Exception e){
                    Log.e(TAG, e.getLocalizedMessage(),e);
                }
                break;
            //***********************************
            case SOURCE_TABLE_ID:
                Log.d(TAG, "insert/(case)SOURCE_TABLE_ID");
                break;
            //***********************************
            case EXCLUDE_TABLE:
                Log.d(TAG, "insert/(case)EXCLUDE_TABLE");
                break;
            //***********************************
            case EXCLUDE_TABLE_ID:
                Log.d(TAG, "insert/(case)EXCLUDE_TABLE_ID");

                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update");
        return 0;
    }
    //endregion

    //region custom methods
    private void open_db(){
        Log.d(TAG, "open_db");
        if(db!=null){
            Log.d(TAG, "open_db/db is NOT NULL");
            if(db.isOpen())return;
            else {
                if(myHelper!=null)
                db = myHelper.getWritableDatabase();
            }
        }else{
            Log.d(TAG, "open_db/db is NULL");
            db = myHelper.getWritableDatabase();
        }
        Log.d(TAG, "open_db=END");
    }
    //endregion
}
