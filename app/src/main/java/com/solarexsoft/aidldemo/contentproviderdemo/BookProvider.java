package com.solarexsoft.aidldemo.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 21:28/2019-10-23
 *    Desc:
 * </pre>
 */

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";
    public static final String AUTHORITY = "com.solarexsoft.bookprovider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate, current thread = " + Thread.currentThread().getName());
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDatabase = new DBOpenHelper(getContext()).getWritableDatabase();
        mDatabase.execSQL("delete from " + DBOpenHelper.BOOK_TABLE_NAME);
        mDatabase.execSQL("delete from " + DBOpenHelper.USER_TABLE_NAME);
        mDatabase.execSQL("insert into book values(1, 'Android');");
        mDatabase.execSQL("insert into book values(2, 'iOS');");
        mDatabase.execSQL("insert into book values(3, 'WP');");
        mDatabase.execSQL("insert into user values(1, 'jackson', 1);");
        mDatabase.execSQL("insert into user values(2, 'jane', 0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query, current thread = " + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport uri = " + uri);
        }
        return mDatabase.query(table, projection, selection, selectionArgs, null, null, sortOrder);
    }

    private String getTableName(Uri uri) {
        String tablename = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tablename = DBOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tablename = DBOpenHelper.USER_TABLE_NAME;
                break;
        }
        return tablename;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType thread = " + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert thread = " + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport uri = " + uri);
        }
        mDatabase.insert(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete thread = " + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport uri = " + uri);
        }
        int count = mDatabase.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update thread = " + Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport uri = " + uri);
        }
        int row = mDatabase.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
