package com.droid.klo.crawler.db;

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
    public static final String ID = "_id"; //int autoincrement
    public static final String SOURCE_ID = "_source_id"; //int foreign key
    public static final String PHONE_NUMBER = "result_phone_number"; // text
    public static final String TITLE = "result_title";//text
    public static final String CONTENT = "result_content"; //text
    public static final String PRICE = "result_price"; //int
    public static final String TIME = "result_time"; //text

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
                    "FOREIGN KEY (" + SOURCE_ID + ") REFERENCES " + Source.TABLE_SOURCE_NAME + "(" +Source.ID + ")" +
                    ");";
    //endregion

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
