package com.solarexsoft.aidldemo.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 07:42/2019-10-22
 *    Desc:
 * </pre>
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "book_provider.db";
    private static final int DB_VERSION = 1;

    public static final String BOOK_TABLE_NAME = "book";
    public static final String USER_TABLE_NAME = "user";

    private String CREATE_BOOK_TABLE = "create table if not exists " + BOOK_TABLE_NAME + "(_id integer primary key, name text)";
    private String CREATE_USER_TABLE = "create table if not exists " + USER_TABLE_NAME + "(_id integer primary key, name text, gender int)";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
