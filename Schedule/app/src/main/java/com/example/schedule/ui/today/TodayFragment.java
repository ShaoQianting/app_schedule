package com.example.schedule.ui.today;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.MainActivity;
import com.example.schedule.R;
import com.example.schedule.po.Task;
import com.example.schedule.taskdao.TaskDaoImp;
import com.example.schedule.ui.tasks.AddTaskActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TodayFragment extends Fragment {
    public SharedPreferences.Editor editor;
    private TodayViewModel todayViewModel;
    private List<Task> taskList = new ArrayList<>();
    private TodayAdapter adapter;
    TaskDaoImp taskDaoImp;
    private  Task task;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        todayViewModel = new ViewModelProvider(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);
//        final TextView tv_today_time = root.findViewById(R.id.today_time);

//        添加首页时间
        final TextView tv_today_time = root.findViewById(R.id.today_time);
        todayViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                tv_today_time.setText(s);
            }
        });

        return root;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        首页列表
        taskDaoImp = new TaskDaoImp(getActivity());

        SharedPreferences sp = getActivity().getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
        boolean is_firstLogin = sp.getBoolean("is_firstLogin",false);
        List<Map> data = taskDaoImp.findAll(Integer.parseInt(sp.getString("user_id","0")));

        initToday(data);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_todayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TodayAdapter(taskList);
        recyclerView.setAdapter(adapter);

        adapter.setImportanceOnClick(new TodayAdapter.OnItemImportanceClickListener() {
            @Override
            public void onClick(int id,int importance) {
                if(importance == 1){
                    taskDaoImp.updateImportance(id,0);
                    Toast.makeText(getActivity(), "取消标记重要", Toast.LENGTH_SHORT).show();
                }else{
                    taskDaoImp.updateImportance(id,1);
                    Toast.makeText(getActivity(), "已标记为重要", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter.setOnItemFinishClickListener(new TodayAdapter.OnItemFinishClickListener() {
            @Override
            public void onClick(int id, int finish) {
                Log.d("test ","ss:"+finish);
                if(finish == 1){
                    taskDaoImp.updateFinish(id,0);
                    Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT).show();
                }else{
                    taskDaoImp.updateFinish(id,1);
                    Toast.makeText(getActivity(), "已完成", Toast.LENGTH_SHORT).show();
                }
                //int res = taskDaoImp.updateFinish(id,finish);
//                if(res == 1){
//                    if(finish == 1){
//                        Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getActivity(), "已完成", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    Toast.makeText(getActivity(), "更新失败", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getActivity(), "isfinish click " + id, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemClickListener(new TodayAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                intent.putExtra("task_id", id);
                startActivity(intent);
                Toast.makeText(getActivity(), "点击"+id, Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void initToday(List<Map> data) {
        //System.out.println(data);
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
            String remind_date = data.get(i).get("remind_date").toString();
            System.out.println(convertDate(remind_date));
            if(convertDate(remind_date).equals(formatter.format(date))){
                task = new Task();
                task.setId(Integer.parseInt(data.get(i).get("id").toString()));
                task.setIs_repeat(Integer.parseInt(data.get(i).get("is_repeat").toString()));
                task.setIs_importance(Integer.parseInt(data.get(i).get("is_importance").toString()));
                task.setIs_finish(Integer.parseInt(data.get(i).get("is_finish").toString()));
                task.setCreate_id(Integer.parseInt(data.get(i).get("create_id").toString()));
                task.setRemind_time(data.get(i).get("remind_time").toString());
                task.setSort(data.get(i).get("sort").toString());
                task.setContent(data.get(i).get("content").toString());
                task.setRemind_date(data.get(i).get("remind_date").toString());
                taskList.add(task);
            }


        }
    }
    private String convertDate(String date){
        String[] list_date = date.split("-");
        String  new_Date = "";
        if (list_date[1].length() == 1){
            new_Date = list_date[0]+"-0"+list_date[1]+"-"+list_date[2];
        }else{
            new_Date = list_date[0]+"-"+list_date[1]+"-"+list_date[2];
        }
        return new_Date;
    }
}