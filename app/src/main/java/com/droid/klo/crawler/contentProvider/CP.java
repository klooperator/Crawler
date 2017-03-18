package com.droid.klo.crawler.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.droid.klo.crawler.db.ExcludeUsers;
import com.droid.klo.crawler.db.Result;
import com.droid.klo.crawler.db.Source;

/**
 * Created by prpa on 3/17/17.
 */

public class CP extends ContentProvider {

    //region variable
    //URIs
    private static final String AUTHORITY ="com.droid.klo.crawler.provider";
    public static final Uri URI_RESULT = Uri.parse("content://" + AUTHORITY + "/" + Result.TABLE_RESULTS_NAME);
    public static final Uri URI_SOURCE = Uri.parse("content://" + AUTHORITY + "/" + Source.TABLE_SOURCE_NAME);
    public static final Uri URI_EXCLUDE = Uri.parse("content://" + AUTHORITY + "/" + ExcludeUsers.TABLE_EXCLUDE_NAME);

    private static final int RESULT_TABLE = 1;
    private static final int SOURCE_TABLE = 2;
    private static final int EXCLUDE_TABLE = 3;

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Result.TABLE_RESULTS_NAME, RESULT_TABLE);
        uriMatcher.addURI(AUTHORITY, Source.TABLE_SOURCE_NAME, SOURCE_TABLE);
        uriMatcher.addURI(AUTHORITY, ExcludeUsers.TABLE_EXCLUDE_NAME, EXCLUDE_TABLE);
        return uriMatcher;
    }
    //endregion

    //region overrides
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
    //endregion
}
