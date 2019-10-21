package com.solarexsoft.aidldemo.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
