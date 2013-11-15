package com.your.worth.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Catalin BORA
 * Date: 10/18/13
 * Time: 2:48 PM
 */
class SQLiteHelper extends SQLiteOpenHelper {

    // The fields
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MODIFIED = "modified";
    public static final String TABLE_WORTH = "worth";


    private static final String DATABASE_NAME = "worth.db";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_WORTH + "(" +
            COLUMN_ID + " text primary key not null , " +
            COLUMN_VALUE + " integer not null ," +
            COLUMN_DESCRIPTION + " text ,"+
            COLUMN_TYPE + " text not null,"+
            COLUMN_DATE + "  text, " +
            COLUMN_MODIFIED + " integer );";

    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WORTH);
        onCreate(sqLiteDatabase);
    }
}
