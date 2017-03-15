package com.droid.klo.crawler.db;

/**
 * Created by prpa on 3/15/17.
 */

public class Source {

    //region variabels
    private long id;
    private String name;
    private String link;
    private int top_value;
    private int bottom_value;

    //table
    public static final String TABLE_SOURCE_NAME = "t_source";

    //TABLE_SOURCE columns
    public static final String ID = "_id";
    public static final String NAME = "source_name"; //text
    public static final String LINK = "source_link"; //text
    public static final String TOP_VALUE = "source_top_value"; //int
    public static final String BOTTOM_VALUE = "source_bottom_value"; //int

    public static final String[] sourceColumns = {NAME, LINK, TOP_VALUE, BOTTOM_VALUE};

    //create statements
    public static String CREATE_TABLE_SOURCE =
            "CREATE TABLE " + TABLE_SOURCE_NAME + "( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    LINK + " TEXT NOT NULL, " +
                    BOTTOM_VALUE + " INTEGER, " +
                    TOP_VALUE + " INTEGER" +
                    ");";
    //endregion

    //region getters setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getTop_value() {
        return top_value;
    }

    public void setTop_value(int top_value) {
        this.top_value = top_value;
    }

    public int getBottom_value() {
        return bottom_value;
    }

    public void setBottom_value(int bottom_value) {
        this.bottom_value = bottom_value;
    }
    //endregion
}
