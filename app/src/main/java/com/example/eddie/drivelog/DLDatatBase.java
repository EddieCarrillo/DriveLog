package com.example.eddie.drivelog;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eddie on 9/19/2015.
 */
public class DLDatatBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dldatabase.db";
    private static final String TABLE_NAME = "DLTABLE";
    private static final String UID = "_id";
    private static final String DATE = "date";
    private static final int DATABASE_VERSION = 1;

    DLDatatBase (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {          db.execSQL("CREATE TABLE DLTABLE(_id INTEGER PRIMARY" +
                    "KEY AUTOINCREMENT,Name VARCHAR(255))");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DLTABLE");
        onCreate(db);
    }
}
