package com.example.myapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/4/30.
 */
public class TimeOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_SUMMARY = "create table Summary ("
            + "id integer primary key autoincrement, "
            + "time real, "
            + "conclusion text)";
    public static final String CREATE_LIFE = "create table Life (" +
            "id integer primary key autoincrement, " +
            "time real, " +
            "mood text)" ;
    public static final String CREATE_PLAN = "create table Plan (" +
            "id integer primary key autoincrement, " +
            "time real, " +
            "number integer, " +
            "content text)";

    public TimeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIFE);
        db.execSQL(CREATE_PLAN);
        db.execSQL(CREATE_SUMMARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
