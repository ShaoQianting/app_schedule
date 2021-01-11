package com.example.schedule.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.schedule.po.User;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public DBOpenHelper(Context context) {
        super(context, "db_test", null, 2);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    //版本适应
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS tb_account");
        db.execSQL("DROP TABLE IF EXISTS tb_icon");
        onCreate(db);
    }


    void add(String name, String password, String email, String phonenum) {
        db.execSQL("INSERT INTO user (name,password,email,phonenum) VALUES(?,?,?,?)", new Object[]{name, password, email, phonenum});
    }

    public void delete(String name, String password) {
        db.execSQL("DELETE FROM user WHERE name = AND password =" + name + password);
    }

    public void updata(String password) {
        db.execSQL("UPDATE user SET password = ?", new Object[]{password});
    }

    ArrayList<User> getAllData() {
        ArrayList<User> list = new ArrayList<User>();
        @SuppressLint("Recycle") Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phonenum = cursor.getString(cursor.getColumnIndex("phonenum"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            list.add(new User(name, password, email, phonenum));
        }
        return list;
    }

    // 添加记录
//    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "type INTEGER," +
//            "kind INTEGER," +
//            "cost_money TEXT," +
//            "remark TEXT," +
//            "time TEXT)"

    public void add_account(int type, int kind, String cost_money, String remark, String time) {

        db.execSQL("INSERT INTO tb_account (type,kind,cost_money,remark,time) VALUES(?,?,?,?,?)",
                new Object[]{type, kind, cost_money, remark, time});
    }

//    ArrayList<Account> getAllRecord() {
//        ArrayList<Account> list = new ArrayList<Account>();
//        @SuppressLint("Recycle") Cursor cursor = db.query("tb_account", null, null, null, null, null, "_id DESC");
//        while (cursor.moveToNext()) {
//            int type = cursor.getInt(cursor.getColumnIndex("type"));
//            int kind = cursor.getInt(cursor.getColumnIndex("kind"));
//            String cost_money = cursor.getString(cursor.getColumnIndex("cost_money"));
//            String remark = cursor.getString(cursor.getColumnIndex("remark"));
//            String time = cursor.getString(cursor.getColumnIndex("time"));
//
//            list.add(new Account(type, kind, cost_money, remark,time));
//        }
//        return list;
//    }

}

