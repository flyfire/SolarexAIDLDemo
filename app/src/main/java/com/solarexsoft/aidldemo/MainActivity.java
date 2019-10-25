package com.solarexsoft.aidldemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.solarexsoft.aidldemo.contentproviderdemo.BookProvider;
import com.solarexsoft.aidldemo.contentproviderdemo.DBOpenHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "current process = " + Utils.getAppNameByPID(this, Process.myPid()));

        Uri bookUri = Uri.parse("content://" + BookProvider.AUTHORITY + "/" + DBOpenHelper.BOOK_TABLE_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", 6);
        contentValues.put("name", "linux");
        getContentResolver().insert(bookUri, contentValues);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            int id = bookCursor.getInt(bookCursor.getColumnIndex("_id"));
            String name = bookCursor.getString(bookCursor.getColumnIndex("name"));
            Log.d(TAG, "book id = " + id + ",name = " + name);
        }
        Uri userUri = Uri.parse("content://" + BookProvider.AUTHORITY + "/" + DBOpenHelper.USER_TABLE_NAME);
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "gender"}, null, null, null);
        while (userCursor.moveToNext()) {
            int id = userCursor.getInt(userCursor.getColumnIndex("_id"));
            String name = userCursor.getString(userCursor.getColumnIndex("name"));
            int gender = userCursor.getInt(userCursor.getColumnIndex("gender"));
            Log.d(TAG, "id = " + id + ",name = " + name + ",gender = " + gender);
        }
    }
}
