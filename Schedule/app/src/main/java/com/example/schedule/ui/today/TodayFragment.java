package com.example.schedule.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.schedule.R;

public class TodayFragment extends Fragment {

    private TodayModel todayModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todayModel =
                new ViewModelProvider(this).get(TodayModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);

//        final TextView textView = root.findViewById(R.id.text_home);
//        oneDayModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}