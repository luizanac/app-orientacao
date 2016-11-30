package com.example.laz.appoo.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by laz on 30/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "weboo";
    private static final int DB_VERSION = 1;
    private static final String TABLE =  "create table students(" +
            "id integer primary key autoincrement," +
            "name varchar(45)," +
            "email varchar(45)," +
            "course varchar(45)," +
            "school varchar(45)," +
            "phone varchar(45)," +
            "id_user integer," +
            "tag varchar(45));";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
