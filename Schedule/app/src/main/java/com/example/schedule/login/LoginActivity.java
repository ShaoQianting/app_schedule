package com.example.schedule.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.schedule.MainActivity;
import com.example.schedule.R;
import com.example.schedule.userdao.UserDaoImp;
import com.example.schedule.po.User;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_User, et_Psw;
    private CheckBox cb_rmbPsw;
    private String userName;
    public SharedPreferences.Editor editor;
    UserDaoImp userDaoImp;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        Objects.requireNonNull(getSupportActionBar()).hide();//隐藏标题栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//禁止横屏
        setContentView(R.layout.activity_login);

        initView();//初始化界面
        userDaoImp = new UserDaoImp(this);
        SharedPreferences sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        editor = sp.edit();
        if(sp.getBoolean("flag",false)){
            String user_read = sp.getString("user","");
            String psw_read = sp.getString("psw","");
            et_User.setText(user_read);
            et_Psw.setText(psw_read);
        }
    }

    private void initView() {

        et_User = findViewById(R.id.et_User);
        et_Psw = findViewById(R.id.et_Psw);
        cb_rmbPsw = findViewById(R.id.cb_rmbPsw);
        Button btn_Login = findViewById(R.id.btn_Login);
        TextView tv_register = findViewById(R.id.tv_Register);

        btn_Login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Register: //注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);//跳转到注册界面
                startActivity(intent);
                finish();
                break;
            case R.id.btn_Login:
                String name = et_User.getText().toString().trim();
                String password = et_Psw.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = userDaoImp.findAll();
                    boolean match = false;
                    boolean match2 = false;
                    System.out.println(data.size());
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);

                        if ((name.equals(user.getUser_name()) && password.equals(user.getPassword()))||
                                (name.equals(user.getEmail())&&password.equals(user.getPassword()))||
                                (name.equals(user.getPhone_num())&&password.equals(user.getPassword()))) {
                            userName = user.getUser_name();
                            match = true;
                            if(cb_rmbPsw.isChecked()){
                                editor.putBoolean("flag",true);
                                editor.putString("user_id",Integer.toString(user.getId()));
                                editor.putString("user",user.getUser_name());
                                editor.putString("psw",user.getPassword());
                                editor.putBoolean("is_login",true);
                                editor.apply();
                                match2 = true;
                            }else {
                                editor.putString("user",user.getUser_name());
                                editor.putString("psw","");
//                                editor.clear();
                                editor.apply();
                                match2 = false;
                            }
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if(match2){
                            Toast.makeText(this, "成功记住密码", Toast.LENGTH_SHORT).show();
                            cb_rmbPsw.setChecked(true);
                        }
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Runnable target;

                        Thread thread = new Thread(){
                            @Override
                            public void run(){
                                try {
                                    sleep(2000);//2秒 模拟登录时间
                                    String user_name = userName;
                                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);//设置自己跳转到成功的界面
                                    //intent1.putExtra("user_name",user_name);
                                    startActivity(intent1);
                                    finish();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        };
                        thread.start();//打开线程
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
