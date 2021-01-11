package com.example.schedule.ui.importance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImportanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ImportanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is importance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}