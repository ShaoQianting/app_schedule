package com.example.schedule.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "db_test", null, 2);
        db = getReadableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "email TEXT," +
                "phonenum TEXT)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS tb_account(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type INTEGER," +
                "kind INTEGER," +
                "cost_money TEXT," +
                "remark TEXT," +
                "time TEXT)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS tb_icon(" +
                "kind INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kind_id INTEGER," +
                "kind_name TEXT," +
                "kind_pic INTEGER)"
        );
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS tb_account");
        db.execSQL("DROP TABLE IF EXISTS tb_icon");
        onCreate(db);
    }

}
