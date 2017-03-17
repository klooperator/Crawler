package com.droid.klo.crawler.db;

/**
 * Created by prpa on 3/15/17.
 */

public class ExcludeUsers {

    //region variables
    public static final String ID = "_id";
    public static final String USER = "user";

    //table
    public static final String TABLE_EXCLUDE_NAME = "t_result";

    //create statements
    public static final String CREATE_TABLE_EXCLUDE =
            "CREATE TABLE " + TABLE_EXCLUDE_NAME + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER + " TEXT" +
                    ");";

    //endregion
}
