package com.example.schedule.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.schedule.db.DBHelper;
import com.example.schedule.po.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao{
    DBHelper helper = null;


    public UserDaoImp(Context context){
        helper = new DBHelper(context);

    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findById(int id) {

        return null;
    }

    @Override
    public List<User> findByName(User user_name) {
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
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

}
