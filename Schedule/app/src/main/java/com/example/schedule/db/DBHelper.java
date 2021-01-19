package com.example.schedule.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "db_schedule", null, 6);
        db = getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS tb_user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_name TEXT," +
                "password TEXT," +
                "email TEXT," +
                "phone_num TEXT," +
                "item_sets TEXT)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS tb_task(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "is_finish INTEGER," +
                "is_importance INTEGER," +
                "is_repeat INTEGER," +
                "remind_time TEXT," +
                "remind_date TEXT," +
                "content TEXT," +
                "sort TEXT," +
                "create_id INTEGER)"
        );
//
//        db.execSQL("CREATE TABLE IF NOT EXISTS tb_icon(" +
//                "kind INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "kind_id INTEGER," +
//                "kind_name TEXT," +
//                "kind_pic INTEGER)"
//        );
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
//        db.execSQL("DROP TABLE IF EXISTS tb_user");
//        db.execSQL("DROP TABLE IF EXISTS tb_task");
//        db.execSQL("DROP TABLE IF EXISTS tb_icon");
        onCreate(db);
    }

}
