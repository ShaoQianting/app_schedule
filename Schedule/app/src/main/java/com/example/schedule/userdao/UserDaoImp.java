package com.example.schedule.userdao;

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
        SQLiteDatabase db = helper.getReadableDatabase();
        System.out.println("---------------------------start");
        System.out.println(user.getUser_name());
        db.execSQL("INSERT INTO tb_user (user_name,password,email,phone_num,item_sets) VALUES(?,?,?,?,?)",
                new Object[]{user.getUser_name(), user.getPassword(), user.getEmail(), user.getPhone_num(),user.getItem_sets()});

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
        @SuppressLint("Recycle") Cursor cursor = db.query("tb_user", null, null, null, null, null, "user_name DESC");
        System.out.print("____-----------in database");
        cursor.moveToFirst();
        System.out.println(cursor.getString(cursor.getColumnIndex("user_name")));
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phone_num = cursor.getString(cursor.getColumnIndex("phone_num"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String item_sets = cursor.getString(cursor.getColumnIndex("item_sets"));
            System.out.print("____-----------in database");
            System.out.println(user_name);
            list.add(new User(id,user_name, password,  email,  phone_num,  item_sets));
        }
        return list;
    }

}
