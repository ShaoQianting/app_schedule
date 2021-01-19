package com.example.schedule.ui.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedule.po.Task;
import com.example.schedule.taskdao.TaskDaoImp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayViewModel extends ViewModel {

    final private MutableLiveData<String> mText;
    final private MutableLiveData<Task> mAllTask;


    public TodayViewModel() {

        mText = new MutableLiveData<>();
        mAllTask = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        Date date = new Date();
//        ("dd-MM-yyyy HH:mm:ss")
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        System.out.println(formatter.format(date));
        String today_date = formatter.format(date);
        mText.setValue(today_date);
        return mText;
    }

    public  LiveData<Task> getAllTask(){

        return mAllTask;
    }

}