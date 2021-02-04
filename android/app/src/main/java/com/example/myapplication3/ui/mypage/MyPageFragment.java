package com.example.myapplication3.ui.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication3.R;

public class MyPageFragment extends Fragment {

    private MyPageViewModel MyPageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyPageViewModel =
                new ViewModelProvider(this).get(MyPageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);
        final TextView textView = (TextView)root.findViewById(R.id.text_mypage);
        MyPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}