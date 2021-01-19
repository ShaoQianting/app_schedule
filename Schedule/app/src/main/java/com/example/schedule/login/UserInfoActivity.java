package com.example.schedule.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.schedule.MainActivity;
import com.example.schedule.R;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    public SharedPreferences.Editor editor;
    private Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        setTitle("我的信息");

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                doLogout();
                break;
        }
    }

    private void doLogout() {
        SharedPreferences sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        editor = sp.edit();
        //editor.remove("user_id");
        //editor.remove("user");
        //editor.remove("psw");
        editor.remove("is_login");
        editor.apply();
        Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
        finish();
    }
}