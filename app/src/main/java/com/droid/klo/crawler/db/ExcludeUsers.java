package com.droid.klo.crawler.db;

import android.net.Uri;

import com.droid.klo.crawler.contentProvider.CP;

/**
 * Created by prpa on 3/15/17.
 */

public class ExcludeUsers {

    //region variables
    public static final String ID = "_ID";
    public static final String USER = "user";
    public static final String SOURCE = "source";

    //table
    public static final String TABLE_EXCLUDE_NAME = "t_result";

    //all columns
    public static final String[] excludedUsersColumns = {ID, USER, SOURCE};

    //create statements
    public static final String CREATE_TABLE_EXCLUDE =
            "CREATE TABLE " + TABLE_EXCLUDE_NAME + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER + " TEXT" +
                    ");";

    //URIs
    public static final Uri CONTENT_URI = CP.BASE_CONTENT_URI.buildUpon().appendPath(TABLE_EXCLUDE_NAME).build();

    //endregion
}
