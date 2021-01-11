package com.example.schedule.ui.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodayViewModel extends ViewModel {

    final private MutableLiveData<String> mText;

    public TodayViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {

        return mText;
    }
}