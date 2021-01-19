package com.example.schedule.taskdao;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.schedule.db.DBHelper;
import com.example.schedule.po.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoImp implements TaskDao {
    DBHelper helper = null;
    private String table_name = "tb_task";

    public TaskDaoImp(Context context) {
        helper = new DBHelper(context);

    }

    @Override
    public void add(Task task) {
        System.out.print("----------------task add ");
        System.out.print(task.getContent());

        SQLiteDatabase db = helper.getReadableDatabase();

        db.execSQL("INSERT INTO " + table_name + " (is_finish,is_importance,is_repeat,remind_time,remind_date,content,sort,create_id) " +
                        "VALUES(?,?,?,?,?,?,?,?)",
                new Object[]{task.getIs_finish(), task.getIs_importance(), task.getIs_repeat(),
                        task.getRemind_time(), task.getRemind_date(), task.getContent(), task.getSort(), task.getCreate_id()});
        System.out.print("保存成功");
    }

    @Override
    public int remove(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int result=db.delete(table_name, "id=?", new String[]{Integer.toString(id)});
        return result;
    }

//     map_task.put("id", Integer.toString(id));
//                map_task.put("is_finish", Integer.toString(is_finish));
//                map_task.put("is_importance", Integer.toString(is_importance));
//                map_task.put("is_repeat", Integer.toString(is_repeat));
//                map_task.put("create_id", Integer.toString(create_id));
//                map_task.put("remind_time", remind_time);
//                map_task.put("content", content);
//                map_task.put("sort", sort);
//                map_task.put("remind_date", remind_date);

    @Override
    public int update(Task task) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", task.getId());
        values.put("is_finish", task.getIs_finish());
        values.put("is_importance", task.getIs_importance());
        values.put("is_repeat", task.getIs_repeat());
        values.put("remind_date", task.getRemind_date());
        values.put("remind_time", task.getRemind_time());
        values.put("content", task.getContent());
        values.put("sort", task.getSort());
        Log.d("update_task",""+values);
        int result = db.update(table_name, values, "id=?", new String[]{Integer.toString(task.getId())});
        System.out.print("after update:" + result);
        return result;
    }

    @Override
    public Task findById(int task_id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(table_name, null, "id=" + task_id, null, null, null, "ID DESC");
//        System.out.print("____-----------in database");
        cursor.moveToFirst();
        List<Map> list = new ArrayList<Map>();
        Task task = new Task();
        if (cursor != null && cursor.moveToFirst()) {

            Map<String, String> map_task = new HashMap<>();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int is_finish = cursor.getInt(cursor.getColumnIndex("is_finish"));
            int is_importance = cursor.getInt(cursor.getColumnIndex("is_importance"));
            int is_repeat = cursor.getInt(cursor.getColumnIndex("is_repeat"));
            int create_id = cursor.getInt(cursor.getColumnIndex("create_id"));
            String remind_time = cursor.getString(cursor.getColumnIndex("remind_time"));
            String remind_date = cursor.getString(cursor.getColumnIndex("remind_date"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String sort = cursor.getString(cursor.getColumnIndex("sort"));

            task.setId(id);
            task.setIs_finish(is_finish);
            task.setIs_importance(is_importance);
            task.setIs_repeat(is_repeat);
            task.setCreate_id(create_id);
            task.setRemind_date(remind_date);
            task.setRemind_time(remind_time);
            task.setContent(content);
            task.setSort(sort);

        }
        cursor.close();
        return task;
    }

    @Override
    public List<Task> findByName(Task user_name) {
        return null;
    }

    //     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//             "is_finish INTEGER," +
//             "is_importance INTEGER," +
//             "is_repeat INTEGER," +
//             "remind_time TEXT," +
//             "content TEXT," +
//             "sort TEXT," +
//             "create_id INTEGER)"
    @Override
    public List<Map> findAll(int user_id) {
//        int user_id= 2;
        SQLiteDatabase db = helper.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(table_name, null, "create_id=" + user_id, null, null, null, "ID DESC");
//        System.out.print("____-----------in database");
        cursor.moveToFirst();
        List<Map> list = new ArrayList<Map>();
        if (cursor != null && cursor.moveToFirst())
            do {
                Map<String, String> map_task = new HashMap<>();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int is_finish = cursor.getInt(cursor.getColumnIndex("is_finish"));
                int is_importance = cursor.getInt(cursor.getColumnIndex("is_importance"));
                int is_repeat = cursor.getInt(cursor.getColumnIndex("is_repeat"));
                int create_id = cursor.getInt(cursor.getColumnIndex("create_id"));
                String remind_time = cursor.getString(cursor.getColumnIndex("remind_time"));
                String remind_date = cursor.getString(cursor.getColumnIndex("remind_date"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String sort = cursor.getString(cursor.getColumnIndex("sort"));

                map_task.put("id", Integer.toString(id));
                map_task.put("is_finish", Integer.toString(is_finish));
                map_task.put("is_importance", Integer.toString(is_importance));
                map_task.put("is_repeat", Integer.toString(is_repeat));
                map_task.put("create_id", Integer.toString(create_id));
                map_task.put("remind_time", remind_time);
                map_task.put("content", content);
                map_task.put("sort", sort);
                map_task.put("remind_date", remind_date);
                list.add(map_task);
            } while (cursor.moveToNext());

        cursor.close();
        return list;
    }

    @Override
    public int updateImportance(int id, int importance) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("is_importance", importance);

        int result = db.update(table_name, values, "id=?", new String[]{Integer.toString(id)});
        System.out.print("after update importance:" + result);
        return result;
    }

    @Override
    public int updateFinish(int id, int finish) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        Log.d("database:", "finish:" + finish);
        values.put("is_finish", finish);

        int result = db.update(table_name, values, "id=?", new String[]{Integer.toString(id)});
        System.out.print("after update finish:" + result);
        return result;
    }


}
