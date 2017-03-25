package com.droid.klo.crawler.db;

import android.net.Uri;

import com.droid.klo.crawler.contentProvider.CP;

/**
 * Created by prpa on 3/15/17.
 */

public class Result {

    //region variabels
    private long id;
    private long source_id;
    private String title;
    private String content;
    private String phone_number;
    private String time;
    private int price;

    //table
    public static final String TABLE_RESULTS_NAME = "t_results";

    //TABLE_RESULTS columns
    public static final String ID = "_ID"; //int autoincrement
    public static final String SOURCE_ID = "_source_id"; //int foreign key
    public static final String PHONE_NUMBER = "result_phone_number"; // text
    public static final String TITLE = "result_title";//text
    public static final String CONTENT = "result_content"; //text
    public static final String PRICE = "result_price"; //int
    public static final String TIME = "result_time"; //text
    public static final String ORIGINAL_LINK = "original_link";
    public static final String LINK = "link";

    public static final String[] resultColumns = {ID,SOURCE_ID, PHONE_NUMBER, TITLE, CONTENT,PRICE,TIME, ORIGINAL_LINK, LINK};

    //create statements
    public static final String CREATE_TABLE_RESULTS =
            "CREATE TABLE " + TABLE_RESULTS_NAME + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SOURCE_ID + " INTEGER, " +
                    TITLE + " TEXT NOT NULL, " +
                    CONTENT + " TEXT, " +
                    PHONE_NUMBER + " TEXT NOT NULL, " +
                    PRICE + " INT, " +
                    TIME + " TEXT, " +
                    ORIGINAL_LINK + " TEXT, "+
                    LINK + " TEXT, " +
                    "FOREIGN KEY (" + SOURCE_ID + ") REFERENCES " + Source.TABLE_SOURCE_NAME + "(" +Source.ID + ")" +
                    ");";
    //endregion

    public static final Uri CONTENT_URI = CP.BASE_CONTENT_URI.buildUpon().appendPath(TABLE_RESULTS_NAME).build();

    //region getters setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSource_id() {
        return source_id;
    }

    public void setSource_id(long source_id) {
        this.source_id = source_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    //endregion
}
