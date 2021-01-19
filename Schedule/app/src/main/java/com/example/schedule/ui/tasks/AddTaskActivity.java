package com.example.schedule.ui.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.schedule.MainActivity;
import com.example.schedule.R;
import com.example.schedule.alarm.AlarmBrodcast;
import com.example.schedule.po.Task;
import com.example.schedule.taskdao.TaskDaoImp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ed_content;
    SharedPreferences sp;
    TaskDaoImp taskDaoImp;
    // 自定义变量
    private EditText titleEdit;
    private EditText contentEdit;
    String timeTonotify;
    private CheckBox cb_repeat;
    private ImageView iv_important;
    private TextView tv_class;
    private TextView tv_importance;
    // 底部四个布局按钮

    // 定义显示时间控件
    private LinearLayout llDate, llTime;
    private EditText btn_date;
    private EditText btn_time;
    private Button btn_save;
    private Button btn_del_task;

    private Calendar calendar; // 通过Calendar获取系统时间
    private StringBuffer date, time;
    private int year, month, day, hour, minute;

    Intent intent;
    private  int task_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        taskDaoImp = new TaskDaoImp(this);
        intent = getIntent();
        task_id= intent.getIntExtra("task_id", 0);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        String[] mItems = getResources().getStringArray(R.array.type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String[] languages = getResources().getStringArray(R.array.type);
                tv_class.setText(languages[pos]);
                Toast.makeText(AddTaskActivity.this, "你选中的是:" + languages[pos], 2000).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        initView();

        Log.d("add_task", "" + task_id);
        if (task_id != 0) {
            Log.d("add_task", "this is edit page");
            setTitle("修改任务");
            Task task = new Task();
            task = taskDaoImp.findById(task_id);
            btn_save.setText("修改");
            btn_date.setText(task.getRemind_date());
            btn_time.setText(task.getRemind_time());
            ed_content.setText(task.getContent());
            if (task.getIs_importance() == 1) {
                tv_importance.setText("1");
                iv_important.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_important_yes_foreground));
            } else {
                tv_importance.setText("0");
                iv_important.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_important_no_foreground));
            }
            if (task.getIs_repeat() == 1) {
                cb_repeat.setChecked(true);
            } else {
                cb_repeat.setChecked(false);
            }
            String[] sort = {"读书", "健身", "娱乐", "工作", "其他"};
            int pos = 0;
            for(int i = 0 ; i<sort.length;i++){
                if(sort[i].equals(task.getSort())){
                    pos = i;
                }
            }
            Log.d("add_task", "pos:" + pos);
            spinner.setSelection(pos);
            Log.d("add_task", "" + task.getContent());
        } else {
            setTitle("添加任务");
            btn_save.setText("添加");
            btn_del_task.setVisibility(View.GONE);
            Log.d("add_task", "this is new page");
        }

    }

    private void initView() {
        btn_save = findViewById(R.id.btn_save);
        btn_del_task = findViewById(R.id.btn_del_task);
        ed_content = findViewById(R.id.et_content);
        btn_save.setOnClickListener(this);
        btn_del_task.setOnClickListener(this);
        date = new StringBuffer();
        time = new StringBuffer();

        llDate = (LinearLayout) findViewById(R.id.ll_date);
        llTime = (LinearLayout) findViewById(R.id.ll_time);

        btn_date = findViewById(R.id.et_date);
        btn_time = findViewById(R.id.et_time);

        cb_repeat = findViewById(R.id.cb_repeat);
        iv_important = findViewById(R.id.iv_important);

        tv_class = findViewById(R.id.tv_class);
        tv_importance = findViewById(R.id.tv_importance);

        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        iv_important.setOnClickListener(this);
        initDateTime();

    }

    @Override
    public void onClick(View v) {
        System.out.print("----------------click");
        System.out.println(v.getId());
        switch (v.getId()) {

            case R.id.btn_save:
                saveTask();
                break;
            case R.id.et_date: //data btn
                selectDate();
                break;
            case R.id.et_time: // time btn
                selectTime();
                break;
            case R.id.iv_important: // is important btn
                setImportance();
                break;
            case R.id.btn_del_task: // is important btn
                deleteTask();
                break;
        }
    }

    private void deleteTask() {
        int res = taskDaoImp.remove(task_id);
        if(res==1){
            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "删除失败！", Toast.LENGTH_SHORT).show();
        }
        Intent intent1 = new Intent(AddTaskActivity.this, MainActivity.class);
        startActivity(intent1);
        finish();

    }

    private void setImportance() {
        String is_importance = tv_importance.getText().toString().trim();
        if (is_importance.equals("0")) {
            Toast.makeText(this, "已标记为重要", Toast.LENGTH_SHORT).show();
            tv_importance.setText("1");
            iv_important.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_important_yes_foreground));
        } else {
            tv_importance.setText("0");
            Toast.makeText(this, "取消标记重要", Toast.LENGTH_SHORT).show();
            iv_important.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_important_no_foreground));
        }

    }

    private void initDateTime() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

    }

    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;
                //btn_time.setText(timeTonotify);
                btn_time.setText(FormatTime(i, i1));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btn_date.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }

        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }

        return time;
    }


    private void setAlarm(String text, String date, String time) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlarmBrodcast.class);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            System.out.println("-------set alarm " + dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();
    }

    private void saveTask(){
        String content = ed_content.getText().toString().trim();
        String tv_date = btn_date.getText().toString().trim();
        String tv_time = btn_time.getText().toString().trim();
        String option_class = tv_class.getText().toString().trim();
        String is_importance = tv_importance.getText().toString().trim();
        int final_is_repeat = 0;
        if (cb_repeat.isChecked()) {
            final_is_repeat = 1;
        }
        Task task = new Task();
        task.setIs_finish(0);
        task.setIs_importance(Integer.parseInt(is_importance));
        task.setIs_repeat(final_is_repeat);
        task.setRemind_time(tv_time);
        task.setRemind_date(tv_date);
        task.setContent(content);
        task.setSort(option_class);
        if (content.isEmpty()) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
        } else {
            setAlarm(content, tv_date, tv_time);
            task.setCreate_id(Integer.parseInt(sp.getString("user_id", "0")));
            if(task_id == 0){
                taskDaoImp.add(task);
                Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();

            }else{
                task.setId(task_id);
                int res = taskDaoImp.update(task);
                if(res ==1){
                    Toast.makeText(this, "更新成功!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "更新失败!", Toast.LENGTH_SHORT).show();
                }

            }
            Intent intent1 = new Intent(AddTaskActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}