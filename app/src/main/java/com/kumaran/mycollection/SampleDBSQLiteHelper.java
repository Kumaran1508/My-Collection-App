package com.kumaran.mycollection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDBSQLiteHelper extends SQLiteOpenHelper {

    public SampleDBSQLiteHelper(Context context) {
        super(context, "Hello", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
